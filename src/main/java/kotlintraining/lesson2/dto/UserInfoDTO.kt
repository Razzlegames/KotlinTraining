package kotlintraining.lesson2.dto

import java.time.LocalDate

data class UserInfoDTO(
    var primaryUserInfo: PrimaryUserInfoDTO? = null,
    var extendedUserInfo: ExtendedUserInfoDTO? = null
)

data class PrimaryUserInfoDTO(
    var name: NameDTO? = null,
    var addresses: List<AddressDTO> = emptyList(),
    var phones: List<PhoneDTO>? = null,
    var email: String? = null,
    var dateOfBirth: LocalDate? = null,
    var nationalId: NationalIdDTO? = null
)

data class NameDTO(
    var firstName: String,
    var middleName: String? = null,
    var middleInitial: String? = null,
    var lastName: String,
    var title: String? = null,
    var suffix: String? = null
)

data class AddressDTO(
    var addressType: AddressType? = null,
    var addressLine1: String? = null,
    var addressLine2: String? = null,
    var city: String? = null,
    var state: String? = null,
    var postalCode: String? = null,
    var countryCode: String? = null
)

data class PhoneDTO(
    var number: String? = null,
    var extension: String? = null,
    var countryCallingCode: String? = null,
    var type: PhoneType? = null
)

data class ExtendedUserInfoDTO(
    var bureauProvidedNationalId: NationalIdDTO? = null
)

data class NationalIdDTO(
    var idShort: String? = null,
    var id: String? = null
)

enum class PhoneType {
    HOME,
    MOBILE,
    BUSINESS
}

enum class AddressType {
    RESIDENTIAL,
    BUSINESS
}
