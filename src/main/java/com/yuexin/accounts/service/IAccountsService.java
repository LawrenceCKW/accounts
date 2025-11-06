package com.yuexin.accounts.service;

import com.yuexin.accounts.dto.AccountsDto;
import com.yuexin.accounts.dto.CustomerDto;

import java.util.List;

public interface IAccountsService {

    void createAccount(CustomerDto customerDto);
    CustomerDto fetchAccount(String mobileNumber);
    boolean updateAccount(CustomerDto customerDto);
    boolean deleteAccount(String mobileNumber);

}
