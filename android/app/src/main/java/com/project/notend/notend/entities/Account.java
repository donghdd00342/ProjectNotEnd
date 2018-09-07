package com.project.notend.notend.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Account {
    @SerializedName("aboutSelf")
    @Expose
    private String aboutSelf;
    @SerializedName("activated")
    @Expose
    private Boolean activated;
    @SerializedName("authorities")
    @Expose
    private List<String> authorities = null;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("contactNumber")
    @Expose
    private String contactNumber;
    @SerializedName("countryLiving")
    @Expose
    private String countryLiving;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("dateOfBirth")
    @Expose
    private String dateOfBirth;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("expirationDate")
    @Expose
    private String expirationDate;
    @SerializedName("familyDetails")
    @Expose
    private String familyDetails;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("heightCm")
    @Expose
    private Integer heightCm;
    @SerializedName("hobbies")
    @Expose
    private String hobbies;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("langKey")
    @Expose
    private String langKey;
    @SerializedName("lastModifiedBy")
    @Expose
    private String lastModifiedBy;
    @SerializedName("lastModifiedDate")
    @Expose
    private String lastModifiedDate;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("marriedStatus")
    @Expose
    private Integer marriedStatus;
    @SerializedName("middleName")
    @Expose
    private String middleName;
    @SerializedName("motherTongue")
    @Expose
    private String motherTongue;
    @SerializedName("paidUser")
    @Expose
    private Boolean paidUser;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("paymentPeriod")
    @Expose
    private Integer paymentPeriod;
    @SerializedName("qualification")
    @Expose
    private String qualification;
    @SerializedName("religion")
    @Expose
    private String religion;
    @SerializedName("workingAt")
    @Expose
    private String workingAt;

    public String getAboutSelf() {
        return aboutSelf;
    }

    public void setAboutSelf(String aboutSelf) {
        this.aboutSelf = aboutSelf;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getCountryLiving() {
        return countryLiving;
    }

    public void setCountryLiving(String countryLiving) {
        this.countryLiving = countryLiving;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getFamilyDetails() {
        return familyDetails;
    }

    public void setFamilyDetails(String familyDetails) {
        this.familyDetails = familyDetails;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getHeightCm() {
        return heightCm;
    }

    public void setHeightCm(Integer heightCm) {
        this.heightCm = heightCm;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getMarriedStatus() {
        return marriedStatus;
    }

    public void setMarriedStatus(Integer marriedStatus) {
        this.marriedStatus = marriedStatus;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getMotherTongue() {
        return motherTongue;
    }

    public void setMotherTongue(String motherTongue) {
        this.motherTongue = motherTongue;
    }

    public Boolean getPaidUser() {
        return paidUser;
    }

    public void setPaidUser(Boolean paidUser) {
        this.paidUser = paidUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPaymentPeriod() {
        return paymentPeriod;
    }

    public void setPaymentPeriod(Integer paymentPeriod) {
        this.paymentPeriod = paymentPeriod;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getWorkingAt() {
        return workingAt;
    }

    public void setWorkingAt(String workingAt) {
        this.workingAt = workingAt;
    }

    public Account(String email, String firstName, String lastName,String login, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "aboutSelf='" + aboutSelf + '\'' +
                ", activated=" + activated +
                ", authorities=" + authorities +
                ", city='" + city + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", countryLiving='" + countryLiving + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", email='" + email + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", familyDetails='" + familyDetails + '\'' +
                ", firstName='" + firstName + '\'' +
                ", gender=" + gender +
                ", heightCm=" + heightCm +
                ", hobbies='" + hobbies + '\'' +
                ", id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", langKey='" + langKey + '\'' +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", lastModifiedDate='" + lastModifiedDate + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", marriedStatus=" + marriedStatus +
                ", middleName='" + middleName + '\'' +
                ", motherTongue='" + motherTongue + '\'' +
                ", paidUser=" + paidUser +
                ", password='" + password + '\'' +
                ", paymentPeriod=" + paymentPeriod +
                ", qualification='" + qualification + '\'' +
                ", religion='" + religion + '\'' +
                ", workingAt='" + workingAt + '\'' +
                '}';
    }
}
