package com.example.demo.controller;

import com.example.demo.domain.SaldoResponse;
import com.example.demo.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DemoApplicationController.class)
class DemoApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    private final Long accountNumber1 = 123456L;
    private final Long wrongAccountNumber = 1234561L;
    private final Long customerNumber1 = 10001L;

    @BeforeEach
    void setUp() {
        BDDMockito.given(accountService.getSaldo(accountNumber1))
                .willReturn(getSampleSaldo());
    }

    private SaldoResponse getSampleSaldo(){
        return SaldoResponse.builder()
                .balance(BigDecimal.valueOf(10000L))
                .accountNumber(accountNumber1)
                .customerNumber(customerNumber1)
                .build();
    }

    @Test
    public void checkSaldoResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/account/{accountNumber}", accountNumber1))
                .andExpect(status().isOk());

    }

    @Test
    public void checkSaldoResponse_expect404NotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/acct/{accountNumber}", wrongAccountNumber))
                .andExpect(status().isNotFound());

    }


}