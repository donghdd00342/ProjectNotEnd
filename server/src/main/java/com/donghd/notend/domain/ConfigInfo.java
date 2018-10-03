package com.donghd.notend.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ConfigInfo.
 */
@Entity
@Table(name = "config_info")
public class ConfigInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "remove_acc_not_active", nullable = false)
    private Integer removeAccNotActive;

    @NotNull
    @Column(name = "remind_upgrade", nullable = false)
    private Integer remindUpgrade;

    @NotNull
    @Column(name = "send_come_back", nullable = false)
    private Integer sendComeBack;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRemoveAccNotActive() {
        return removeAccNotActive;
    }

    public ConfigInfo removeAccNotActive(Integer removeAccNotActive) {
        this.removeAccNotActive = removeAccNotActive;
        return this;
    }

    public void setRemoveAccNotActive(Integer removeAccNotActive) {
        this.removeAccNotActive = removeAccNotActive;
    }

    public Integer getRemindUpgrade() {
        return remindUpgrade;
    }

    public ConfigInfo remindUpgrade(Integer remindUpgrade) {
        this.remindUpgrade = remindUpgrade;
        return this;
    }

    public void setRemindUpgrade(Integer remindUpgrade) {
        this.remindUpgrade = remindUpgrade;
    }

    public Integer getSendComeBack() {
        return sendComeBack;
    }

    public ConfigInfo sendComeBack(Integer sendComeBack) {
        this.sendComeBack = sendComeBack;
        return this;
    }

    public void setSendComeBack(Integer sendComeBack) {
        this.sendComeBack = sendComeBack;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConfigInfo configInfo = (ConfigInfo) o;
        if (configInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), configInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConfigInfo{" +
            "id=" + getId() +
            ", removeAccNotActive=" + getRemoveAccNotActive() +
            ", remindUpgrade=" + getRemindUpgrade() +
            ", sendComeBack=" + getSendComeBack() +
            "}";
    }
}
