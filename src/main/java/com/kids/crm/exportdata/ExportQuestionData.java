package com.kids.crm.exportdata;

import com.kids.crm.model.*;
import com.kids.crm.repository.QuestionRepository;
import com.kids.crm.repository.SessionRepository;
import com.kids.crm.repository.SubTopicRepository;
import com.kids.crm.repository.SubjectRepository;
import com.kids.crm.service.SessionService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Component
public class ExportQuestionData {
    private static final String FILE_NAME = "/Users/nowshad/Downloads/octa-images-exam/Question_Information.xlsx";

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    SubTopicRepository subTopicRepository;

    @Autowired
    SessionService sessionService;

    public List<Question> readQuestionExcel() {
        FileInputStream excelFile = null;
        List<Question> questionList = new ArrayList<>();
        try {
            excelFile = new FileInputStream(new File(FILE_NAME));
            XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                if (currentRow.getRowNum() == 0) {
                    continue;
                }
                Question question = Question.builder().build();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();
                    int columnIndex = currentCell.getColumnIndex();
                    switch (columnIndex) {
                        case 0:
                            question.setFileName((String) getCellValue(currentCell)+".png");
                            break;
                        case 1:
                            question.setAnswer((String) getCellValue(currentCell));
                            break;
                        case 2:
                            question.setYear(new Double((double)getCellValue(currentCell)).intValue());
                            break;
                        case 3:
                            question.setSession(sessionService.findOrCreateSessionByNameAndYear((String) getCellValue(currentCell), question.getYear()));
                            break;
                        case 4:
                            question.setPaper(new Double((double)getCellValue(currentCell)).intValue());
                            break;

                        case 5:
                            question.setQuestionNo(new Double((double)getCellValue(currentCell)).intValue());
                            break;

                        case 6:
                            question.setTopic(Topic.builder().name((String) getCellValue(currentCell)).build());
                            break;

                        case 7:
                            question.setSubTopics(findOrCreateSubTopic((String) getCellValue(currentCell)));
                            break;
                    }
                }
                questionList.add(question);

            }
            System.out.println(questionList.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return questionList;
    }

    private static Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();

            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();

            case Cell.CELL_TYPE_NUMERIC:
                return cell.getNumericCellValue();
        }

        return null;
    }

    public Subject findOrCreateSubject(String name) {
        Subject subject = subjectRepository.findByName(name);
        if (subject != null) {
            return subject;
        } else {
            return subjectRepository.save(Subject.builder().name(name).code("3333").build());
        }
    }



    public Set<SubTopic> findOrCreateSubTopic(String commaSeparatedSubTopic) {
        Set<SubTopic> subTopics = new HashSet<>();
        for (String subTopicStr : commaSeparatedSubTopic.split(",")) {
            subTopicRepository.findByName(subTopicStr.trim())
                    .ifPresentOrElse(subTopics::add,
                            () -> subTopics.add(subTopicRepository.save(SubTopic.builder().name(subTopicStr.trim()).build())
                            ));
        }
        return subTopics;
    }
}
