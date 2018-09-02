package com.donghd.notend.service.impl;

import com.donghd.notend.service.TransactionHistoryService;
import com.donghd.notend.domain.TransactionHistory;
import com.donghd.notend.repository.TransactionHistoryRepository;
import com.donghd.notend.service.dto.TransactionHistoryDTO;
import com.donghd.notend.service.mapper.TransactionHistoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing TransactionHistory.
 */
@Service
@Transactional
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

    private final Logger log = LoggerFactory.getLogger(TransactionHistoryServiceImpl.class);

    private final TransactionHistoryRepository transactionHistoryRepository;

    private final TransactionHistoryMapper transactionHistoryMapper;

    public TransactionHistoryServiceImpl(TransactionHistoryRepository transactionHistoryRepository, TransactionHistoryMapper transactionHistoryMapper) {
        this.transactionHistoryRepository = transactionHistoryRepository;
        this.transactionHistoryMapper = transactionHistoryMapper;
    }

    /**
     * Save a transactionHistory.
     *
     * @param transactionHistoryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TransactionHistoryDTO save(TransactionHistoryDTO transactionHistoryDTO) {
        log.debug("Request to save TransactionHistory : {}", transactionHistoryDTO);
        TransactionHistory transactionHistory = transactionHistoryMapper.toEntity(transactionHistoryDTO);
        transactionHistory = transactionHistoryRepository.save(transactionHistory);
        return transactionHistoryMapper.toDto(transactionHistory);
    }

    /**
     * Get all the transactionHistories.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TransactionHistoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TransactionHistories");
        return transactionHistoryRepository.findAll(pageable)
            .map(transactionHistoryMapper::toDto);
    }


    /**
     * Get one transactionHistory by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TransactionHistoryDTO> findOne(Long id) {
        log.debug("Request to get TransactionHistory : {}", id);
        return transactionHistoryRepository.findById(id)
            .map(transactionHistoryMapper::toDto);
    }

    /**
     * Delete the transactionHistory by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TransactionHistory : {}", id);
        transactionHistoryRepository.deleteById(id);
    }
}
