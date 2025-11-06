package com.yuexin.accounts.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Bank account entity.
 * <p>
 * Inherits audit fields from {@link BaseEntity}. Uses {@code accountNumber} as the
 * primary key (no auto-generation) and stores a foreign key reference to the owning
 * customer via {@code customerId}.
 *
 * <p><b>Columns</b>
 * <ul>
 *   <li><code>customer_id</code> – id of the owning customer</li>
 *   <li><code>account_number</code> – primary key</li>
 *   <li><code>account_type</code> – e.g., Savings, Current</li>
 *   <li><code>branch_address</code> – branch location/address</li>
 * </ul>
 *
 * <p>Consider mapping a relation to {@code Customer} (e.g., {@code @ManyToOne}) instead of
 * storing a raw id, and adding indexes/constraints as needed.</p>
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Accounts extends  BaseEntity {

    /** Owning customer's id (foreign key reference). */
    @Column(name = "customer_id")
    private Long customerId;

    /** Account number used as the primary key (no auto-generation). */
    @Id
    @Column(name = "account_number")
    private Long accountNumber;

    /** Account type (e.g., Savings, Current). */
    @Column(name = "account_type")
    private String accountType;

    /** Branch address associated with the account. */
    @Column(name = "branch_address")
    private String branchAddress;

}
