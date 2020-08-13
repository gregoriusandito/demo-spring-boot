package com.example.demo.controller;

import com.example.demo.domain.CheckSaldoResponse;
import com.example.demo.domain.TransferRequest;
import com.example.demo.service.AccountService;
import com.example.demo.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path="/account")
public class DemoApplicationController {

    @Autowired
    AccountService accountService;

    @Autowired
    TransferService transferService;

    @GetMapping(path = "/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CheckSaldoResponse checkSaldoResponse(@PathVariable Long accountNumber) {

        return accountService.getSaldo(accountNumber);
    }

    @PostMapping(path = "/{fromAccountNumber}/transfer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CheckSaldoResponse transfer(@PathVariable Long fromAccountNumber, HttpServletRequest request, @RequestBody TransferRequest transferRequest) {

        return transferService.transfer(fromAccountNumber, transferRequest);
    }
}
