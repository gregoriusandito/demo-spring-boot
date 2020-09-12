package com.example.demo.controller;

import com.example.demo.domain.SaldoResponse;
import com.example.demo.domain.TransferRequest;
import com.example.demo.service.AccountService;
import com.example.demo.service.CustomDetailsService;
import com.example.demo.service.TransferService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = DemoApplicationController.class)
public class DemoApplicationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AccountService accountService;

    @MockBean
    private TransferService transferService;

    @MockBean
    private CustomDetailsService customDetailsService;

    @MockBean
    private AuthorizationServerConfigurer authorizationServerConfigurer;

    private final Long accountNumber1 = 123456L;
    private final Long wrongAccountNumber = 1234561L;
    private final Long customerNumber1 = 10001L;

    @Before
    public void setUp() {
        BDDMockito.given(accountService.getSaldo(accountNumber1))
                .willReturn(getSampleSaldo(accountNumber1, customerNumber1));
    }

    private SaldoResponse getSampleSaldo(Long accountNumber, Long customerNumber){
        return SaldoResponse.builder()
                .balance(BigDecimal.valueOf(10000L))
                .accountNumber(accountNumber)
                .customerNumber(customerNumber)
                .build();
    }

    private TransferRequest getTransferRequest(){
        return TransferRequest.builder()
                .amount(BigDecimal.valueOf(10000L))
                .toAccountNumber(654321L)
                .build();
    }

    @Test
    public void checkSaldoResponse() throws Exception {
        mockMvc.perform(get("/api/account/{accountNumber}", accountNumber1)
        ).andExpect(status().isOk());

    }

    @Test
    public void checkSaldoResponse_expect404NotFound() throws Exception {
        mockMvc.perform(get("/api//acct/{accountNumber}", wrongAccountNumber))
                .andExpect(status().isNotFound());

    }

    @Test
    public void transferSuccess() throws Exception {
        mockMvc.perform(post("/api/account/{fromAccountNumber}/transfer", accountNumber1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(getTransferRequest()))
        ).andExpect(status().isOk());

    }


}