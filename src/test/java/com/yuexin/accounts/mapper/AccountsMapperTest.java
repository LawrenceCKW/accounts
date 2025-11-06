package com.yuexin.accounts.mapper;

import com.yuexin.accounts.dto.AccountsDto;
import com.yuexin.accounts.entity.Accounts;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AccountsMapperTest {
    private AccountsDto accountsDto;
    private Accounts accountsEntity;

    @BeforeEach
    void setup(TestInfo info) {
        System.out.println("[BEFORE EACH] STARTS...: " + info.getDisplayName());

        accountsEntity = Accounts.builder()
                .accountNumber(5501234321L)
                .accountType("SAVINGS")
                .branchAddress("123C Bishan")
                .build();

        accountsDto = AccountsDto.builder()
                .accountNumber(5501234322L)
                .accountType("CURRENT")
                .branchAddress("12C City Hall")
                .build();

    }

    @AfterEach
    void tearDown(TestInfo info) {
        System.out.println("[AFTER EACH] ENDS...: " + info.getDisplayName());
        accountsDto = null;
        accountsEntity = null;
    }

    @Test
    @DisplayName("mapToAccountsEntity: copies all fields")
    void mapToAccountsEntity_shouldCopyAllFields() {
        Accounts entity = AccountsMapper.mapToAccountsEntity(accountsDto);

        assertNotNull(entity);
        assertEquals(entity.getAccountNumber(), accountsDto.getAccountNumber());
        assertEquals(entity.getAccountType(), accountsDto.getAccountType());
        assertEquals(entity.getBranchAddress(), accountsDto.getBranchAddress());
    }

    @Test
    @DisplayName("mapToAccountsDto: copies all fields")
    void mapToAccountsDto_shouldCopyAllFields() {
        AccountsDto dto = AccountsMapper.mapToAccountsDto(accountsEntity);

        assertNotNull(dto);
        assertEquals(dto.getAccountNumber(), accountsEntity.getAccountNumber());
        assertEquals(dto.getAccountType(), accountsEntity.getAccountType());
        assertEquals(dto.getBranchAddress(), accountsEntity.getBranchAddress());
    }
}