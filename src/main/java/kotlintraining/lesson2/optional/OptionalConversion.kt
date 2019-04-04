package kotlintraining.lesson2.optional

import kotlintraining.lesson2.dto.ExtendedUserInfoDTO
import kotlintraining.lesson2.dto.NationalIdDTO
import kotlintraining.lesson2.dto.PrimaryUserInfoDTO
import kotlintraining.lesson2.dto.UserInfoDTO

import java.util.Optional

class OptionalConversion {

    /**
     * Return the user email if present, "none" if not
     */
    fun getUserEmail(userInfoDTO: UserInfoDTO?): String? {

        return if( userInfoDTO?.primaryUserInfo?.email?.isEmpty() ?: true) {
            "none"
        } else {
            userInfoDTO?.primaryUserInfo?.email
        }
//        Optional.ofNullable(userInfoDTO)
//                .map<PrimaryUserInfoDTO>(Function<UserInfoDTO, PrimaryUserInfoDTO> { it.getPrimaryUserInfo() })
//                .map<String>(Function<PrimaryUserInfoDTO, String> { it.getEmail() })
//                .filter { email -> !email.isEmpty() }
//                .orElse("none")
    }

    /**
     * Return whether or not the user has an email
     */
    fun hasEmail(userInfoDTO: UserInfoDTO): Boolean {
        // TODO: return whether or not the user has an email address
        throw UnsupportedOperationException()
    }

    /**
     * Return an optional containing the user's bureau SSN9
     */
//    fun getBureauSsn9(userInfoDTO: UserInfoDTO?): String? {
//
//        //val lambda = userInfoDTO
//        return Optional.ofNullable(userInfoDTO)
//                .map<ExtendedUserInfoDTO>(Function<UserInfoDTO, ExtendedUserInfoDTO> { it.getExtendedUserInfo() })
//                .map<NationalIdDTO>(Function<ExtendedUserInfoDTO, NationalIdDTO> { it.getBureauProvidedNationalId() })
//                .map<String>(Function<NationalIdDTO, String> { it.getId() })
//                .filter { id -> !id.isEmpty() }
//    }

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
    fun getUserSsn(userInfoDTO: UserInfoDTO): String {
        throw UnsupportedOperationException()
    }
}
