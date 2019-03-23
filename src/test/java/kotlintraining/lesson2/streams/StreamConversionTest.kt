package kotlintraining.lesson2.streams

import io.mockk.every
import io.mockk.mockk
import kotlintraining.lesson2.dto.*
import org.junit.Test
import java.lang.RuntimeException
import java.time.LocalDate
import java.time.Month
import kotlin.test.*

class StreamConversionTest {

    private val testSubject = StreamConversion()

    @Test
    fun getAllHomePhones() {

        val phoneList = listOf(
            PhoneDTO(type = PhoneType.BUSINESS, number = "business1"),
            PhoneDTO(type = PhoneType.HOME, number = "home1"),
            PhoneDTO(type = PhoneType.BUSINESS, number = "business2"),
            PhoneDTO(type = PhoneType.MOBILE, number = "mobile1"),
            PhoneDTO(type = PhoneType.HOME, number = "home2"),
            PhoneDTO(type = PhoneType.BUSINESS, number = "business3"),
            PhoneDTO(type = PhoneType.MOBILE, number = "mobile2"),
            PhoneDTO(type = PhoneType.HOME, number = "home3"),
            PhoneDTO(type = PhoneType.MOBILE, number = "mobile3"),
            PhoneDTO(type = PhoneType.BUSINESS, number = "business4")
        )

        // hacky assertion to avoid giving away the answer :)
        val homePhones = testSubject.getAllHomePhones(phoneList)

        assertEquals("home1", homePhones[0].number)
        assertEquals("home2", homePhones[1].number)
        assertEquals("home3", homePhones[2].number)
    }

    @Test
    fun getAllHomePhones_BadInput() {

        assertEquals(emptyList(), testSubject.getAllHomePhones(null))
        assertEquals(emptyList(), testSubject.getAllHomePhones(emptyList()))
    }

    @Test
    fun getFirstResidentialAddress() {
        val addresses = listOf(
            AddressDTO(addressType = null),
            AddressDTO(addressType = AddressType.BUSINESS),
            AddressDTO(addressType = AddressType.RESIDENTIAL),
            AddressDTO(addressType = AddressType.BUSINESS)
        )

        assertSame(addresses[2], testSubject.getFirstResidentialAddress(UserInfoDTO(PrimaryUserInfoDTO(addresses = addresses))))
    }

    @Test
    fun getFirstResidentialAddress_BadInput() {

        assertNull(testSubject.getFirstResidentialAddress(null))
        assertNull(testSubject.getFirstResidentialAddress(UserInfoDTO()))
        assertNull(testSubject.getFirstResidentialAddress(UserInfoDTO(PrimaryUserInfoDTO())))
        assertNull(testSubject.getFirstResidentialAddress(UserInfoDTO(PrimaryUserInfoDTO(addresses = emptyList()))))
        assertNull(testSubject.getFirstResidentialAddress(UserInfoDTO(PrimaryUserInfoDTO(addresses = listOf(AddressDTO())))))
        assertNull(testSubject.getFirstResidentialAddress(UserInfoDTO(PrimaryUserInfoDTO(addresses = listOf(AddressDTO(AddressType.BUSINESS))))))
    }

    @Test
    fun containsArizonaResident() {

        val users = listOf(
            UserInfoDTO(PrimaryUserInfoDTO(addresses = getListOfAddressesForUser(listOf(Pair(AddressType.BUSINESS, "PA"), Pair(AddressType.RESIDENTIAL, "CA"))))),
            UserInfoDTO(PrimaryUserInfoDTO(addresses = getListOfAddressesForUser(listOf(Pair(AddressType.RESIDENTIAL, "PA"), Pair(AddressType.RESIDENTIAL, "CO"))))),
            UserInfoDTO(PrimaryUserInfoDTO(addresses = getListOfAddressesForUser(listOf(Pair(AddressType.BUSINESS, "AZ"), Pair(AddressType.RESIDENTIAL, "CA"))))),
            UserInfoDTO(PrimaryUserInfoDTO(addresses = getListOfAddressesForUser(listOf(Pair(AddressType.BUSINESS, "PA"), Pair(AddressType.RESIDENTIAL, "AZ")))))
        )

        assertTrue(testSubject.containsArizonaResident(users))
        assertFalse(testSubject.containsArizonaResident(users.subList(0, 2)))
    }

    @Test
    fun containsArizonaResident_ExceptionThrowingResidents() {

        val mockAddressDTO = mockk<AddressDTO>()

        every { mockAddressDTO.state } throws RuntimeException("shouldn't be accessing this")
        every { mockAddressDTO.addressType } throws RuntimeException("shouldn't be accessing this")

        val users = listOf(
            UserInfoDTO(PrimaryUserInfoDTO(addresses = getListOfAddressesForUser(listOf(Pair(AddressType.BUSINESS, "PA"), Pair(AddressType.RESIDENTIAL, "CA"))))),
            UserInfoDTO(PrimaryUserInfoDTO(addresses = getListOfAddressesForUser(listOf(Pair(AddressType.RESIDENTIAL, "AZ"), Pair(AddressType.RESIDENTIAL, "CO"))))),
            UserInfoDTO(PrimaryUserInfoDTO(addresses = listOf(mockAddressDTO))),
            UserInfoDTO(PrimaryUserInfoDTO(addresses = getListOfAddressesForUser(listOf(Pair(AddressType.BUSINESS, "PA"), Pair(AddressType.RESIDENTIAL, "AZ")))))
        )

        assertTrue(testSubject.containsArizonaResident(users))
    }

    @Test
    fun containsArizonaResident_BadInput() {

        assertFalse(testSubject.containsArizonaResident(null))
        assertFalse(testSubject.containsArizonaResident(emptyList()))
        assertFalse(testSubject.containsArizonaResident(listOf(UserInfoDTO())))
        assertFalse(testSubject.containsArizonaResident(listOf(UserInfoDTO(PrimaryUserInfoDTO()))))
        assertFalse(testSubject.containsArizonaResident(listOf(UserInfoDTO(PrimaryUserInfoDTO(addresses = emptyList())))))
        assertFalse(testSubject.containsArizonaResident(listOf(UserInfoDTO(PrimaryUserInfoDTO(addresses = listOf(AddressDTO()))))))
        assertFalse(testSubject.containsArizonaResident(listOf(UserInfoDTO(PrimaryUserInfoDTO(addresses = listOf(AddressDTO(state = "AZ")))))))
        assertFalse(testSubject.containsArizonaResident(listOf(UserInfoDTO(PrimaryUserInfoDTO(addresses = listOf(AddressDTO(addressType = AddressType.RESIDENTIAL)))))))
    }

    @Test
    fun getOldestPersonLastName() {

        val users = listOf(
            UserInfoDTO(PrimaryUserInfoDTO(dateOfBirth = LocalDate.of(1945, Month.APRIL, 4), name = NameDTO(lastName = "Wilson", firstName = "John"))),
            UserInfoDTO(PrimaryUserInfoDTO(dateOfBirth = LocalDate.of(1956, Month.JANUARY, 7), name = NameDTO(lastName = "Kimble", firstName = "Art"))),
            UserInfoDTO(PrimaryUserInfoDTO(dateOfBirth = LocalDate.of(2000, Month.NOVEMBER, 14), name = NameDTO(lastName = "Perez", firstName = "Maria"))),
            UserInfoDTO(PrimaryUserInfoDTO(dateOfBirth = LocalDate.of(2018, Month.DECEMBER, 24), name = NameDTO(lastName = "Franklin", firstName = "Jimbo"))),
            UserInfoDTO(PrimaryUserInfoDTO(dateOfBirth = LocalDate.of(1945, Month.APRIL, 3), name = NameDTO(lastName = "Fremont", firstName = "Clarissa")))
        )

        assertEquals("Fremont", testSubject.getOldestPersonLastName(users))
    }

    @Test
    fun getOldestPersonLastName_BadInput() {

        assertNull(testSubject.getOldestPersonLastName(null))
        assertNull(testSubject.getOldestPersonLastName(emptyList()))
        assertNull(testSubject.getOldestPersonLastName(listOf(UserInfoDTO())))
        assertNull(testSubject.getOldestPersonLastName(listOf(UserInfoDTO(PrimaryUserInfoDTO(name = NameDTO(lastName = "A", firstName = "B"))))))
        assertNull(testSubject.getOldestPersonLastName(listOf(UserInfoDTO(PrimaryUserInfoDTO(dateOfBirth = LocalDate.of(1977, Month.SEPTEMBER, 22))))))
    }

    @Test
    fun getAllPhoneNumbers() {

        val users = listOf(
            UserInfoDTO(PrimaryUserInfoDTO(phones = listOf(PhoneDTO(number = "12345"), PhoneDTO("449394")))),
            UserInfoDTO(PrimaryUserInfoDTO(phones = listOf(PhoneDTO(number = "123456"), PhoneDTO("293982")))),
            UserInfoDTO(PrimaryUserInfoDTO(phones = listOf(PhoneDTO(number = "12345"), PhoneDTO(extension = "4444", type = PhoneType.MOBILE)))),
            UserInfoDTO(PrimaryUserInfoDTO()),
            UserInfoDTO(PrimaryUserInfoDTO(phones = listOf(PhoneDTO(number = "54321"), PhoneDTO("293982"))))
        )

        // hacky assert to avoid giving away the answer
        assertEquals(setOf("12345", "449394", "123456", "293982", "54321", "293982"), testSubject.getAllPhoneNumbers(users))
    }

    @Test
    fun getAllPhoneNumbers_BadInput() {

        assertEquals(emptySet(), testSubject.getAllPhoneNumbers(null))
        assertEquals(emptySet(), testSubject.getAllPhoneNumbers(emptyList()))
        assertEquals(emptySet(), testSubject.getAllPhoneNumbers(listOf(UserInfoDTO())))
        assertEquals(emptySet(), testSubject.getAllPhoneNumbers(listOf(UserInfoDTO(PrimaryUserInfoDTO()))))
        assertEquals(emptySet(), testSubject.getAllPhoneNumbers(listOf(UserInfoDTO(PrimaryUserInfoDTO(phones = emptyList())))))
        assertEquals(emptySet(), testSubject.getAllPhoneNumbers(listOf(UserInfoDTO(PrimaryUserInfoDTO(phones = listOf(PhoneDTO()))))))
        assertEquals(emptySet(), testSubject.getAllPhoneNumbers(listOf(UserInfoDTO(PrimaryUserInfoDTO(phones = listOf(PhoneDTO(extension = "1", type = PhoneType.MOBILE, countryCallingCode = "1")))))))
    }

    private fun getListOfAddressesForUser(stateAndTypes: List<Pair<AddressType, String>>): List<AddressDTO> {

        return stateAndTypes
            .map { AddressDTO(addressType = it.first, state = it.second) }
    }
}