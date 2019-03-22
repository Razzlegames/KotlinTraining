// TODO: Note the package name
package kotlintraining.lesson2.`when`

import org.junit.Test
import kotlin.test.assertEquals

// TODO: Note the alias
import kotlintraining.lesson2.`when`.WhenConversion.ColorTemperature as ColorTemp
import kotlintraining.lesson2.`when`.WhenConversion.Color
import kotlintraining.lesson2.dto.*
import java.lang.RuntimeException
import kotlin.test.assertFailsWith
import java.awt.Color as OtherColor

class WhenConversionTest {

    private val testSubject: WhenConversion = WhenConversion()

    @Test
    fun getColorTemp() {
        var anotherColorTYpe: OtherColor

        assertEquals(ColorTemp.WARM, testSubject.getColorTemp(Color.RED))
        assertEquals(ColorTemp.WARM, testSubject.getColorTemp(Color.ORANGE))
        assertEquals(ColorTemp.WARM, testSubject.getColorTemp(Color.YELLOW))
        assertEquals(ColorTemp.NEUTRAL, testSubject.getColorTemp(Color.GREEN))
        assertEquals(ColorTemp.COOL, testSubject.getColorTemp(Color.INDIGO))
        assertEquals(ColorTemp.COOL, testSubject.getColorTemp(Color.BLUE))
        assertEquals(ColorTemp.COOL, testSubject.getColorTemp(Color.VIOLET))
        assertEquals(ColorTemp.NONE, testSubject.getColorTemp(Color.BLACK))
        assertEquals(ColorTemp.NONE, testSubject.getColorTemp(Color.WHITE))
    }

    @Test
    fun mixPrimaryColorsWithoutSets() {

        assertEquals(Color.ORANGE, testSubject.mixPrimaryColorsWithoutSets(Color.RED, Color.YELLOW))
        assertEquals(Color.ORANGE, testSubject.mixPrimaryColorsWithoutSets(Color.YELLOW, Color.RED))

        assertEquals(Color.GREEN, testSubject.mixPrimaryColorsWithoutSets(Color.BLUE, Color.YELLOW))
        assertEquals(Color.GREEN, testSubject.mixPrimaryColorsWithoutSets(Color.YELLOW, Color.BLUE))

        assertEquals(Color.VIOLET, testSubject.mixPrimaryColorsWithoutSets(Color.RED, Color.BLUE))
        assertEquals(Color.VIOLET, testSubject.mixPrimaryColorsWithoutSets(Color.BLUE, Color.RED))

        assertFailsWith(RuntimeException::class) {
            testSubject.mixPrimaryColorsWithoutSets(Color.YELLOW, Color.ORANGE)
        }
    }

    @Test
    fun returnObjectLabel_AddressDTO() {

        val address = AddressDTO(
            line1 = "125 N MAIN ST",
            line2 = "SUITE 223",
            city = "Springerville",
            state = "IN",
            postalCode = "45223"
        )

        assertEquals("${address.line1}/${address.line2}/${address.city}, ${address.state} ${address.postalCode}", testSubject.returnObjectLabel(address))
    }

    @Test
    fun returnObjectLabel_NameDTO() {

        val name = NameDTO(
            firstName = "Michael",
            middleName = "B",
            lastName = "Jordan",
            title = "III"
        )

        assertEquals("${name.firstName} ${name.middleName} ${name.lastName}", testSubject.returnObjectLabel(name))
    }

    @Test
    fun returnObjectLabel_PhoneDTO() {

        val phone = PhoneDTO(
            number = "123456789",
            countryCallingCode = "1",
            extension = "2342",
            type = PhoneType.BUSINESS
        )

        assertEquals("+${phone.countryCallingCode} ${phone.number} x${phone.extension}", testSubject.returnObjectLabel(phone))
    }

    @Test
    fun returnObjectLabel_OtherDTO() {

        val userInfo = UserInfoDTO()

        assertEquals(userInfo.javaClass.name, testSubject.returnObjectLabel(userInfo))
    }


}