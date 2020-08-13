package com.example.demo.service;

import com.example.demo.domain.SaldoResponse;
import com.example.demo.domain.TransferRequest;
import com.example.demo.exceptions.TransferException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransferService {
    @Autowired
    AccountService accountService;

    public SaldoResponse transfer(Long fromAccountNumber, TransferRequest transferRequest){
        SaldoResponse updateDestinationBalance = null;
        SaldoResponse fromAccountDetail = null;
        SaldoResponse toAccountDetail;

        try {
            fromAccountDetail = accountService.getSaldo(fromAccountNumber);
            toAccountDetail = accountService.getSaldo(transferRequest.getToAccountNumber());
        } catch (TransferException ex) {
            if (fromAccountDetail == null) {
                throw new TransferException("SRCNOTFOUND", "Source Account not Found", "SAF".concat(generateRandomRef().toString()));
            } else {
                throw new TransferException("DSTNOTFOUND", "Beneficiary Account not Found", "BAF".concat(generateRandomRef().toString()));
            }
        }

        if (transferRequest.getAmount().compareTo(fromAccountDetail.getBalance()) == -1 ) {
            throw new TransferException("INSUFBAL", "Insufficient Balance", "IBA".concat(generateRandomRef().toString()));
        } else {
            updateDestinationBalance.setAccountNumber(toAccountDetail.getAccountNumber());
            updateDestinationBalance.setBalance(toAccountDetail.getBalance().add(transferRequest.getAmount()));
            updateDestinationBalance.setCustomerNumber(toAccountDetail.getCustomerNumber());

            accountService.updateSaldo(updateDestinationBalance);
        }

        return updateDestinationBalance;
    }

    private Integer generateRandomRef() {
        int min = 1000;
        int max = 10000;
        int randomInt;
        return randomInt = (int)(Math.random() * (max - min + 1) + min);
    }

}
