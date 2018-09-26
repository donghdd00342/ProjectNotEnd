package com.project.notend.notend.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Friend implements Parcelable {
    @SerializedName("friendFirstName")
    @Expose
    private String friendFirstName;
    @SerializedName("friendLastName")
    @Expose
    private String friendLastName;
    @SerializedName("friendId")
    @Expose
    private Integer friendId;
    @SerializedName("ownerId")
    @Expose
    private Integer ownerId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("friendLogin")
    @Expose
    private String friendLogin;
    @SerializedName("relationshipDate")
    @Expose
    private String relationshipDate;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("friendImageUrl")
    @Expose
    private String friendImageUrl;

    protected Friend(Parcel in) {
        friendFirstName = in.readString();
        friendLastName = in.readString();
        if (in.readByte() == 0) {
            friendId = null;
        } else {
            friendId = in.readInt();
        }
        if (in.readByte() == 0) {
            ownerId = null;
        } else {
            ownerId = in.readInt();
        }
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        friendLogin = in.readString();
        relationshipDate = in.readString();
        if (in.readByte() == 0) {
            status = null;
        } else {
            status = in.readInt();
        }
        friendImageUrl = in.readString();
    }

    public static final Creator<Friend> CREATOR = new Creator<Friend>() {
        @Override
        public Friend createFromParcel(Parcel in) {
            return new Friend(in);
        }

        @Override
        public Friend[] newArray(int size) {
            return new Friend[size];
        }
    };

    public String getFriendFirstName() {
        return friendFirstName;
    }

    public void setFriendFirstName(String friendFirstName) {
        this.friendFirstName = friendFirstName;
    }

    public String getFriendLastName() {
        return friendLastName;
    }

    public void setFriendLastName(String friendLastName) {
        this.friendLastName = friendLastName;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFriendLogin() {
        return friendLogin;
    }

    public void setFriendLogin(String friendLogin) {
        this.friendLogin = friendLogin;
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

    public String getFriendImageUrl() {
        return friendImageUrl;
    }

    public void setFriendImageUrl(String friendImageUrl) {
        this.friendImageUrl = friendImageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(friendFirstName);
        parcel.writeString(friendLastName);
        if (friendId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(friendId);
        }
        if (ownerId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(ownerId);
        }
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(friendLogin);
        parcel.writeString(relationshipDate);
        if (status == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(status);
        }
        parcel.writeString(friendImageUrl);
    }

    public Friend() {
    }
}
