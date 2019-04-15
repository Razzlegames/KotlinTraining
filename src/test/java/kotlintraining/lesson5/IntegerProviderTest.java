package kotlintraining.lesson5;

import org.junit.Assert;
import org.junit.Test;

public class IntegerProviderTest {

    @Test
    public void getInteger() {

        IntegerService integerProvider = new IntegerService();

        Assert.assertEquals(100, integerProvider.getInteger(10));
    }
}