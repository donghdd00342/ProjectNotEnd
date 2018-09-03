package com.donghd.notend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Message.
 */
@Entity
@Table(name = "message")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "msg", nullable = false)
    private String msg;

    @NotNull
    @Column(name = "status", nullable = false)
    private Integer status;

    @NotNull
    @Column(name = "sending_date_time", nullable = false)
    private Instant sendingDateTime;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User fromUser;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User toUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public Message msg(String msg) {
        this.msg = msg;
        return this;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public Message status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Instant getSendingDateTime() {
        return sendingDateTime;
    }

    public Message sendingDateTime(Instant sendingDateTime) {
        this.sendingDateTime = sendingDateTime;
        return this;
    }

    public void setSendingDateTime(Instant sendingDateTime) {
        this.sendingDateTime = sendingDateTime;
    }

    public User getFromUser() {
        return fromUser;
    }

    public Message fromUser(User user) {
        this.fromUser = user;
        return this;
    }

    public void setFromUser(User user) {
        this.fromUser = user;
    }

    public User getToUser() {
        return toUser;
    }

    public Message toUser(User user) {
        this.toUser = user;
        return this;
    }

    public void setToUser(User user) {
        this.toUser = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Message message = (Message) o;
        if (message.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), message.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Message{" +
            "id=" + getId() +
            ", msg='" + getMsg() + "'" +
            ", status=" + getStatus() +
            ", sendingDateTime='" + getSendingDateTime() + "'" +
            "}";
    }
}
