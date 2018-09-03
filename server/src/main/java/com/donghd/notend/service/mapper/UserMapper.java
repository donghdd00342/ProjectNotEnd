package com.donghd.notend.service.mapper;

import com.donghd.notend.domain.Authority;
import com.donghd.notend.domain.User;
import com.donghd.notend.service.dto.UserDTO;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Mapper for the entity User and its DTO called UserDTO.
 *
 * Normal mappers are generated using MapStruct, this one is hand-coded as MapStruct
 * support is still in beta, and requires a manual step with an IDE.
 */
@Service
public class UserMapper {

    public UserDTO userToUserDTO(User user) {
        return new UserDTO(user);
    }

    public List<UserDTO> usersToUserDTOs(List<User> users) {
        return users.stream()
            .filter(Objects::nonNull)
            .map(this::userToUserDTO)
            .collect(Collectors.toList());
    }

    public User userDTOToUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        } else {
            User user = new User();
            user.setId(userDTO.getId());
            user.setLogin(userDTO.getLogin());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setImageUrl(userDTO.getImageUrl());
            user.setActivated(userDTO.isActivated());
            user.setLangKey(userDTO.getLangKey());
            user.setMiddleName(userDTO.getMiddleName());
            user.setDateOfBirth(userDTO.getDateOfBirth());
            user.setGender(userDTO.getGender());
            user.setMarriedStatus(userDTO.getMarriedStatus());
            user.setHeightCm(userDTO.getHeightCm());
            user.setCountryLiving(userDTO.getCountryLiving());
            user.setCity(userDTO.getCity());
            user.setContactNumber(userDTO.getContactNumber());
            user.setMotherTongue(userDTO.getMotherTongue());
            user.setReligion(userDTO.getReligion());
            user.setAboutSelf(userDTO.getAboutSelf());
            user.setFamilyDetails(userDTO.getFamilyDetails());
            user.setQualification(userDTO.getQualification());
            user.setWorkingAt(userDTO.getWorkingAt());
            user.setHobbies(userDTO.getHobbies());

            user.setPaidUser(userDTO.getPaidUser());
            user.setExpirationDate(userDTO.getExpirationDate());
            user.setPaymentPeriod(userDTO.getPaymentPeriod());
            
            Set<Authority> authorities = this.authoritiesFromStrings(userDTO.getAuthorities());
            if (authorities != null) {
                user.setAuthorities(authorities);
            }
            return user;
        }
    }

    public List<User> userDTOsToUsers(List<UserDTO> userDTOs) {
        return userDTOs.stream()
            .filter(Objects::nonNull)
            .map(this::userDTOToUser)
            .collect(Collectors.toList());
    }

    public User userFromId(Long id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }

    public Set<Authority> authoritiesFromStrings(Set<String> strings) {
        return strings.stream().map(string -> {
            Authority auth = new Authority();
            auth.setName(string);
            return auth;
        }).collect(Collectors.toSet());
    }
}
