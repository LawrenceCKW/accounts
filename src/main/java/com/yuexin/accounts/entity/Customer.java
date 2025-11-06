package com.yuexin.accounts.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Customer entity.
 * <p>
 * Persists basic customer details and inherits audit fields from {@link BaseEntity}
 * (createdAt/createdBy/updatedAt/updatedBy via Spring Data auditing).
 *
 * <p><b>Schema</b>
 * <ul>
 *   <li><code>customer_id</code> – primary key (IDENTITY)</li>
 *   <li><code>name</code>
 *   <li><code>email</code></li>
 *   <li><code>mobile_number</code></li>
 * </ul>
 *
 * <p>Notes: consider adding validation annotations (e.g., {@code @NotBlank}, {@code @Email}),
 * unique constraints on email/mobile, and overriding {@code equals}/{@code hashCode} if needed.</p>
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Customer extends BaseEntity {

    /** Database-generated primary key (column: customer_id). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    /** Customer's name. */
    private String name;

    /** Customer's email address. */
    private String email;

    /** Customer's mobile phone number (column: mobile_number). */
    @Column(name = "mobile_number")
    private String mobileNumber;
}
