package com.example.demo.service;

import com.example.demo.domain.SaldoResponse;
import com.example.demo.domain.TransferRequest;
import com.example.demo.exceptions.GeneralException;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TransferService.class)
public class TransferServiceTest {

    @Autowired
    TransferService transferService;

    @MockBean
    AccountService accountService;

    private final Long accountNumber = 123456L;
    private final String customerName = "Linus Torvald";

    private final Long toAccountNumber = 112345L;
    private final String toCustomerName = "Linus Torvald";

    @Before
    public void beforeTest() {
        Mockito.when(accountService.getSaldo(accountNumber))
                .thenReturn(getSampleSaldo(accountNumber, customerName));
        Mockito.when(accountService.getSaldo(toAccountNumber))
                .thenReturn(getSampleSaldo(toAccountNumber, toCustomerName));
    }

    private SaldoResponse getSampleSaldo(Long accountNumber, String customerName) {
        return SaldoResponse.builder()
                .balance(BigDecimal.valueOf(10000L))
                .accountNumber(accountNumber)
                .customerName(customerName)
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

    @Test(expected = GeneralException.class)
    public void transfer_expectInsufficientBalance() {
        transferService.transfer(accountNumber, getTransferRequest(toAccountNumber, BigDecimal.valueOf(50000L)));
    }


}
