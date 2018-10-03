package com.donghd.notend.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.donghd.notend.security.AuthoritiesConstants;
import com.donghd.notend.service.ConfigInfoService;
import com.donghd.notend.web.rest.util.HeaderUtil;
import com.donghd.notend.service.dto.ConfigInfoDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;

import java.util.Optional;

/**
 * REST controller for managing ConfigInfo.
 */
@RestController
@RequestMapping("/api")
public class ConfigInfoResource {

    private final Logger log = LoggerFactory.getLogger(ConfigInfoResource.class);

    private static final String ENTITY_NAME = "configInfo";

    private final ConfigInfoService configInfoService;

    public ConfigInfoResource(ConfigInfoService configInfoService) {
        this.configInfoService = configInfoService;
    }

    /**
     * PUT  /config-infos : Updates an existing configInfo.
     *
     * @param configInfoDTO the configInfoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated configInfoDTO,
     * or with status 400 (Bad Request) if the configInfoDTO is not valid,
     * or with status 500 (Internal Server Error) if the configInfoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/config-infos")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<ConfigInfoDTO> updateConfigInfo(@Valid @RequestBody ConfigInfoDTO configInfoDTO) throws URISyntaxException {
        log.debug("REST request to update ConfigInfo : {}", configInfoDTO);
        configInfoDTO.setId(1L);
        ConfigInfoDTO result = configInfoService.save(configInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, configInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /config-infos/:id : get the "id" configInfo.
     *
     * @param id the id of the configInfoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the configInfoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/config-infos")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<ConfigInfoDTO> getConfigInfo() {
        log.debug("REST request to get ConfigInfo");
        Optional<ConfigInfoDTO> configInfoDTO = configInfoService.findOne(1L);
        return ResponseUtil.wrapOrNotFound(configInfoDTO);
    }
}
