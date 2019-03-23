package kotlintraining.lesson2.streams;

import kotlintraining.lesson2.dto.AddressDTO;
import kotlintraining.lesson2.dto.AddressType;
import kotlintraining.lesson2.dto.PhoneDTO;
import kotlintraining.lesson2.dto.PhoneType;
import kotlintraining.lesson2.dto.PrimaryUserInfoDTO;
import kotlintraining.lesson2.dto.UserInfoDTO;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamConversion {

    /**
     * Return all phones in the list with type "HOME"
     */
    public List<PhoneDTO> getAllHomePhones(List<PhoneDTO> phones) {

        if (phones == null) {
            return Collections.emptyList();
        }

        return phones.stream()
            .filter(phone -> PhoneType.HOME.equals(phone.getType()))
            .collect(Collectors.toList());
    }

    /**
     * Get the first address in the list that's a residential address
     */
    public AddressDTO getFirstResidentialAddress(UserInfoDTO userInfoDTO) {

        return Optional.ofNullable(userInfoDTO)
            .map(UserInfoDTO::getPrimaryUserInfo)
            .map(PrimaryUserInfoDTO::getAddresses)
            .orElse(Collections.emptyList())
            .stream()
            .filter(addressDTO -> AddressType.RESIDENTIAL.equals(addressDTO.getAddressType()))
            .findFirst()
            .orElse(null);
    }

    /**
     * Returns true if the list of users has a user with an Arizona residence
     */
    public boolean containsArizonaResident(List<UserInfoDTO> userInfoDTO) {

        if (userInfoDTO == null) {
            return false;
        }

        return userInfoDTO.stream()
            .map(UserInfoDTO::getPrimaryUserInfo)
            .filter(Objects::nonNull)
            .flatMap(primaryUserInfo -> primaryUserInfo.getAddresses().stream())
            .filter(address -> AddressType.RESIDENTIAL.equals(address.getAddressType()))
            .map(AddressDTO::getState)
            .anyMatch("AZ"::equals);
    }

    /**
     * Returns the last name of the oldest person in a list of people
     */
    public String getOldestPersonLastName(List<UserInfoDTO> users) {

        throw new NotImplementedException("TODO");
    }

    /**
     * Returns the full set of phone numbers in the user list (just numbers, not extensions or country codes)
     */
    public Set<String> getAllPhoneNumbers(List<UserInfoDTO> users) {

        throw new NotImplementedException("TODO");
    }

}
