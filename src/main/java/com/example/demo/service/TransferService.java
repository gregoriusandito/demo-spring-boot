package com.example.demo.service;

import com.example.demo.domain.CheckSaldoResponse;
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

    public CheckSaldoResponse transfer(Long fromAccountNumber, TransferRequest transferRequest){
        CheckSaldoResponse updateDestinationBalance = null;
        CheckSaldoResponse fromAccountDetail = null;
        CheckSaldoResponse toAccountDetail;

        try {
            fromAccountDetail = accountService.getSaldo(fromAccountNumber);
            toAccountDetail = accountService.getSaldo(transferRequest.getToAccountNumber());
        } catch (TransferException ex) {
            if (fromAccountDetail == null) {
                throw new TransferException("SRCNOTFOUND", "Source Account not Found", "ref");
            } else {
                throw new TransferException("DSTNOTFOUND", "Beneficiary Account not Found", "ref");
            }
        }

        if (transferRequest.getAmount().compareTo(fromAccountDetail.getBalance()) == -1 ) {
            throw new TransferException("INSUFBAL", "Insufficient Balance", "ref");
        } else {
            updateDestinationBalance.setAccountNumber(toAccountDetail.getAccountNumber());
            updateDestinationBalance.setBalance(toAccountDetail.getBalance().add(transferRequest.getAmount()));
            updateDestinationBalance.setCustomerNumber(toAccountDetail.getCustomerNumber());

            accountService.updateSaldo(updateDestinationBalance);
        }

        return updateDestinationBalance;
    }

}
