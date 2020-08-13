package com.example.demo.service;

import com.example.demo.domain.CheckSaldoResponse;
import com.example.demo.domain.entity.Account;
import com.example.demo.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Check;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;

import java.util.Optional;

@Service
@Slf4j
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public CheckSaldoResponse getSaldo(long accountNumber){
        CheckSaldoResponse response = new CheckSaldoResponse();
        Account finalAccount = accountRepository.findById(accountNumber).get();

        return response.builder()
                .accountNumber(finalAccount.getAccountNumber())
                .customerNumber(finalAccount.getCustomerNumber())
                .balance(finalAccount.getBalance())
                .build();

    }

    public void updateSaldo(CheckSaldoResponse updatedSaldo){
        Account updatedAccount = new Account();
        updatedAccount.setBalance(updatedSaldo.getBalance());
        updatedAccount.setAccountNumber(updatedSaldo.getAccountNumber());
        updatedAccount.setCustomerNumber(updatedSaldo.getCustomerNumber());
        accountRepository.save(updatedAccount);
    }

}
