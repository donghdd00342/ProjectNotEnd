package com.donghd.notend.web.rest;

import java.util.List;

import com.donghd.notend.config.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UtilResource
 */
@RestController
@RequestMapping("/api")
public class UtilResource {
    private final Logger log = LoggerFactory.getLogger(UtilResource.class);

    @GetMapping("/countries")
    public List<String> getCountries() {
        log.debug("REST request to get countries");
        return Constants.LIST_OF_COUNTRIES;
    }

    @GetMapping("/languages")
    public List<String> getLanguages() {
        log.debug("REST request to get languages");
        return Constants.LIST_OF_LANGUAGES;
    }
}