package com.donghd.notend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * A TransactionHistory.
 */
@Entity
@Table(name = "transaction_history")
public class TransactionHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "amout", precision = 10, scale = 2, nullable = false)
    private BigDecimal amout;

    @NotNull
    @Column(name = "pay_date", nullable = false)
    private Instant payDate;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmout() {
        return amout;
    }

    public TransactionHistory amout(BigDecimal amout) {
        this.amout = amout;
        return this;
    }

    public void setAmout(BigDecimal amout) {
        this.amout = amout;
    }

    public Instant getPayDate() {
        return payDate;
    }

    public TransactionHistory payDate(Instant payDate) {
        this.payDate = payDate;
        return this;
    }

    public void setPayDate(Instant payDate) {
        this.payDate = payDate;
    }

    public User getUser() {
        return user;
    }

    public TransactionHistory user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TransactionHistory transactionHistory = (TransactionHistory) o;
        if (transactionHistory.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), transactionHistory.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TransactionHistory{" +
            "id=" + getId() +
            ", amout=" + getAmout() +
            ", payDate='" + getPayDate() + "'" +
            "}";
    }
}
