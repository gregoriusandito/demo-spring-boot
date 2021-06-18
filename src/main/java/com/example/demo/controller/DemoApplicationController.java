package com.example.demo.controller;

import com.example.demo.domain.SaldoResponse;
import com.example.demo.domain.TransferRequest;
import com.example.demo.exceptions.GeneralException;
import com.example.demo.service.AccountService;
import com.example.demo.service.TransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path="/account")
@Slf4j
public class DemoApplicationController {

    @Autowired
    AccountService accountService;

    @Autowired
    TransferService transferService;

    @GetMapping(path = "/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public SaldoResponse checkSaldoResponse(@PathVariable Long accountNumber) {

        return accountService.getSaldo(accountNumber);
    }

    @PostMapping(path = "/{fromAccountNumber}/transfer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<SaldoResponse> transfer(@PathVariable Long fromAccountNumber, HttpServletRequest request, @RequestBody TransferRequest transferRequest) {
        SaldoResponse transferResponse = SaldoResponse.builder().build();

        try {
            transferResponse = transferService.transfer(fromAccountNumber, transferRequest);
        } catch (GeneralException generalException) {
            throw generalException;
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
