package kotlintraining.lesson2.optional;

import kotlintraining.lesson2.dto.ExtendedUserInfoDTO;
import kotlintraining.lesson2.dto.NationalIdDTO;
import kotlintraining.lesson2.dto.PrimaryUserInfoDTO;
import kotlintraining.lesson2.dto.UserInfoDTO;

import java.util.Optional;

public class OptionalConversion {

    /**
     * Return the user email if present, "none" if not
     */
    public String getUserEmail(UserInfoDTO userInfoDTO) {

        return Optional.ofNullable(userInfoDTO)
            .map(UserInfoDTO::getPrimaryUserInfo)
            .map(PrimaryUserInfoDTO::getEmail)
            .filter(email -> !email.isEmpty())
            .orElse("none");
    }

    /**
     * Return whether or not the user has an email
     */
    public boolean hasEmail(UserInfoDTO userInfoDTO) {
        // TODO: return whether or not the user has an email address
        throw new UnsupportedOperationException();
    }

    /**
     * Return an optional containing the user's bureau SSN9
     */
    public Optional<String> getBureauSsn9(UserInfoDTO userInfoDTO) {

        return Optional.ofNullable(userInfoDTO)
            .map(UserInfoDTO::getExtendedUserInfo)
            .map(ExtendedUserInfoDTO::getBureauProvidedNationalId)
            .map(NationalIdDTO::getId)
            .filter(id -> !id.isEmpty());
    }

    // Advanced

    /**
     * Return the user SSN by the following priority:
     *
     * - Bureau SSN9
     * - Bureau SSN4
     * - User SSN9
     * - User SSN4
     *
     * Any of these should be skipped if they are an null or empty string.
     */
    public String getUserSsn(UserInfoDTO userInfoDTO) {
        throw new UnsupportedOperationException();
    }
}
