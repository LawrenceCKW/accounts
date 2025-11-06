package com.yuexin.accounts.mapper;

import com.yuexin.accounts.dto.AccountsDto;
import com.yuexin.accounts.entity.Accounts;

/**
 * Utility mapper for converting between {@link AccountsDto} and {@link Accounts}.
 * <p>Stateless and non-instantiable.</p>
 */
public class AccountsMapper {
    private AccountsMapper() {}

    /**
     * Maps an {@link AccountsDto} to an {@link Accounts} entity.
     *
     * @param accountsDto source DTO (must not be {@code null})
     * @return a new {@link Accounts} populated from the DTO
     */
    public static Accounts mapToAccountsEntity(AccountsDto accountsDto) {
        return Accounts.builder()
                .accountNumber(accountsDto.getAccountNumber())
                .accountType(accountsDto.getAccountType())
                .branchAddress(accountsDto.getBranchAddress())
                .build();
    }

    /**
     * Maps an {@link Accounts} entity to an {@link AccountsDto}.
     *
     * @param accounts source entity (must not be {@code null})
     * @return a new {@link AccountsDto} populated from the entity
     */
    public static AccountsDto mapToAccountsDto(Accounts accounts) {
        return AccountsDto.builder()
                .accountNumber(accounts.getAccountNumber())
                .accountType(accounts.getAccountType())
                .branchAddress(accounts.getBranchAddress())
                .build();
    }
}
