package com.yuexin.accounts.repository;

import com.yuexin.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for {@link Customer} entities.
 * <p>
 * Extends {@link org.springframework.data.jpa.repository.JpaRepository} to provide
 * CRUD operations, paging, and sorting. Spring Data generates the implementation
 * at runtime.
 *
 * <p><b>Notes</b>
 * <ul>
 *   <li>Method names derive queries automatically (e.g., {@code findByMobileNumber}).</li>
 *   <li>Consider adding a unique index on {@code mobile_number} for faster lookups
 *       and to enforce uniqueness.</li>
 * </ul>
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * Finds a customer by their mobile number.
     *
     * @param mobileNumber the mobile number to search for
     * @return an {@link Optional} containing the matching {@link Customer} if found, otherwise empty
     */
    Optional<Customer> findByMobileNumber(String mobileNumber);
}
