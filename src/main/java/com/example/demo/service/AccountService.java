package com.example.demo.service;

import com.example.demo.domain.SaldoResponse;
import com.example.demo.domain.entity.Account;
import com.example.demo.domain.entity.Customer;
import com.example.demo.exceptions.GeneralException;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Slf4j
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CustomerRepository customerRepository;

    public SaldoResponse getSaldo(long accountNumber){
        SaldoResponse response = new SaldoResponse();

        try {
            Account finalAccount = accountRepository.findById(accountNumber).get();
            accountRepository.findAll();
            Customer finalCustomer = customerRepository.findById(finalAccount.getCustomerNumber()).get();
            log.info("getall: {}", customerRepository.findAll());
            return response.builder()
                    .accountNumber(finalAccount.getAccountNumber())
                    .customerName(finalCustomer.getCustomerName())
                    .balance(finalAccount.getBalance())
                    .customerNumber(finalAccount.getCustomerNumber())
                    .build();
        } catch (NoSuchElementException ex) {
            throw new GeneralException("NOACCFOUND", "No Account Found", "NAF".concat(generateRandomRef().toString()));
        }

    }

    public void updateSaldo(SaldoResponse updatedSaldo, Long customerNumber){
        Account updatedAccount = new Account();
        updatedAccount.setBalance(updatedSaldo.getBalance());
        updatedAccount.setAccountNumber(updatedSaldo.getAccountNumber());
        updatedAccount.setCustomerNumber(customerNumber);
        accountRepository.save(updatedAccount);
    }

    private Integer generateRandomRef() {
        int min = 1000;
        int max = 10000;
        int randomInt;
        return randomInt = (int)(Math.random() * (max - min + 1) + min);
    }

}
