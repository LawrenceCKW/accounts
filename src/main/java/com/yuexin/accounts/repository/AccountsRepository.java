package com.yuexin.accounts.repository;

import com.yuexin.accounts.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Repository for {@link Accounts} entities.
 * <p>
 * Extends {@link org.springframework.data.jpa.repository.JpaRepository} to provide
 * CRUD, paging, and sorting operations. Spring Data generates the implementation.
 *
 * <p><b>Notes</b>:
 * <ul>
 *   <li>Derives queries from method names (e.g., {@code findByCustomerId}).</li>
 *   <li>Consider a unique index on the {@code customer_id} column if one account per customer is enforced.</li>
 * </ul>
 */
@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    /**
     * Finds the account associated with the given customer id.
     *
     * @param customerId the owning customer's id
     * @return an {@link Optional} containing the matching {@link Accounts}, or empty if none
     */
    Optional<Accounts> findByCustomerId(Long customerId);

    /**
     * Deletes the account associated with the given customer id.
     * <p>
     * Marked {@link org.springframework.transaction.annotation.Transactional} and
     * {@link org.springframework.data.jpa.repository.Modifying} because it performs a write operation.
     *
     * @param customerId the owning customer's id
     */
    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId);
}
