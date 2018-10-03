package com.donghd.notend.service;

import com.donghd.notend.service.dto.ConfigInfoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ConfigInfo.
 */
public interface ConfigInfoService {

    /**
     * Save a configInfo.
     *
     * @param configInfoDTO the entity to save
     * @return the persisted entity
     */
    ConfigInfoDTO save(ConfigInfoDTO configInfoDTO);

    /**
     * Get all the configInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ConfigInfoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" configInfo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ConfigInfoDTO> findOne(Long id);

    /**
     * Delete the "id" configInfo.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
