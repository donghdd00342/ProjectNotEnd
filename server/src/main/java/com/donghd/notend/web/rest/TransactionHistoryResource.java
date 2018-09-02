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
     * POST  /transaction-histories : Create a new transactionHistory.
     *
     * @param transactionHistoryDTO the transactionHistoryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new transactionHistoryDTO, or with status 400 (Bad Request) if the transactionHistory has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/transaction-histories")
    public ResponseEntity<TransactionHistoryDTO> createTransactionHistory(@Valid @RequestBody TransactionHistoryDTO transactionHistoryDTO) throws URISyntaxException {
        log.debug("REST request to save TransactionHistory : {}", transactionHistoryDTO);
        if (transactionHistoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new transactionHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TransactionHistoryDTO result = transactionHistoryService.save(transactionHistoryDTO);
        return ResponseEntity.created(new URI("/api/transaction-histories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /transaction-histories : Updates an existing transactionHistory.
     *
     * @param transactionHistoryDTO the transactionHistoryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated transactionHistoryDTO,
     * or with status 400 (Bad Request) if the transactionHistoryDTO is not valid,
     * or with status 500 (Internal Server Error) if the transactionHistoryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/transaction-histories")
    public ResponseEntity<TransactionHistoryDTO> updateTransactionHistory(@Valid @RequestBody TransactionHistoryDTO transactionHistoryDTO) throws URISyntaxException {
        log.debug("REST request to update TransactionHistory : {}", transactionHistoryDTO);
        if (transactionHistoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TransactionHistoryDTO result = transactionHistoryService.save(transactionHistoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, transactionHistoryDTO.getId().toString()))
            .body(result);
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
        Page<TransactionHistoryDTO> page = transactionHistoryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/transaction-histories");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /transaction-histories/:id : get the "id" transactionHistory.
     *
     * @param id the id of the transactionHistoryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the transactionHistoryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/transaction-histories/{id}")
    public ResponseEntity<TransactionHistoryDTO> getTransactionHistory(@PathVariable Long id) {
        log.debug("REST request to get TransactionHistory : {}", id);
        Optional<TransactionHistoryDTO> transactionHistoryDTO = transactionHistoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(transactionHistoryDTO);
    }

    /**
     * DELETE  /transaction-histories/:id : delete the "id" transactionHistory.
     *
     * @param id the id of the transactionHistoryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/transaction-histories/{id}")
    public ResponseEntity<Void> deleteTransactionHistory(@PathVariable Long id) {
        log.debug("REST request to delete TransactionHistory : {}", id);
        transactionHistoryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
