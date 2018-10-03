package com.donghd.notend.service.mapper;

import com.donghd.notend.domain.*;
import com.donghd.notend.service.dto.ConfigInfoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ConfigInfo and its DTO ConfigInfoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ConfigInfoMapper extends EntityMapper<ConfigInfoDTO, ConfigInfo> {



    default ConfigInfo fromId(Long id) {
        if (id == null) {
            return null;
        }
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setId(id);
        return configInfo;
    }
}
