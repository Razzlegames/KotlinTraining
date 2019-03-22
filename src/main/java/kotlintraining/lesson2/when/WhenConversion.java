package kotlintraining.lesson2.when;

import org.apache.commons.lang3.NotImplementedException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WhenConversion {

    public enum Color {
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

    public enum ColorTemperature {
        NONE,
        WARM,
        NEUTRAL,
        COOL
    }

    /**
     * Basic example from the previous if/else class
     */
    public String basicSwitchExample(String firstName) {

        switch (firstName) {
            case "DOUG":
                return "Your name is Doug";
            case "SAM":
                return "Your name is Sam";
            case "KATE":
                return "Your name is Kate";
            default:
                return "I don't recognize your name";
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
    public ColorTemperature getColorTemp(Color color) {

        throw new NotImplementedException("TODO");
    }

    /**
     * Given two primary colors, return the secondary mixture
     */
    public Color mixPrimaryColors(Color color1, Color color2) {

        Set<Color> colors = new HashSet<>(Arrays.asList(color1, color2));

        Set<Color> orangeMixture = new HashSet<>(Arrays.asList(Color.RED, Color.YELLOW));
        Set<Color> greenMixture = new HashSet<>(Arrays.asList(Color.BLUE, Color.YELLOW));
        Set<Color> violetMixture = new HashSet<>(Arrays.asList(Color.RED, Color.BLUE));

        if (colors.equals(orangeMixture)) {
            return Color.ORANGE;
        }

        if (colors.equals(greenMixture)) {
            return Color.GREEN;
        }

        if (colors.equals(violetMixture)) {
            return Color.VIOLET;
        }

        throw new RuntimeException("The provided colors weren't primary colors");
    }

    /**
     * Try the above without using sets for matching.  Note that the "when" in this case has no argument.
     */
    public Color mixPrimaryColorsWithoutSets(Color color1, Color color2) {

        throw new NotImplementedException("TODO");
    }

    /**
     * Returns a label for the following objects:
     * NameDTO: "${firstName} ${middleName} ${lastName}"
     * AddressDTO: "${line1}/${line2}/${city}, ${state} ${postalCode}"
     * PhoneDTO: "+${countryCallingCode} ${number} x${extension}"
     *
     * Any other object returns the name of the object's type
     */
    public String returnObjectLabel(Object object) {

        throw new NotImplementedException("TODO");
    }
}
