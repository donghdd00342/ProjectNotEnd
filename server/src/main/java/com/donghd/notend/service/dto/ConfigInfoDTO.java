package com.donghd.notend.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ConfigInfo entity.
 */
public class ConfigInfoDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer removeAccNotActive;

    @NotNull
    private Integer remindUpgrade;

    @NotNull
    private Integer sendComeBack;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRemoveAccNotActive() {
        return removeAccNotActive;
    }

    public void setRemoveAccNotActive(Integer removeAccNotActive) {
        this.removeAccNotActive = removeAccNotActive;
    }

    public Integer getRemindUpgrade() {
        return remindUpgrade;
    }

    public void setRemindUpgrade(Integer remindUpgrade) {
        this.remindUpgrade = remindUpgrade;
    }

    public Integer getSendComeBack() {
        return sendComeBack;
    }

    public void setSendComeBack(Integer sendComeBack) {
        this.sendComeBack = sendComeBack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ConfigInfoDTO configInfoDTO = (ConfigInfoDTO) o;
        if (configInfoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), configInfoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConfigInfoDTO{" +
            "id=" + getId() +
            ", removeAccNotActive=" + getRemoveAccNotActive() +
            ", remindUpgrade=" + getRemindUpgrade() +
            ", sendComeBack=" + getSendComeBack() +
            "}";
    }
}
