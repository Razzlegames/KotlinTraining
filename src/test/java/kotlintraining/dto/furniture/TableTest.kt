package kotlintraining.dto.furniture

import org.junit.Assert
import org.junit.Test

class TableTest {

    @Test
    fun tableBuilderTest() {

        // Optional constructor parameter color is set to default value, of "White"
        val table = Table(length = 10.0f, height = 10.0f, width = 5.0f)
        Assert.assertEquals("White", table.color)

    }
}