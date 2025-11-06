package com.yuexin.accounts.service.implementation;

import com.yuexin.accounts.dto.*;
import com.yuexin.accounts.entity.*;
import com.yuexin.accounts.exception.*;
import com.yuexin.accounts.mapper.AccountsMapper;
import com.yuexin.accounts.repository.*;
import com.yuexin.accounts.service.IAccountsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.yuexin.accounts.constants.AccountsConstants.*;
import static com.yuexin.accounts.mapper.AccountsMapper.mapToAccountsDto;
import static com.yuexin.accounts.mapper.AccountsMapper.mapToAccountsEntity;
import static com.yuexin.accounts.mapper.CustomerMapper.mapToCustomerDto;
import static com.yuexin.accounts.mapper.CustomerMapper.mapToCustomerEntity;

/**
 * Service layer for account lifecycle operations (create, fetch, update, delete).
 * <p>
 * Coordinates {@link AccountsRepository} and {@link CustomerRepository} and uses DTO↔entity mappers.
 */
@Service
public class AccountsService implements IAccountsService {
    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    public AccountsService(AccountsRepository accountsRepository, CustomerRepository customerRepository) {
        this.accountsRepository = accountsRepository;
        this.customerRepository = customerRepository;
    }

    /**
     * Creates a new {@link Customer} and an associated {@link Accounts}.
     *
     * @param customerDto customer input (name/email/mobile + optional account)
     * @throws CustomerAlreadyExistsException if a customer with the same mobile number already exists
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = mapToCustomerEntity(customerDto);
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException(
                    "Customer already registered with provided mobile number: " + customerDto.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy(customerDto.getName());
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    /**
     * Fetches a customer's profile and linked account by mobile number.
     *
     * @param mobileNumber customer's mobile number
     * @return {@link CustomerDto} including embedded {@link AccountsDto}
     * @throws ResourceNotFoundException if the customer or the account is not found
     */
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Customer",  "mobileNumber", mobileNumber));
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));

        CustomerDto customerDto = mapToCustomerDto(customer);
        customerDto.setAccountsDto(mapToAccountsDto(accounts));
        return customerDto;
    }

    /**
     * Updates customer and account details.
     *
     * @param customerDto updated customer data (must include {@link AccountsDto} with accountNumber)
     * @return {@code true} if both customer and account were updated; otherwise {@code false}
     * @throws ResourceNotFoundException if the account (by accountNumber) or customer (by customerId) is not found
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdatedFlag = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();

        if (accountsDto != null) {
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            mapToAccountsEntity(accountsDto);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "customerId", customerId.toString())
            );
            mapToCustomerEntity(customerDto,  customer);
            customerRepository.save(customer);
            isUpdatedFlag = true;
        }
        return isUpdatedFlag;
    }

    /**
     * Deletes a customer and their associated account by mobile number.
     *
     * @param mobileNumber customer's mobile number
     * @return {@code true} on successful deletion
     * @throws ResourceNotFoundException if the customer does not exist
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

    /**
     * Builds a new {@link Accounts} for a saved customer with default type and branch,
     * and a random 10-digit account number.
     *
     * @param customer the persisted customer
     * @return a new {@link Accounts} entity linked to the customer
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts account = Accounts.builder()
                .customerId(customer.getCustomerId())
                .accountNumber(1000000000L + new Random().nextInt(900000000))
                .accountType(SAVINGS)
                .branchAddress(ADDRESS)
                .build();
        account.setCreatedAt(LocalDateTime.now());
        account.setCreatedBy(customer.getName());
        return account;
    }
}
