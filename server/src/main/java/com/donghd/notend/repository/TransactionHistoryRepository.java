package com.donghd.notend.repository;

import com.donghd.notend.domain.TransactionHistory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Spring Data  repository for the TransactionHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

    @Query("select transaction_history from TransactionHistory transaction_history where transaction_history.user.login = ?#{principal.username}")
    List<TransactionHistory> findByUserIsCurrentUser();

    @Query("select transaction_history from TransactionHistory transaction_history where transaction_history.user.login = ?#{principal.username}")
    Page<TransactionHistory> findAllByUserIsCurrentUser(Pageable pageable);

    @Query("select SUM(transaction_history.amout) from TransactionHistory transaction_history")
    BigDecimal sumAmount();

    @Query("select SUM(transaction_history.amout) from TransactionHistory transaction_history where MONTH(transaction_history.payDate) = MONTH(CURRENT_DATE)")
    BigDecimal sumAmountThisMonth();

}
