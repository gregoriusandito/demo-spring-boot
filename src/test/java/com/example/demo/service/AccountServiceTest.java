package com.example.demo.service;

import com.example.demo.domain.SaldoResponse;
import com.example.demo.domain.entity.Account;
import com.example.demo.repository.AccountRepository;
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

    private final Long accountNumber = 123456L;
    private final Long customerNumber = 123456L;

    @Before
    public void beforeTest() {
        Mockito.when(accountRepository.findById(accountNumber))
                .thenReturn(Optional.of(getAccount(accountNumber, customerNumber)));
    }

    private Account getAccount(Long accountNumber, Long customerNumber) {
        Account accountResult = new Account();
        accountResult.setAccountNumber(accountNumber);
        accountResult.setBalance(BigDecimal.valueOf(10000L));
        accountResult.setCustomerNumber(customerNumber);

        return accountResult;
    }

    private SaldoResponse getSampleSaldo(Long accountNumber, Long customerNumber){
        return SaldoResponse.builder()
                .balance(BigDecimal.valueOf(10000L))
                .accountNumber(accountNumber)
                .customerNumber(customerNumber)
                .build();
    }

    @Test
    public void getSaldoTest() {
        SaldoResponse saldo = accountService.getSaldo(accountNumber);
        Assert.assertNotNull(saldo);
        Assert.assertEquals((long) accountNumber, (long) saldo.getAccountNumber());
        Assert.assertEquals((long) customerNumber, (long) saldo.getCustomerNumber());
    }

    @Test
    public void updateSaldoTest() {
        accountService.updateSaldo(getSampleSaldo(accountNumber, customerNumber));
        Assert.assertNotNull(accountRepository.findById(accountNumber));
    }

}
