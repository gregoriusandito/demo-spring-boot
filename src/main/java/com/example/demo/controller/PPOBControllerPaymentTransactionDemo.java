package com.example.demo.controller;

import com.example.demo.domain.PinRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(path="/ppob-transaction-service")
@Slf4j
public class PPOBControllerPaymentTransactionDemo {

    @PostMapping(path = "/v2/payment/{referenceNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> executeTransaction(@PathVariable String referenceNumber, @Validated @RequestBody PinRequest pinRequest) {
        String response = "{\n" +
                "    \"transactionStatus\": \"IN_PROGRESS\",\n" +
                "    \"state\": \"INCOMPLETE\",\n" +
                "    \"paymentMethod\": \"paylater\",\n" +
                "    \"category\": \"Airtime\",\n" +
                "    \"referenceNumber\": \""+ referenceNumber +"\",\n" +
                "    \"totalAmount\": 11500\n" +
                "}";

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/v2/payment/{referenceNumber}/status", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<String> transactionStatus(@PathVariable String referenceNumber) {
        String response = "{\n" +
                "  \"transactionStatus\": \"SUCCESS\",\n" +
                "  \"state\": \"COMPLETED\",\n" +
                "  \"paymentMethod\": \"paylater\",\n" +
                "  \"category\": \"Airtime\",\n" +
                "  \"customerNumber\": \"08172345678\",\n" +
                "  \"referenceNumber\": \"" + referenceNumber + "\",\n" +
                "  \"transactionAmount\": 10000,\n" +
                "  \"feeAmount\": 1500,\n" +
                "  \"totalAmount\": 11500,\n" +
                "  \"monthlyInstallmentAmount\": {\n" +
                "    \"value\": 500000,\n" +
                "    \"currency\": \"IDR\"\n" +
                "  },\n" +
                "  \"tenor\": 1,\n" +
                "  \"dueDate\": \"2021-12-08\",\n" +
                "  \"productDesc\": \"XL Prabayar 50K\",\n" +
                "  \"transactionDate\": \"2022-03-29\",\n" +
                "  \"code\": \"000000\",\n" +
                "  \"message\": \"Success\",\n" +
                "  \"additionalDataList\": null\n" +
                "}";

        return ResponseEntity.ok(response);
    }

}
