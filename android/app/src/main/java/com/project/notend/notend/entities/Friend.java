package com.project.notend.notend.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Friend {
    @SerializedName("friendFirstName")
    @Expose
    private String friendFirstName;
    @SerializedName("friendId")
    @Expose
    private Integer friendId;
    @SerializedName("friendImageUrl")
    @Expose
    private String friendImageUrl;
    @SerializedName("friendLastName")
    @Expose
    private String friendLastName;
    @SerializedName("friendLogin")
    @Expose
    private String friendLogin;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("ownerId")
    @Expose
    private Integer ownerId;
    @SerializedName("relationshipDate")
    @Expose
    private String relationshipDate;
    @SerializedName("status")
    @Expose
    private Integer status;

    public String getFriendFirstName() {
        return friendFirstName;
    }

    public void setFriendFirstName(String friendFirstName) {
        this.friendFirstName = friendFirstName;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public String getFriendImageUrl() {
        return friendImageUrl;
    }

    public void setFriendImageUrl(String friendImageUrl) {
        this.friendImageUrl = friendImageUrl;
    }

    public String getFriendLastName() {
        return friendLastName;
    }

    public void setFriendLastName(String friendLastName) {
        this.friendLastName = friendLastName;
    }

    public String getFriendLogin() {
        return friendLogin;
    }

    public void setFriendLogin(String friendLogin) {
        this.friendLogin = friendLogin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getRelationshipDate() {
        return relationshipDate;
    }

    public void setRelationshipDate(String relationshipDate) {
        this.relationshipDate = relationshipDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
