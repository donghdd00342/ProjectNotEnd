package com.donghd.notend.service.mapper;

import com.donghd.notend.domain.*;
import com.donghd.notend.service.dto.TransactionHistoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TransactionHistory and its DTO TransactionHistoryDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface TransactionHistoryMapper extends EntityMapper<TransactionHistoryDTO, TransactionHistory> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.firstName", target = "userFirstName")
    @Mapping(source = "user.lastName", target = "userLastName")
    @Mapping(source = "user.email", target = "userEmail")
    TransactionHistoryDTO toDto(TransactionHistory transactionHistory);

    @Mapping(source = "userId", target = "user")
    TransactionHistory toEntity(TransactionHistoryDTO transactionHistoryDTO);

    default TransactionHistory fromId(Long id) {
        if (id == null) {
            return null;
        }
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setId(id);
        return transactionHistory;
    }
}
