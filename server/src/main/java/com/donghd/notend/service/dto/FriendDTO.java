package com.donghd.notend.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Friend entity.
 */
public class FriendDTO implements Serializable {

    private Long id;

    private Instant relationshipDate;

    private Integer status;

    private Long ownerId;

    private Long friendId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getRelationshipDate() {
        return relationshipDate;
    }

    public void setRelationshipDate(Instant relationshipDate) {
        this.relationshipDate = relationshipDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long userId) {
        this.ownerId = userId;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long userId) {
        this.friendId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FriendDTO friendDTO = (FriendDTO) o;
        if (friendDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), friendDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FriendDTO{" +
            "id=" + getId() +
            ", relationshipDate='" + getRelationshipDate() + "'" +
            ", status=" + getStatus() +
            ", owner=" + getOwnerId() +
            ", friend=" + getFriendId() +
            "}";
    }
}
