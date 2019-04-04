package kotlintraining.lesson2.`when`

import kotlintraining.lesson2.dto.NameDTO
import org.apache.commons.lang3.NotImplementedException

import java.util.Arrays
import java.util.HashSet

class WhenConversion {

    enum class Color {
        RED,
        ORANGE,
        YELLOW,
        GREEN,
        BLUE,
        INDIGO,
        VIOLET,
        WHITE,
        BLACK
    }

    enum class ColorTemperature {
        NONE,
        WARM,
        NEUTRAL,
        COOL
    }

    /**
     * Basic example from the previous if/else class
     */
    fun basicSwitchExample(firstName: String): String {

        when (firstName) {
            "DOUG" -> return "Your name is Doug"
            "SAM" -> return "Your name is Sam"
            "KATE" -> return "Your name is Kate"
            else -> return "I don't recognize your name"
        }
    }

    /**
     * Return color temperature based on the following conversion:
     *
     * Red, Orange, Yellow = WARM
     * Green = NEUTRAL
     * Blue, Indigo, Violet = COLD
     * Black, White = NONE
     */
    fun getColorTemp(color: Color): ColorTemperature {

        throw NotImplementedException("TODO")
    }

    /**
     * Given two primary colors, return the secondary mixture
     */
    fun mixPrimaryColors(color1: Color, color2: Color): Color {

        when (setOf(color1, color2)) {
            setOf(Color.RED, Color.YELLOW) -> return Color.ORANGE
            setOf(Color.BLUE, Color.YELLOW) -> return Color.GREEN
            setOf(Color.RED, Color.BLUE) -> return Color.VIOLET
            else -> throw RuntimeException("The provided colors weren't primary colors")
        }

//        val colors = HashSet(Arrays.asList(color1, color2))
//
//        val orangeMixture = HashSet(Arrays.asList(Color.RED, Color.YELLOW))
//        val greenMixture = HashSet(Arrays.asList(Color.BLUE, Color.YELLOW))
//        val violetMixture = HashSet(Arrays.asList(Color.RED, Color.BLUE))
//
//        if (colors == orangeMixture) {
//            return Color.ORANGE
//        }
//
//        if (colors == greenMixture) {
//            return Color.GREEN
//        }
//
//        if (colors == violetMixture) {
//            return Color.VIOLET
//        }
//
//        throw RuntimeException("The provided colors weren't primary colors")
    }

    /**
     * Try the above without using sets for matching.  Note that the "when" in this case has no argument.
     */
    fun mixPrimaryColorsWithoutSets(color1: Color, color2: Color): Color {

        throw NotImplementedException("TODO")
    }

    /**
     * Returns a label for the following objects:
     * NameDTO: "${firstName} ${middleName} ${lastName}"
     * AddressDTO: "${line1}/${line2}/${city}, ${state} ${postalCode}"
     * PhoneDTO: "+${countryCallingCode} ${number} x${extension}"
     *
     * Any other object returns the name of the object's type
     */
    fun returnObjectLabel(dto: Any): String {

        return when (dto) {
            dto as NameDTO -> {"${dto.firstName} ${dto.middleName} ${dto.lastName}"}
            else -> throw NotImplementedException("TODO")
        }
    }
}
