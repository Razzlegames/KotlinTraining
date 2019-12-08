package kotlintraining.lesson2.streams

import kotlintraining.lesson2.dto.AddressType
import kotlintraining.lesson2.dto.UserInfoDTO

fun containsArizonaResident(userInfoDTO: List<UserInfoDTO>): Boolean {

    return userInfoDTO
        .asSequence()
        .map { it.primaryUserInfo }
        .filterNotNull()
        .flatMap { it.addresses.asSequence() }
        .filter { it.addressType == AddressType.RESIDENTIAL }
        .map { it.state }
        .any { "AZ" == it }
}