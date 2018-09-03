package com.donghd.notend.domain;

import com.donghd.notend.config.Constants;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.BatchSize;
import javax.validation.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.time.Instant;

/**
 * A user.
 */
@Entity
@Table(name = "jhi_user")

public class User extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    private String login;

    @JsonIgnore
    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60, nullable = false)
    private String password;

    @Size(max = 50)
    @Column(name = "first_name", length = 50)
    private String firstName;

    @Size(max = 50)
    @Column(name = "last_name", length = 50)
    private String lastName;

    @Size(max = 50)
    @Column(name = "middle_name", length = 50)
    private String middleName;

    @Size(max = 10)
    @Column(name = "date_of_birth", length = 10)
    private String dateOfBirth;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "married_status")
    private Integer marriedStatus;

    @Column(name = "height_cm")
    private Integer heightCm;

    @Size(max = 100)
    @Column(name = "country_living", length = 100)
    private String countryLiving;

    @Size(max = 100)
    @Column(name = "city", length = 100)
    private String city;

    @Size(max = 15)
    @Column(name = "contact_number", length = 15)
    private String contactNumber;

    @Size(max = 100)
    @Column(name = "mother_tongue", length = 100)
    private String motherTongue;

    @Size(max = 100)
    @Column(name = "religion", length = 100)
    private String religion;

    @Size(max = 256)
    @Column(name = "about_self", length = 256)
    private String aboutSelf;

    @Size(max = 256)
    @Column(name = "family_details", length = 256)
    private String familyDetails;

    @Size(max = 256)
    @Column(name = "qualification", length = 256)
    private String qualification;

    @Size(max = 100)
    @Column(name = "working_at", length = 100)
    private String workingAt;

    @Size(max = 256)
    @Column(name = "hobbies", length = 256)
    private String hobbies;

    @Column(name = "payment_period")
    private Integer paymentPeriod;

    @Column(name = "expiration_date")
    private Instant expirationDate;

    @Column(name = "paid_user")
    private boolean paidUser = false;

    @Email
    @Size(min = 5, max = 254)
    @Column(length = 254, unique = true)
    private String email;

    @NotNull
    @Column(nullable = false)
    private boolean activated = false;

    @Size(min = 2, max = 6)
    @Column(name = "lang_key", length = 6)
    private String langKey;

    @Size(max = 256)
    @Column(name = "image_url", length = 256)
    private String imageUrl;

    @Size(max = 20)
    @Column(name = "activation_key", length = 20)
    @JsonIgnore
    private String activationKey;

    @Size(max = 20)
    @Column(name = "reset_key", length = 20)
    @JsonIgnore
    private String resetKey;

    @Column(name = "reset_date")
    private Instant resetDate = null;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "jhi_user_authority",
        joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})

    @BatchSize(size = 20)
    private Set<Authority> authorities = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    // Lowercase the login before saving it in database
    public void setLogin(String login) {
        this.login = StringUtils.lowerCase(login, Locale.ENGLISH);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public boolean getActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public String getResetKey() {
        return resetKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    public Instant getResetDate() {
        return resetDate;
    }

    public void setResetDate(Instant resetDate) {
        this.resetDate = resetDate;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;
        return !(user.getId() == null || getId() == null) && Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "User{" +
            "login='" + login + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", imageUrl='" + imageUrl + '\'' +
            ", activated='" + activated + '\'' +
            ", langKey='" + langKey + '\'' +
            ", activationKey='" + activationKey + '\'' +
            "}";
    }
}
