package com.donghd.notend.service.dto;

import com.donghd.notend.config.Constants;

import com.donghd.notend.domain.Authority;
import com.donghd.notend.domain.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.*;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO representing a user, with his authorities.
 */
public class UserDTO {

    private Long id;

    @NotBlank
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String login;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Size(max = 50)
    private String middleName;
    @Size(max = 50)
    private String dateOfBirth;
    private Integer gender;
    private Integer marriedStatus;
    private Integer heightCm;
    @Size(max = 100)
    private String countryLiving;
    @Size(max = 100)
    private String city;
    @Size(max = 15)
    private String contactNumber;
    @Size(max = 100)
    private String motherTongue;
    @Size(max = 100)
    private String religion;
    @Size(max = 256)
    private String aboutSelf;
    @Size(max = 256)
    private String familyDetails;
    @Size(max = 256)
    private String qualification;
    @Size(max = 100)
    private String workingAt;
    @Size(max = 256)
    private String hobbies;

    private boolean paidUser = false;
    private Instant expirationDate;
    private Integer paymentPeriod;

    private Integer friendStatus;

    public Integer getFriendStatus() {
    	return this.friendStatus;
    }

    public void setFriendStatus(Integer friendStatus) {
    	this.friendStatus = friendStatus;
    }

    @Email
    @Size(min = 5, max = 254)
    private String email;

    @Size(max = 256)
    private String imageUrl;

    private boolean activated = false;

    @Size(min = 2, max = 6)
    private String langKey;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    private Set<String> authorities;

    public UserDTO() {
        // Empty constructor needed for Jackson.
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        
        this.middleName = user.getMiddleName();
        this.dateOfBirth = user.getDateOfBirth();
        this.gender = user.getGender();
        this.marriedStatus = user.getMarriedStatus();
        this.heightCm = user.getHeightCm();
        this.countryLiving = user.getCountryLiving();
        this.city = user.getCity();
        this.contactNumber = user.getContactNumber();
        this.motherTongue = user.getMotherTongue();
        this.religion = user.getReligion();
        this.aboutSelf = user.getAboutSelf();
        this.familyDetails = user.getFamilyDetails();
        this.qualification = user.getQualification();
        this.workingAt = user.getWorkingAt();
        this.hobbies = user.getHobbies();
        
        this.paidUser = user.getPaidUser();
        this.expirationDate = user.getExpirationDate();
        this.paymentPeriod = user.getPaymentPeriod();

        this.email = user.getEmail();
        this.activated = user.getActivated();
        this.imageUrl = user.getImageUrl();
        this.langKey = user.getLangKey();
        this.createdBy = user.getCreatedBy();
        this.createdDate = user.getCreatedDate();
        this.lastModifiedBy = user.getLastModifiedBy();
        this.lastModifiedDate = user.getLastModifiedDate();
        this.authorities = user.getAuthorities().stream()
            .map(Authority::getName)
            .collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public String getMiddleName() {
    	return this.middleName;
    }

    public void setMiddleName(String middleName) {
    	this.middleName = middleName;
    }

    public String getDateOfBirth() {
    	return this.dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
    	this.dateOfBirth = dateOfBirth;
    }

    public Integer getGender() {
    	return this.gender;
    }

    public void setGender(Integer gender) {
    	this.gender = gender;
    }

    public Integer getMarriedStatus() {
    	return this.marriedStatus;
    }

    public void setMarriedStatus(Integer marriedStatus) {
    	this.marriedStatus = marriedStatus;
    }

    public Integer getHeightCm() {
    	return this.heightCm;
    }

    public void setHeightCm(Integer heightCm) {
    	this.heightCm = heightCm;
    }

    public String getCountryLiving() {
    	return this.countryLiving;
    }

    public void setCountryLiving(String countryLiving) {
    	this.countryLiving = countryLiving;
    }

    public String getCity() {
    	return this.city;
    }

    public void setCity(String city) {
    	this.city = city;
    }

    public String getContactNumber() {
    	return this.contactNumber;
    }

    public void setContactNumber(String contectNumber) {
    	this.contactNumber = contectNumber;
    }

    public String getMotherTongue() {
    	return this.motherTongue;
    }

    public void setMotherTongue(String motherTongue) {
    	this.motherTongue = motherTongue;
    }

    public String getReligion() {
    	return this.religion;
    }

    public void setReligion(String religion) {
    	this.religion = religion;
    }

    public String getAboutSelf() {
    	return this.aboutSelf;
    }

    public void setAboutSelf(String aboutSelf) {
    	this.aboutSelf = aboutSelf;
    }

    public String getFamilyDetails() {
    	return this.familyDetails;
    }

    public void setFamilyDetails(String familyDetails) {
    	this.familyDetails = familyDetails;
    }

    public String getQualification() {
    	return this.qualification;
    }

    public void setQualification(String qualification) {
    	this.qualification = qualification;
    }

    public String getWorkingAt() {
    	return this.workingAt;
    }

    public void setWorkingAt(String workingAt) {
    	this.workingAt = workingAt;
    }

    public String getHobbies() {
    	return this.hobbies;
    }

    public void setHobbies(String hobbies) {
    	this.hobbies = hobbies;
    }

    public boolean getPaidUser() {
    	return this.paidUser;
    }

    public void setPaidUser(boolean paidUser) {
    	this.paidUser = paidUser;
    }

    public Instant getExpirationDate() {
    	return this.expirationDate;
    }

    public void setExpirationDate(Instant expirationDate) {
    	this.expirationDate = expirationDate;
    }

    public Integer getPaymentPeriod() {
    	return this.paymentPeriod;
    }

    public void setPaymentPeriod(Integer paymentPeriod) {
    	this.paymentPeriod = paymentPeriod;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
            "login='" + login + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", imageUrl='" + imageUrl + '\'' +
            ", activated=" + activated +
            ", langKey='" + langKey + '\'' +
            ", createdBy=" + createdBy +
            ", createdDate=" + createdDate +
            ", lastModifiedBy='" + lastModifiedBy + '\'' +
            ", lastModifiedDate=" + lastModifiedDate +
            ", authorities=" + authorities +
            "}";
    }
}
