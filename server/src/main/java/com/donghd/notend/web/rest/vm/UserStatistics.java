package com.donghd.notend.web.rest.vm;

/**
 * UserStatistic
 */
public class UserStatistics {
    private Long totalUser;
    private Long activedUser;// Tài khoản đã actived
    private Long paidUser;// Tài khoản trả phí

    private Long totalFriends;
    private Long pendingFriends;// Yêu cầu kết bạn

    private Long totalMessages;
    private Long unreadMessages;// Số tin nhắn chưa đọc

    private Long totalTrangsactions;// Tổng số giao dịch thành công

    public Long getTotalTrangsactions() {
    	return this.totalTrangsactions;
    }

    public void setTotalTrangsactions(Long totalTrangsactions) {
    	this.totalTrangsactions = totalTrangsactions;
    }


    public Long getTotalMessages() {
    	return this.totalMessages;
    }

    public void setTotalMessages(Long totalMessages) {
    	this.totalMessages = totalMessages;
    }

    public Long getUnreadMessages() {
    	return this.unreadMessages;
    }

    public void setUnreadMessages(Long unreadMessages) {
    	this.unreadMessages = unreadMessages;
    }


    public Long getTotalFriends() {
    	return this.totalFriends;
    }

    public void setTotalFriends(Long totalFriends) {
    	this.totalFriends = totalFriends;
    }

    public Long getPendingFriends() {
    	return this.pendingFriends;
    }

    public void setPendingFriends(Long pendingFriends) {
    	this.pendingFriends = pendingFriends;
    }



    public Long getPaidUser() {
    	return this.paidUser;
    }

    public void setPaidUser(Long paidUser) {
    	this.paidUser = paidUser;
    }


    public Long getActivedUser() {
    	return this.activedUser;
    }

    public void setActivedUser(Long activedUser) {
    	this.activedUser = activedUser;
    }


    public Long getTotalUser() {
    	return this.totalUser;
    }

    public void setTotalUser(Long totalUser) {
    	this.totalUser = totalUser;
    }

    
}