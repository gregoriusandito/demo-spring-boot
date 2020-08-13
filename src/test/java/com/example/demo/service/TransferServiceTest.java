package com.example.demo.service;

import com.example.demo.domain.SaldoResponse;
import com.example.demo.domain.TransferRequest;
import com.example.demo.exceptions.TransferException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.NumberUtils;

import java.math.BigDecimal;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TransferService.class)
public class TransferServiceTest {

    @Autowired
    TransferService transferService;

    @MockBean
    AccountService accountService;

    private final Long accountNumber = 123456L;
    private final Long customerNumber = 123456L;

    private final Long toAccountNumber = 112345L;
    private final Long toCustomerNumber = 112345L;


    @Before
    public void beforeTest() {
        Mockito.when(accountService.getSaldo(accountNumber))
                .thenReturn(getSampleSaldo(accountNumber, customerNumber));
        Mockito.when(accountService.getSaldo(toAccountNumber))
                .thenReturn(getSampleSaldo(toAccountNumber, toCustomerNumber));
    }

    private SaldoResponse getSampleSaldo(Long accountNumber, Long customerNumber) {
        return SaldoResponse.builder()
                .balance(BigDecimal.valueOf(10000L))
                .accountNumber(accountNumber)
                .customerNumber(customerNumber)
                .build();
    }

    private TransferRequest getTransferRequest(Long toAccountNumber, BigDecimal amount) {
        return TransferRequest
                .builder()
                .toAccountNumber(toAccountNumber)
                .amount(amount)
                .build();
    }

    @Test
    public void transfer_expectSuccess() {
        SaldoResponse transfer = transferService.transfer(accountNumber, getTransferRequest(toAccountNumber, BigDecimal.valueOf(500L)));
        Assert.assertNotNull(transfer);
        Assert.assertEquals(transfer.getBalance(), BigDecimal.valueOf(10500L));

    }

    @Test(expected = TransferException.class)
    public void transfer_expectInsufficientBalance() {
        transferService.transfer(accountNumber, getTransferRequest(toAccountNumber, BigDecimal.valueOf(50000L)));

    }


}
