package com.yuexin.accounts.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


/**
 * Base auditable fields for JPA entities.
 * <p>
 * Marked as {@code @MappedSuperclass} so subclasses inherit these columns.
 * Values are filled by Spring Data JPA’s {@link org.springframework.data.jpa.domain.support.AuditingEntityListener}.
 *
 * <p><b>Note:</b> Enable auditing with {@code @EnableJpaAuditing} and provide
 * {@code AuditorAware<String>} to populate createdBy/updatedBy.
 */
@MappedSuperclass
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    /** Creation timestamp, set once when the entity is first persisted. */
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    /** Creator identifier (e.g., username), set on first persist. */
    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    /** Last update timestamp, refreshed on each modification. */
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    /** Last modifier identifier (e.g., username), refreshed on each update. */
    @LastModifiedBy
    @Column(insertable = false)
    private String updatedBy;
}