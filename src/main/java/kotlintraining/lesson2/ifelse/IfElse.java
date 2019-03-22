package kotlintraining.lesson2.ifelse;

import kotlintraining.lesson2.dto.NameDTO;
import org.apache.commons.lang3.StringUtils;

public class IfElse {

    /**
     * Returns the middle name if present and the middle initial if not
     */
    public String getMiddleNameOrInitial(NameDTO nameDTO) {

        if (StringUtils.isNotBlank(nameDTO.getMiddleName())) {
            return nameDTO.getMiddleName();
        }

        return nameDTO.getMiddleInitial();
    }

    /**
     * If the middle name provided is a single character, append a period.  Otherwise return the value.
     */
    public String appendMiddleInitialPeriod(String middleName) {

        return StringUtils.length(middleName) == 1 ? middleName + "." : middleName;
    }

    public String complexIfElse(String firstName) {

        if (firstName.equals("DOUG")) {
            return "Your name is Doug";
        }
        else if (firstName.equals("SAM")) {
            return "Your name is Sam";
        }
        else if (firstName.equals("KATE")) {
            return "Your name is Kate";
        }
        else {
            return "I don't recognize your name";
        }

    }
}
