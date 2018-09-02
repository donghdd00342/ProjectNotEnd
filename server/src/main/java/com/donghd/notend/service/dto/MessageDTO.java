package com.donghd.notend.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Message entity.
 */
public class MessageDTO implements Serializable {

    private Long id;

    @NotNull
    private String msg;

    @NotNull
    private Integer status;

    @NotNull
    private Instant sendingDateTime;

    private Long fromUserId;

    private Long toUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Instant getSendingDateTime() {
        return sendingDateTime;
    }

    public void setSendingDateTime(Instant sendingDateTime) {
        this.sendingDateTime = sendingDateTime;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long userId) {
        this.fromUserId = userId;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long userId) {
        this.toUserId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MessageDTO messageDTO = (MessageDTO) o;
        if (messageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), messageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
            "id=" + getId() +
            ", msg='" + getMsg() + "'" +
            ", status=" + getStatus() +
            ", sendingDateTime='" + getSendingDateTime() + "'" +
            ", fromUser=" + getFromUserId() +
            ", toUser=" + getToUserId() +
            "}";
    }
}
