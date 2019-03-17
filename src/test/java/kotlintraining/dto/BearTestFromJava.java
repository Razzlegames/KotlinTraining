package kotlintraining.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BearTestFromJava {

    @Test
    public void testSet() {

        Bear bear = new Bear();

        // Getter / setter created for all non private fields
        assertNotNull(bear.getBack());
        assertNotNull(bear.getWeight());

        bear.setWeight("100");
        assertEquals("100 lbs", bear.getWeight());
    }
}
