package random;

import com.kids.crm.utils.Utils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class AnswerMatchedTest {

    @Test
    public void checkTwoArrayAreEqual(){
        String selected = " A,    D ";
        String answer = "A,D";
        Assert.assertTrue(Utils.answerMatched(selected, answer));
    }

    @Test
    public void checkTwoArrayAreEqual2(){
        String selected = "A,D";
        String answer = "D,A";
        Assert.assertTrue(Utils.answerMatched(selected, answer));
    }

    @Test
    public void checkTwoArrayAreEqual3(){
        String selected = "A, D";
        String answer = "A,d";
        Assert.assertTrue(Utils.answerMatched(selected, answer));
    }

    @Test
    public void checkTwoArrayAreEqual4(){
        String selected = "A,D";
        String answer = "A,D";
        Assert.assertTrue(Utils.answerMatched(selected, answer));
    }

    @Test
    public void checkTwoArrayAreEqual5(){
        String selected = "D";
        String answer = "D";
        Assert.assertTrue(Utils.answerMatched(selected, answer));
    }


    @Test
    public void checkTwoArrayAreEqual6(){
        String selected = " D";
        String answer = "D";
        Assert.assertTrue(Utils.answerMatched(selected, answer));
    }

    @Test
    public void isNotEqual(){
        String selected = "A,E";
        String answer = "A,D";
        Assert.assertFalse(Utils.answerMatched(selected, answer));
    }

    @Test
    public void isNotEqual2(){
        String selected = "A,E";
        String answer = "A";
        Assert.assertFalse(Utils.answerMatched(selected, answer));
    }

    @Test
    public void isNotEqual3(){
        String selected = "A";
        String answer = "B";
        Assert.assertFalse(Utils.answerMatched(selected, answer));
    }
}
