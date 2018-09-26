package com.project.notend.notend.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChatMessage {

    @SerializedName("fromUserFirstName")
    @Expose
    private String fromUserFirstName;
    @SerializedName("fromUserId")
    @Expose
    private Integer fromUserId;
    @SerializedName("fromUserImageUrl")
    @Expose
    private String fromUserImageUrl;
    @SerializedName("fromUserLastName")
    @Expose
    private String fromUserLastName;
    @SerializedName("fromUserLogin")
    @Expose
    private String fromUserLogin;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("sendingDateTime")
    @Expose
    private String sendingDateTime;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("toUserId")
    @Expose
    private Integer toUserId;

    public String getFromUserFirstName() {
        return fromUserFirstName;
    }

    public void setFromUserFirstName(String fromUserFirstName) {
        this.fromUserFirstName = fromUserFirstName;
    }

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getFromUserImageUrl() {
        return fromUserImageUrl;
    }

    public void setFromUserImageUrl(String fromUserImageUrl) {
        this.fromUserImageUrl = fromUserImageUrl;
    }

    public String getFromUserLastName() {
        return fromUserLastName;
    }

    public void setFromUserLastName(String fromUserLastName) {
        this.fromUserLastName = fromUserLastName;
    }

    public String getFromUserLogin() {
        return fromUserLogin;
    }

    public void setFromUserLogin(String fromUserLogin) {
        this.fromUserLogin = fromUserLogin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSendingDateTime() {
        return sendingDateTime;
    }

    public void setSendingDateTime(String sendingDateTime) {
        this.sendingDateTime = sendingDateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    public ChatMessage(Integer fromUserId, Integer id, String msg, String sendingDateTime, Integer toUserId, String fromUserImageUrl,
                       String fromUserFirstName, String fromUserLastName) {
        this.fromUserId = fromUserId;
        this.id = id;
        this.msg = msg;
        this.sendingDateTime = sendingDateTime;
        this.toUserId = toUserId;
        this.fromUserImageUrl= fromUserImageUrl;
        this.fromUserFirstName = fromUserFirstName;
        this.fromUserLastName = fromUserLastName;
    }

    public ChatMessage() {
    }
}
