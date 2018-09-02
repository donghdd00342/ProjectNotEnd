package com.donghd.notend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Friend.
 */
@Entity
@Table(name = "friend")
public class Friend implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "relationship_date", nullable = false)
    private Instant relationshipDate;

    @NotNull
    @Column(name = "status", nullable = false)
    private Integer status;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User owner;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User friend;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getRelationshipDate() {
        return relationshipDate;
    }

    public Friend relationshipDate(Instant relationshipDate) {
        this.relationshipDate = relationshipDate;
        return this;
    }

    public void setRelationshipDate(Instant relationshipDate) {
        this.relationshipDate = relationshipDate;
    }

    public Integer getStatus() {
        return status;
    }

    public Friend status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public User getOwner() {
        return owner;
    }

    public Friend owner(User user) {
        this.owner = user;
        return this;
    }

    public void setOwner(User user) {
        this.owner = user;
    }

    public User getFriend() {
        return friend;
    }

    public Friend friend(User user) {
        this.friend = user;
        return this;
    }

    public void setFriend(User user) {
        this.friend = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Friend friend = (Friend) o;
        if (friend.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), friend.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Friend{" +
            "id=" + getId() +
            ", relationshipDate='" + getRelationshipDate() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
