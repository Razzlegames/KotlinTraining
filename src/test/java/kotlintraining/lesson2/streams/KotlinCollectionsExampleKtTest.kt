package kotlintraining.lesson2.streams

import kotlintraining.lesson2.dto.AddressDTO
import kotlintraining.lesson2.dto.AddressType
import kotlintraining.lesson2.dto.PrimaryUserInfoDTO
import kotlintraining.lesson2.dto.UserInfoDTO
import kotlin.test.Test
import kotlin.test.assertTrue

class KotlinCollectionsExampleKtTest {

    @Test
    fun testContainsArizonaResident() {

        val userInfoList = (0 .. 10)
            .toList()
            .map { UserInfoDTO(primaryUserInfo = PrimaryUserInfoDTO()
                .apply { addresses = listOf(AddressDTO()
                    .apply { addressType = AddressType.RESIDENTIAL
                    state = "AZ"})
                })
            }

        assertTrue(containsArizonaResident(userInfoList))
    }
}