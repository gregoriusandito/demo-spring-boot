package com.example.demo.service;

import com.example.demo.domain.CheckSaldoResponse;
import com.example.demo.domain.entity.Account;
import com.example.demo.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public CheckSaldoResponse getSaldo(long accountNumber){
        CheckSaldoResponse response = new CheckSaldoResponse();
        Optional<Account> account = accountRepository.findById(accountNumber);
        Account finalAccount = account.get();

        return response.builder()
                .accountNumber(finalAccount.getAccountNumber())
                .customerNumber(finalAccount.getCustomerNumber())
                .balance(finalAccount.getBalance())
                .build();

    }
}
