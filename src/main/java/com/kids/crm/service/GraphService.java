package com.kids.crm.service;

import com.kids.crm.json.graph.GraphStudentResult;
import com.kids.crm.model.Question;
import com.kids.crm.model.StudentAnswer;
import com.kids.crm.model.SubTopic;
import com.kids.crm.model.User;
import com.kids.crm.model.mongo.QuestionSolvingTime;
import com.kids.crm.mongo.repository.QuestionSolvingTimeRepository;
import com.kids.crm.repository.QuestionRepository;
import com.kids.crm.repository.StudentAnswerRepository;
import com.kids.crm.repository.UserRepository;
import com.kids.crm.utils.DateUtils;
import com.kids.crm.utils.Utils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GraphService {
    @Autowired
    private StudentAnswerRepository studentAnswerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    GraphService graphService;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    QuestionSolvingTimeRepository questionSolvingTimeRepository;

    public List<GraphStudentResult> getGraphStudentResultList(User user) {
        List<GraphStudentResult> graphStudentResults = new ArrayList<>();
        Date to =  DateUtils.toDate(LocalDate.now().plusDays(1));
        Date from = DateUtils.oneMonthBack(to);

        List<StudentAnswer> studentAnswerList = studentAnswerRepository.findByUserAndAttendedOnBetween(user, from, to);
        for (LocalDate index = DateUtils.toLocalDate(from); !index.isEqual(DateUtils.toLocalDate(to)); index = index.plusDays(1)) {
            LocalDate finalIndex = index;
            graphStudentResults.add(GraphStudentResult.builder()
                    .date(index)
                    .correct(studentAnswerList.stream()
                            .filter(studentAnswer -> finalIndex.isEqual(DateUtils.toLocalDate(studentAnswer.getAttendedOn())))
                            .filter(StudentAnswer::isGotCorrect)
                            .count())
                    .wrong(studentAnswerList.stream()
                            .filter(studentAnswer -> finalIndex.isEqual(DateUtils.toLocalDate(studentAnswer.getAttendedOn())))
                            .filter(studentAnswer -> !studentAnswer.isGotCorrect())
                            .count())
                    .build());
        }


        return graphStudentResults.stream()
                .sorted(Comparator.comparing(GraphStudentResult::getDate))
                .collect(Collectors.toList());

    }

    public HashMap<String, Integer> spiderServiceMap(User user) {

        HashMap<String, Integer> radarMap = new HashMap<>();
        List<QuestionSolvingTime> questionSolvingTimesList = questionSolvingTimeRepository.findAllByActionEqualsAndUserId("ANSWERED", 3L);
        //start finding student speed
        int totalSolvingTime = 0;
        for (QuestionSolvingTime qst : questionSolvingTimesList) {
            totalSolvingTime += qst.getDuration();
        }
        int averageSolvingTime;
        try{
            averageSolvingTime = totalSolvingTime/questionSolvingTimesList.size();
        }
        catch (ArithmeticException ae){
            averageSolvingTime = 0;
        }
        int speedValue = 0;
        if (averageSolvingTime > 45)
            speedValue = 5;
        else if(averageSolvingTime >= 42 && averageSolvingTime <= 44)
            speedValue = 10;
        else if(averageSolvingTime >= 35 && averageSolvingTime <= 41)
            speedValue = 40;
        else if(averageSolvingTime >= 30 && averageSolvingTime <= 34)
            speedValue = 80;
        else if(averageSolvingTime < 30)
            speedValue = 100;
        //end finding student speed

        //start Accuracy- Correct answers from total questions attempted
        List<StudentAnswer> studentAnswerList = studentAnswerRepository.findAllByUser(user);
        int totalCorrectAns = 0;
        for (StudentAnswer studentAnswer : studentAnswerList) {
            if (studentAnswer.isGotCorrect())
                totalCorrectAns += 1;
        }
        int accuracyValue;
        try{
            accuracyValue = (totalCorrectAns*100)/studentAnswerList.size();
        }
        catch (ArithmeticException ae){
            accuracyValue = 0;
        }
        //End Accuracy- Correct answers from total questions attempted

/*
        start Effort - Number of total questions attempted from DBlet say there are total 1000 questions in the bank.
        You have got 20 correct, 30 wrong, 10Skipped. Efficiency=((20 +30 +10)/1000) * 100.
        In our bank we may have 1000 questions in english and 1000 in bangla. Total question in DB is 2000. But for effort we will use 1000
*/
        List<Question> questionList = questionRepository.findAllByVersionEquals(0);
        List<StudentAnswer> studentAnswerCorrectList = studentAnswerRepository.findAllByUserAndGotCorrectEquals(user,   true);
        List<StudentAnswer> studentAnswerWrongList = studentAnswerRepository.findAllByUserAndGotCorrectEquals(user,   false);
        List<QuestionSolvingTime> questionSolvingTimeSkippedList = questionSolvingTimeRepository.findAllByActionEqualsAndUserId("SKIPPED", 3L);
        int efficiencyValue;
        try{
            efficiencyValue = ((studentAnswerCorrectList.size()+studentAnswerWrongList.size()+questionSolvingTimeSkippedList.size())*100)/questionList.size();

        }
        catch (ArithmeticException ae){
            efficiencyValue = 0;
        }
        //end Effort

/*      Start Consistency- Daily access and questions answered
        Last 10 days record of answering questions (minimum 5 question needed to be counted)
        1/10- answered for 1 out of 10 last days (10%). In last 10 days, only 1 day the student solved more than or equal to 5 question.
        2/10- In last 10 days, only 2 days the student solved more than or equal to 5 question.
        10/10- answered for 10 out of 10 last days    (100%). Student solved more than or equal to 5 question in last 10 days
*/

        int consistencyValue = 0;
        int totalDailyAccess = 0;
        if (studentAnswerRepository.findAllByUserAndAttendedOnGreaterThanAndAttendedOnLessThanEqualAndGotCorrectEquals(user,
                DateUtils.daysBack10(new Date()), DateUtils.today(), true).size() >= 5){
            for(int i=1; i <=10; i++){
                Calendar cal = Calendar.getInstance();
                Calendar cal1 = Calendar.getInstance();
                cal.setTime(new Date());
                cal.set(Calendar.HOUR_OF_DAY, 23);
                cal.set(Calendar.MINUTE, 59);
                cal.set(Calendar.SECOND, 59);
                cal.add(Calendar.DAY_OF_MONTH, -(i+1));

                cal1.set(Calendar.HOUR_OF_DAY, 23);
                cal1.set(Calendar.MINUTE, 59);
                cal1.set(Calendar.SECOND, 59);
                cal1.add(Calendar.DAY_OF_MONTH, -i);
                List<StudentAnswer> studentAnswerDailyList = studentAnswerRepository.findAllByUserAndAttendedOnGreaterThanAndAttendedOnLessThanEqual(user, cal.getTime(), cal1.getTime());
                if(studentAnswerDailyList.size() > 0){
                    totalDailyAccess += 1;
                }
            }

        }
        consistencyValue = (totalDailyAccess*100)/10;

        //end Consistency value

        //Improvement- Change in accuracy over a given time period
        //Last 10 days/last 30 days (11th to 40th day) accuracy ratio
        //Calculate accuracy of last 10 days and last 30 days(11th to 40th day). Percentage increase is the improvement.
        //(Accuracy of 10 days - accuracy of 30 days)/accuracy of 30 days.  * 100
        List<StudentAnswer> studentAnswerForLast_10List = studentAnswerRepository.findAllByUserAndAttendedOnGreaterThanAndAttendedOnLessThanEqual(user,DateUtils.daysBack10(new Date()), DateUtils.today());
        List<StudentAnswer> studentAnswerCorrectForLast_10List = studentAnswerRepository.findAllByUserAndAttendedOnGreaterThanAndAttendedOnLessThanEqualAndGotCorrectEquals(user, DateUtils.daysBack10(new Date()), DateUtils.today(), true);
        List<StudentAnswer> studentAnswerForLast30Before10List  = studentAnswerRepository.findAllByUserAndAttendedOnGreaterThanAndAttendedOnLessThanEqual(user, DateUtils.backFortyFirstDate(), DateUtils.backEleventhDate());
        List<StudentAnswer> studentAnswerCorrectForLast30Before10List = studentAnswerRepository.findAllByUserAndAttendedOnGreaterThanAndAttendedOnLessThanEqualAndGotCorrectEquals(user, DateUtils.backFortyFirstDate(), DateUtils.backEleventhDate(), true);

        int improvementValue = 0;
        try {
            int accuracyFor_10_days = ((studentAnswerCorrectForLast_10List.size()*100)/studentAnswerForLast_10List.size());
            int accuracyFor_30_days = ((studentAnswerCorrectForLast30Before10List.size()*100)/studentAnswerForLast30Before10List.size());
            improvementValue = (((accuracyFor_10_days - accuracyFor_30_days)*100)/accuracyFor_30_days);
            if (improvementValue < 1)
                improvementValue = 0;

        }
        catch (ArithmeticException ae){
            improvementValue = 0;
        }


        radarMap.put("speed", speedValue);
        radarMap.put("accuracy", accuracyValue);
        radarMap.put("effort", efficiencyValue);
        radarMap.put("consistency", consistencyValue);
        radarMap.put("improvement", improvementValue);

        return radarMap;
    }
    // daily correct wrong
    public HashMap<Date, HashMap<String, Integer>> htmlTableMap(User user){
        HashMap<Date, HashMap<String, Integer>> outerMap = new HashMap<>();
        for(int i=1; i <= 15; i++){
            HashMap<String, Integer> percentageMap = new HashMap<>();
            Calendar cal = Calendar.getInstance();
            Calendar cal_1 = Calendar.getInstance();
            cal.setTime(new Date());
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.add(Calendar.DAY_OF_MONTH, -(i+1));

            cal_1.set(Calendar.HOUR_OF_DAY, 23);
            cal_1.set(Calendar.MINUTE, 59);
            cal_1.set(Calendar.SECOND, 59);
            cal_1.add(Calendar.DAY_OF_MONTH, -i);
            List<StudentAnswer> studentAnswerDailyList = studentAnswerRepository.findAllByUserAndAttendedOnGreaterThanAndAttendedOnLessThanEqual(user, cal.getTime(), cal_1.getTime());
            HashMap<String, List<Integer>> topicSubTWiseCorrWrongMap = new HashMap<>();
            for (StudentAnswer studentAnswer : studentAnswerDailyList) {
                for (SubTopic subTopic : studentAnswer.getQuestion().getSubTopics()) {
                    String topicSubTopicAsKey = studentAnswer.getQuestion().getTopic().getName().substring(0, 3).concat("-"+subTopic.getName());
                    int correct = 0;
                    int wrong = 0;
                    if (studentAnswer.isGotCorrect()){
                        correct = 1;
                    }
                    else{
                        wrong = 1;
                    }
                    List correctWrongList = topicSubTWiseCorrWrongMap.getOrDefault(topicSubTopicAsKey, new ArrayList());
                    if (correctWrongList.isEmpty()){
                        correctWrongList.add(0, correct);
                        correctWrongList.add(1, wrong);
                    }
                    else {
                        int tempCorrect = (int)correctWrongList.get(0);
                        int tempWrong = (int)correctWrongList.get(1);
                        correctWrongList.clear();
                        correctWrongList.add(0, tempCorrect+correct );
                        correctWrongList.add(1, tempWrong+wrong );
                    }
                    topicSubTWiseCorrWrongMap.put(topicSubTopicAsKey, correctWrongList);
                }
            }
            Iterator it = topicSubTWiseCorrWrongMap.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry pair = (Map.Entry)it.next();
                List<Integer> markspercentage = (List<Integer>) pair.getValue();
                percentageMap.put((String) pair.getKey(), (markspercentage.get(0)*100)/(markspercentage.get(0)+markspercentage.get(1)));
                outerMap.put(cal_1.getTime(), percentageMap);
            }
        }

        return outerMap;
    }


    public  HashMap<String, Integer> TopicSubtopicMarksMap(User user){
        HashMap<String, Integer> topicSubtopicMarks = new HashMap<>();
        HashMap<String, int[]> correctTotalSubTopicMap = new HashMap<>();
        List<StudentAnswer> studentAnswerList = studentAnswerRepository.findAllByUser(user);
        Map<String, List<Question>> questionSubMap = new HashMap<>();
        Map<String, HashSet<Long>> putCorrectQuestionIdOnceUnderSubTopic = new HashMap<>();
        for(Question question : questionRepository.findAll()){
            for(SubTopic subTopic : question.getSubTopics()){
                List<Question> questions = questionSubMap.getOrDefault(subTopic.getName(), new ArrayList<>());
                questions.add(question);
                questionSubMap.put(subTopic.getName(), questions);
            }
        }
        for (StudentAnswer studentAnswer : studentAnswerList){
            String topicName = studentAnswer.getQuestion().getTopic().getName();
            for (SubTopic subTopic : studentAnswer.getQuestion().getSubTopics()) {
                String topicSubStr, topicSubTopicAsKey;
                if (topicName.equals("Mathematics") || topicName.equals("Chemistry") || topicName.equals("Bengali"))
                    topicSubStr = topicName.substring(0, 4);
                else
                    topicSubStr = topicName.substring(0, 3);

                topicSubTopicAsKey = topicSubStr.concat("-"+subTopic.getName());

                int correct = 0;
                if (studentAnswer.isGotCorrect()){
                    if (putCorrectQuestionIdOnceUnderSubTopic.containsKey(topicSubTopicAsKey)){
                        int count = 0;
                        for (int i=0; i<putCorrectQuestionIdOnceUnderSubTopic.get(topicSubTopicAsKey).toArray().length; i++){
                            if (putCorrectQuestionIdOnceUnderSubTopic.get(topicSubTopicAsKey).toArray()[i] == studentAnswer.getQuestion().getId()){
                                correct = 0;
                                count = 1;
                            }
                        }
                        if (count == 0){
                            putCorrectQuestionIdOnceUnderSubTopic.get(topicSubTopicAsKey).add(studentAnswer.getQuestion().getId());
                            correct = 1;
                        }
                    }
                    else{
                        HashSet<Long> questionIdSet = new HashSet<>();
                        questionIdSet.add(studentAnswer.getQuestion().getId());
                        putCorrectQuestionIdOnceUnderSubTopic.put(topicSubTopicAsKey, questionIdSet);
                        correct = 1;
                    }
                }
                else{
                    correct = 0;
                }
                int[] correctTotalSubTopicMapValue = new int[2];
                if (correctTotalSubTopicMap.containsKey(topicSubTopicAsKey)){
                    correctTotalSubTopicMapValue[0] = correctTotalSubTopicMap.get(topicSubTopicAsKey)[0] + correct;
                    correctTotalSubTopicMapValue[1] = questionSubMap.get(subTopic.getName()).size();
                    correctTotalSubTopicMap.put(topicSubTopicAsKey, correctTotalSubTopicMapValue);
                }
                else{
                    correctTotalSubTopicMapValue[0] = correct;
                    correctTotalSubTopicMapValue[1] = questionSubMap.get(subTopic.getName()).size();
                    List<Question> q = questionSubMap.get(subTopic.getName());
                    correctTotalSubTopicMap.put(topicSubTopicAsKey, correctTotalSubTopicMapValue);
                }
            }
        }
        Iterator it = correctTotalSubTopicMap.entrySet().iterator();
        String key;
        int value;
        int[] valueArray = new int[2];
        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            key = pair.getKey().toString();
            valueArray = (int[])pair.getValue();
            value = (valueArray[0]*100)/valueArray[1];
            topicSubtopicMarks.put(key, value);
        }

        return  topicSubtopicMarks;
    }

    //sidebar
    public HashMap<String, Integer> TopicWiseMarksMap(User user){
        Map<String, List<Question>> questionTopicMap = new HashMap<>();
        Map<String, Integer> totalCorrectQuesUnderTopic = new HashMap<>();
        HashMap<String, Integer> topicWiseMarks = new HashMap<>();
        List<StudentAnswer> studentAnswerList = studentAnswerRepository.findAllByUser(user);
        for(Question question : questionRepository.findAll()){
            List<Question> questions = questionTopicMap.getOrDefault(question.getTopic().getName(), new ArrayList<>());
            questions.add(question);
            questionTopicMap.put(question.getTopic().getName(), questions);
        }
        for (StudentAnswer studentAnswer : studentAnswerList) {
            String topicName = studentAnswer.getQuestion().getTopic().getName();
            if (studentAnswer.isGotCorrect()){
                int correct = totalCorrectQuesUnderTopic.getOrDefault(topicName, 0);
                totalCorrectQuesUnderTopic.put(topicName, correct+1);
            }
        }
        Iterator it = totalCorrectQuesUnderTopic.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            topicWiseMarks.put(pair.getKey().toString(), ((int)pair.getValue()*100)/questionTopicMap.get(pair.getKey().toString()).size());
        }

        return  topicWiseMarks;
    }

}
