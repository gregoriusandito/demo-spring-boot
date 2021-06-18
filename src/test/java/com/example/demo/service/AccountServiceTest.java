package com.example.demo.service;

import com.example.demo.domain.SaldoResponse;
import com.example.demo.domain.entity.Account;
import com.example.demo.domain.entity.Customer;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccountService.class)
public class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @MockBean
    AccountRepository accountRepository;

    @MockBean
    CustomerRepository customerRepository;

    private final Long accountNumber = 123456L;
    private final String customerName = "Linus Torvald";

    @Before
    public void beforeTest() {
        Mockito.when(accountRepository.findById(accountNumber))
                .thenReturn(Optional.of(getAccount(accountNumber)));
        Mockito.when(customerRepository.findById(123L))
                .thenReturn(Optional.of(getCustomer(123L, customerName)));
    }

    private Account getAccount(Long accountNumber) {
        Account accountResult = new Account();
        accountResult.setAccountNumber(accountNumber);
        accountResult.setBalance(BigDecimal.valueOf(10000L));
        accountResult.setCustomerNumber(123L);

        return accountResult;
    }

    private Customer getCustomer(Long customerNumber, String customerName) {
        Customer customerResult = new Customer();
        customerResult.setCustomerName(customerName);
        customerResult.setCustomerNumber(customerNumber);

        return customerResult;
    }

    private SaldoResponse getSampleSaldo(Long accountNumber, String customerName){
        return SaldoResponse.builder()
                .balance(BigDecimal.valueOf(10000L))
                .accountNumber(accountNumber)
                .customerName(customerName)
                .build();
    }

    @Test
    public void getSaldoTest() {
        SaldoResponse saldo = accountService.getSaldo(accountNumber);
        Assert.assertNotNull(saldo);
        Assert.assertEquals((long) accountNumber, (long) saldo.getAccountNumber());
        Assert.assertEquals(customerName, saldo.getCustomerName());
    }

    @Test
    public void updateSaldoTest() {
        accountService.updateSaldo(getSampleSaldo(accountNumber, customerName));
        Assert.assertNotNull(accountRepository.findById(accountNumber));
    }

}
