package com.donghd.notend.service;

import com.donghd.notend.service.dto.TransactionHistoryDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing TransactionHistory.
 */
public interface TransactionHistoryService {

    /**
     * Save a transactionHistory.
     *
     * @param transactionHistoryDTO the entity to save
     * @return the persisted entity
     */
    TransactionHistoryDTO save(TransactionHistoryDTO transactionHistoryDTO);

    /**
     * Get all the transactionHistories.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TransactionHistoryDTO> findAll(Pageable pageable);


    /**
     * Get the "id" transactionHistory.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TransactionHistoryDTO> findOne(Long id);

    /**
     * Delete the "id" transactionHistory.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
