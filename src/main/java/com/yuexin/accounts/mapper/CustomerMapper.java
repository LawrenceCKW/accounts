package com.yuexin.accounts.mapper;

import com.yuexin.accounts.dto.CustomerDto;
import com.yuexin.accounts.entity.Customer;

/**
 * Utility mapper for converting between {@link Customer} entities and {@link CustomerDto}s.
 * <p>Stateless and non-instantiable.</p>
 */
public class CustomerMapper {
    private CustomerMapper() {}

    /**
     * Maps a {@link Customer} entity to a {@link CustomerDto}.
     *
     * @param customer the source entity (must not be {@code null})
     * @return a new {@link CustomerDto} with copied fields
     */
    public static CustomerDto mapToCustomerDto(Customer customer) {
        return CustomerDto.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .mobileNumber(customer.getMobileNumber())
                .build();
    }

    /**
     * Maps a {@link CustomerDto} to a {@link Customer} entity.
     *
     * @param customerDto the source DTO (must not be {@code null})
     * @return a new {@link Customer} with copied fields
     */
    public static Customer mapToCustomerEntity(CustomerDto customerDto) {
        return Customer.builder()
                .name(customerDto.getName())
                .email(customerDto.getEmail())
                .mobileNumber(customerDto.getMobileNumber())
                .build();
    }

    public static Customer mapToCustomerEntity(CustomerDto customerDto, Customer customerEntity) {
        customerEntity.setName(customerDto.getName());
        customerEntity.setEmail(customerDto.getEmail());
        customerEntity.setMobileNumber(customerDto.getMobileNumber());
        return customerEntity;
    }

}
