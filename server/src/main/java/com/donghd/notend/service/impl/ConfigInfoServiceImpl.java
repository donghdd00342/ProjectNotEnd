package com.donghd.notend.service.impl;

import com.donghd.notend.service.ConfigInfoService;
import com.donghd.notend.domain.ConfigInfo;
import com.donghd.notend.repository.ConfigInfoRepository;
import com.donghd.notend.service.dto.ConfigInfoDTO;
import com.donghd.notend.service.mapper.ConfigInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing ConfigInfo.
 */
@Service
@Transactional
public class ConfigInfoServiceImpl implements ConfigInfoService {

    private final Logger log = LoggerFactory.getLogger(ConfigInfoServiceImpl.class);

    private final ConfigInfoRepository configInfoRepository;

    private final ConfigInfoMapper configInfoMapper;

    public ConfigInfoServiceImpl(ConfigInfoRepository configInfoRepository, ConfigInfoMapper configInfoMapper) {
        this.configInfoRepository = configInfoRepository;
        this.configInfoMapper = configInfoMapper;
    }

    /**
     * Save a configInfo.
     *
     * @param configInfoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ConfigInfoDTO save(ConfigInfoDTO configInfoDTO) {
        log.debug("Request to save ConfigInfo : {}", configInfoDTO);
        ConfigInfo configInfo = configInfoMapper.toEntity(configInfoDTO);
        configInfo = configInfoRepository.save(configInfo);
        return configInfoMapper.toDto(configInfo);
    }

    /**
     * Get all the configInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ConfigInfoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ConfigInfos");
        return configInfoRepository.findAll(pageable)
            .map(configInfoMapper::toDto);
    }


    /**
     * Get one configInfo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ConfigInfoDTO> findOne(Long id) {
        log.debug("Request to get ConfigInfo : {}", id);
        return configInfoRepository.findById(id)
            .map(configInfoMapper::toDto);
    }

    /**
     * Delete the configInfo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ConfigInfo : {}", id);
        configInfoRepository.deleteById(id);
    }
}
