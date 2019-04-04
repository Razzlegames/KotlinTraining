package kotlintraining.lesson2.optional

import kotlintraining.lesson2.dto.ExtendedUserInfoDTO
import kotlintraining.lesson2.dto.NationalIdDTO
import kotlintraining.lesson2.dto.PrimaryUserInfoDTO
import kotlintraining.lesson2.dto.UserInfoDTO
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class OptionalConversionTest {

    private val testSubject: OptionalConversion = OptionalConversion()

    @Test
    fun getUserEmail() {

        val userInfoDTO = UserInfoDTO(
            PrimaryUserInfoDTO(email = "myEmail")
        )

        val email = testSubject.getUserEmail(userInfoDTO)

        assertEquals("myEmail", email)
    }

    @Test
    fun getUserEmail_NotPresent() {

        val email = testSubject.getUserEmail(UserInfoDTO(PrimaryUserInfoDTO()))

        assertEquals("none", email)
    }

    @Test
    fun getUserEmail_Empty() {

        val email = testSubject.getUserEmail(UserInfoDTO(PrimaryUserInfoDTO()))

        assertEquals("none", email)
    }


    @Test
    fun hasEmail() {

        val userInfoDTO = UserInfoDTO(
            PrimaryUserInfoDTO(email = "myEmail")
        )

        assertTrue(testSubject.hasEmail(userInfoDTO))
    }

    @Test
    fun hasEmail_NotPresent() {

        val userInfoDTO = UserInfoDTO(
            PrimaryUserInfoDTO()
        )

        assertFalse(testSubject.hasEmail(userInfoDTO))
    }

    @Test
    fun hasEmail_Empty() {

        val userInfoDTO = UserInfoDTO(
            PrimaryUserInfoDTO(email = "")
        )

        assertFalse(testSubject.hasEmail(userInfoDTO))
    }

//    @Test
//    fun getBureauSsn9() {
//
//        val userInfoDTO = UserInfoDTO(
//            extendedUserInfo = ExtendedUserInfoDTO(
//                NationalIdDTO(id = "123456789")
//            )
//        )
//
//        assertEquals("123456789", testSubject.getBureauSsn9(userInfoDTO).get())
//    }
//
//    @Test
//    fun getBureauSsn9_Missing() {
//
//        val userInfoDTO = UserInfoDTO(
//            extendedUserInfo = ExtendedUserInfoDTO()
//        )
//
//        assertEquals(Optional.empty(), testSubject.getBureauSsn9(userInfoDTO))
//    }
//
//    @Test
//    fun getBureauSsn9_Blank() {
//
//        val userInfoDTO = UserInfoDTO(
//            extendedUserInfo = ExtendedUserInfoDTO(
//                NationalIdDTO(idShort = "1234", id = "")
//            )
//        )
//
//        assertEquals(Optional.empty(), testSubject.getBureauSsn9(userInfoDTO))
//    }

    @Test
    fun getUserSsn_BureauSSN9() {

        val userInfoDTO = getUserInfoWithSsn("bureauSsn9", "bureauSsn4", "userSsn9", "userSsn4")

        assertEquals("bureauSSN9", testSubject.getUserSsn(userInfoDTO))
    }

    @Test
    fun getUserSsn_BureauSSN4() {

        val userInfoDTO = getUserInfoWithSsn("", "bureauSsn4", "userSsn9", "userSsn4")

        assertEquals("bureauSsn4", testSubject.getUserSsn(userInfoDTO))
    }

    @Test
    fun getUserSsn_UserSsn9() {

        val userInfoDTO = getUserInfoWithSsn("", "bureauSsn4", "userSsn9", "userSsn4")

        assertEquals("bureauSsn4", testSubject.getUserSsn(userInfoDTO))
    }

    private fun getUserInfoWithSsn(bureauSsn9: String?, bureauSsn4: String?, userSsn9: String?, userSsn4: String?): UserInfoDTO {

        return UserInfoDTO(
            PrimaryUserInfoDTO(
                nationalId = NationalIdDTO(bureauSsn4, bureauSsn9)
            ),
            ExtendedUserInfoDTO(
                NationalIdDTO(userSsn4, userSsn9)
            )
        )
    }
}