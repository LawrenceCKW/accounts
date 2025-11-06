package com.yuexin.accounts.mapper;

import com.yuexin.accounts.dto.AccountsDto;
import com.yuexin.accounts.dto.CustomerDto;
import com.yuexin.accounts.entity.Accounts;
import com.yuexin.accounts.entity.Customer;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {
    private Customer customerEntity;
    private CustomerDto customerDto;

    @BeforeEach
    void setup(TestInfo info) {
        System.out.println("[BEFORE EACH] STARTS...: " + info.getDisplayName());

        customerEntity = Customer.builder()
                .name("Wong Yue Xin")
                .email("yuexin@gmail.com")
                .mobileNumber("98989898")
                .build();

        customerDto = CustomerDto.builder()
                .name("Lawrence Chan")
                .email("chan@gmail.com")
                .mobileNumber("89898989")
                .build();
    }

    @AfterEach
    void tearDown(TestInfo info) {
        System.out.println("[AFTER EACH] ENDS...: " + info.getDisplayName());
        customerEntity = null;
        customerDto = null;
    }

    @Test
    @DisplayName("mapToCustomerDto: copies all fields")
    void mapToCustomerDto_shouldCopyAllFields() {
        CustomerDto dto = CustomerMapper.mapToCustomerDto(customerEntity);

        assertNotNull(dto);
        assertEquals(dto.getName(), customerEntity.getName());
        assertEquals(dto.getEmail(), customerEntity.getEmail());
        assertEquals(dto.getMobileNumber(), customerEntity.getMobileNumber());
    }

    @Test
    @DisplayName("mapToCustomerEntity: copies all fields")
    void mapToCustomerEntity_shouldCopyAllFields() {
        Customer entity = CustomerMapper.mapToCustomerEntity(customerDto);

        assertNotNull(entity);
        assertEquals(entity.getName(), customerDto.getName());
        assertEquals(entity.getEmail(), customerDto.getEmail());
        assertEquals(entity.getMobileNumber(), customerDto.getMobileNumber());
    }
}