package org.gfg.expenseTracker.repository;

import org.gfg.expenseTracker.model.TxnDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TxnRepository extends JpaRepository<TxnDetails, Long> {
    public List<TxnDetails> findByExpenseTypesIn(String[] expenseType);

    public List<TxnDetails>findByExpenseTypesId(String[] expenseTypeIds);

    public List<TxnDetails> findByExpenditureAmount(Double value);

    public List<TxnDetails> findByExpenditureAmountLessThanEqual(Double value);

    public List<TxnDetails> findByExpenseDateLessThanEqual(Date date);

    @Query(value = "select SUM(t.expenditure_amount) from txn_details t inner join user u where t.user_id = u.id and t.expense_date >=:date and u.id = :userId", nativeQuery = true)
    public Double getAggregatedData(LocalDate date, Integer userId);
}
