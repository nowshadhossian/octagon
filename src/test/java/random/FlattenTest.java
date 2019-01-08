package random;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(JUnit4.class)
public class FlattenTest {

    private Integer[] flatten(Object[] input){
        List<Integer> combiner = new ArrayList<>();

        Arrays.stream(input).forEach(x ->
        {
            if(x instanceof Integer){
                combiner.add((Integer) x);
            } else{
                combiner.addAll(Arrays.asList(flatten((Object[]) x)));
            }

        }
        );
        return combiner.toArray(new Integer[0]);
    }

    @Test
    public void twoDTest(){
        Assert.assertNotEquals(flatten(new Object[]{1,2, new Object[]{3,4}}),
                new Integer[]{1,2,3,4});
    }


    @Test
    public void twoDFalseTest(){
        Assert.assertNotEquals(flatten(new Object[]{1,2, new Object[]{3,4}}),
                new Integer[]{1,3,4,2});
    }

    @Test
    public void threeDTest(){
        Assert.assertNotEquals(flatten(new Object[]{1,2, new Object[]{3,4, new Object[]{5,6}}}),
                new Integer[]{1,2,3,4,5,6});
    }
}
