package com.project.notend.notend.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Account implements Parcelable {
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
    @SerializedName("friendStatus")
    @Expose
    private Integer friendStatus;

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

    public Integer getFriendStatus() {
        return friendStatus;
    }

    public void setFriendStatus(Integer friendStatus) {
        this.friendStatus = friendStatus;
    }
  
    public Account(String email, String firstName, String lastName, String login, String password, int gender) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.gender = gender;
    }

    public Account(String email, String firstName, String lastName, String login, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    protected Account(Parcel in) {
        aboutSelf = in.readString();
        byte tmpActivated = in.readByte();
        activated = tmpActivated == 0 ? null : tmpActivated == 1;
        authorities = in.createStringArrayList();
        city = in.readString();
        contactNumber = in.readString();
        countryLiving = in.readString();
        createdBy = in.readString();
        createdDate = in.readString();
        dateOfBirth = in.readString();
        email = in.readString();
        expirationDate = in.readString();
        familyDetails = in.readString();
        firstName = in.readString();
        if (in.readByte() == 0) {
            gender = null;
        } else {
            gender = in.readInt();
        }
        if (in.readByte() == 0) {
            friendStatus = null;
        } else {
            friendStatus = in.readInt();
        }
        if (in.readByte() == 0) {
            heightCm = null;
        } else {
            heightCm = in.readInt();
        }
        hobbies = in.readString();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        imageUrl = in.readString();
        langKey = in.readString();
        lastModifiedBy = in.readString();
        lastModifiedDate = in.readString();
        lastName = in.readString();
        login = in.readString();
        if (in.readByte() == 0) {
            marriedStatus = null;
        } else {
            marriedStatus = in.readInt();
        }
        middleName = in.readString();
        motherTongue = in.readString();
        byte tmpPaidUser = in.readByte();
        paidUser = tmpPaidUser == 0 ? null : tmpPaidUser == 1;
        password = in.readString();
        if (in.readByte() == 0) {
            paymentPeriod = null;
        } else {
            paymentPeriod = in.readInt();
        }
        qualification = in.readString();
        religion = in.readString();
        workingAt = in.readString();
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(aboutSelf);
        parcel.writeByte((byte) (activated == null ? 0 : activated ? 1 : 2));
        parcel.writeStringList(authorities);
        parcel.writeString(city);
        parcel.writeString(contactNumber);
        parcel.writeString(countryLiving);
        parcel.writeString(createdBy);
        parcel.writeString(createdDate);
        parcel.writeString(dateOfBirth);
        parcel.writeString(email);
        parcel.writeString(expirationDate);
        parcel.writeString(familyDetails);
        parcel.writeString(firstName);
        if (gender == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(gender);
        }
        if (friendStatus == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(friendStatus);
        }
        if (heightCm == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(heightCm);
        }
        parcel.writeString(hobbies);
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(imageUrl);
        parcel.writeString(langKey);
        parcel.writeString(lastModifiedBy);
        parcel.writeString(lastModifiedDate);
        parcel.writeString(lastName);
        parcel.writeString(login);
        if (marriedStatus == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(marriedStatus);
        }
        parcel.writeString(middleName);
        parcel.writeString(motherTongue);
        parcel.writeByte((byte) (paidUser == null ? 0 : paidUser ? 1 : 2));
        parcel.writeString(password);
        if (paymentPeriod == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(paymentPeriod);
        }
        parcel.writeString(qualification);
        parcel.writeString(religion);
        parcel.writeString(workingAt);
    }

    public Account(String countryLiving, String dateOfBirth, String email, String familyDetails,
                   String firstName, Integer heightCm, Integer id, String login, String motherTongue,
                   String religion, String workingAt, String city, Integer gender, Integer status,
                   String lastName, boolean paid, List<String> authorize, String avatar, String hobbies) {
        this.countryLiving = countryLiving;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.familyDetails = familyDetails;
        this.firstName = firstName;
        this.heightCm = heightCm;
        this.id = id;
        this.login = login;
        this.motherTongue = motherTongue;
        this.religion = religion;
        this.workingAt = workingAt;
        this.city = city;
        this.gender = gender;
        this.marriedStatus = status;
        this.lastName = lastName;
        this.activated = true;
        this.paidUser = paid;
        this.authorities = authorize;
        this.imageUrl = avatar;
        this.hobbies = hobbies;
    }
}

