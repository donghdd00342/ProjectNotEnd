package com.donghd.notend.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the TransactionHistory entity.
 */
public class TransactionHistoryDTO implements Serializable {

    private Long id;

    @NotNull
    private BigDecimal amout;

    @NotNull
    private Instant payDate;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmout() {
        return amout;
    }

    public void setAmout(BigDecimal amout) {
        this.amout = amout;
    }

    public Instant getPayDate() {
        return payDate;
    }

    public void setPayDate(Instant payDate) {
        this.payDate = payDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TransactionHistoryDTO transactionHistoryDTO = (TransactionHistoryDTO) o;
        if (transactionHistoryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), transactionHistoryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TransactionHistoryDTO{" +
            "id=" + getId() +
            ", amout=" + getAmout() +
            ", payDate='" + getPayDate() + "'" +
            ", user=" + getUserId() +
            "}";
    }
}
