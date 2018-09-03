package com.donghd.notend.web.rest;

import com.donghd.notend.service.TransactionHistoryService;
import com.donghd.notend.web.rest.errors.BadRequestAlertException;
import com.donghd.notend.web.rest.util.HeaderUtil;
import com.donghd.notend.web.rest.util.PaginationUtil;
import com.donghd.notend.service.dto.TransactionHistoryDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing TransactionHistory.
 */
@RestController
@RequestMapping("/api")
public class TransactionHistoryResource {

    private final Logger log = LoggerFactory.getLogger(TransactionHistoryResource.class);

    private static final String ENTITY_NAME = "transactionHistory";

    private final TransactionHistoryService transactionHistoryService;

    public TransactionHistoryResource(TransactionHistoryService transactionHistoryService) {
        this.transactionHistoryService = transactionHistoryService;
    }

    /**
     * GET  /transaction-histories : get all the transactionHistories.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of transactionHistories in body
     */
    @GetMapping("/transaction-histories")
    public ResponseEntity<List<TransactionHistoryDTO>> getAllTransactionHistories(Pageable pageable) {
        log.debug("REST request to get a page of TransactionHistories");
        Page<TransactionHistoryDTO> page = transactionHistoryService.findAllByUserIsCurrentUser(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/transaction-histories");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}
