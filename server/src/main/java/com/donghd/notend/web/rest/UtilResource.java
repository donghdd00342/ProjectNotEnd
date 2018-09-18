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

    @GetMapping("/avatar/boy")
    public List<String> getBoys() {
        log.debug("REST request to get avatar/boy");
        return Constants.BOY_AVATAR_LIST;
    }
    @GetMapping("/avatar/girl")
    public List<String> getGirls() {
        log.debug("REST request to get avatar/girl");
        return Constants.GIRL_AVATAR_LIST;
    }
}