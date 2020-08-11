package com.example.demo.controller;

import com.example.demo.domain.CheckSaldoResponse;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/account")
public class DemoApplicationController {

    @Autowired
    AccountService accountService;

    @GetMapping(path = "/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CheckSaldoResponse checkSaldoResponse(@PathVariable Long accountNumber) {

        return accountService.getSaldo(accountNumber);
    }
}
