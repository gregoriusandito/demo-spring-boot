package com.example.demo.service;

import com.example.demo.domain.SaldoResponse;
import com.example.demo.domain.entity.Account;
import com.example.demo.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public SaldoResponse getSaldo(long accountNumber){
        SaldoResponse response = new SaldoResponse();
        Account finalAccount = accountRepository.findById(accountNumber).get();

        return response.builder()
                .accountNumber(finalAccount.getAccountNumber())
                .customerNumber(finalAccount.getCustomerNumber())
                .balance(finalAccount.getBalance())
                .build();

    }

    public void updateSaldo(SaldoResponse updatedSaldo){
        Account updatedAccount = new Account();
        updatedAccount.setBalance(updatedSaldo.getBalance());
        updatedAccount.setAccountNumber(updatedSaldo.getAccountNumber());
        updatedAccount.setCustomerNumber(updatedSaldo.getCustomerNumber());
        accountRepository.save(updatedAccount);
    }

}
