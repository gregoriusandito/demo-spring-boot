package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/ppob-transaction-service")
@Slf4j
public class PPOBControllerInquiryTransactionDemo {

    @GetMapping(path = "/v2/inquiry/{referenceNumber}/detail", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<String> inquiryDetail(@PathVariable String referenceNumber) {

        String response = "{\n" +
                "  \"basicData\": {\n" +
                "    \"referenceNumber\": \"" + referenceNumber + "\",\n" +
                "    \"userId\": \"81299080768\",\n" +
                "    \"cuid\": \"2657720\",\n" +
                "    \"contractNumber\": \"4100009591\",\n" +
                "    \"ayoconnect\": true,\n" +
                "    \"kaspro\": true\n" +
                "  },\n" +
                "  \"inquiries\": [\n" +
                "    {\n" +
                "      \"transactionStatus\": \"NOT_AVAILABLE\",\n" +
                "      \"state\": \"INQUIRY\",\n" +
                "      \"productCode\": \"PLSAXL05\",\n" +
                "      \"productDesc\": \"Pulsa XL 5000\",\n" +
                "      \"billerName\": \"XL\",\n" +
                "      \"hcCode\": \"P001\",\n" +
                "      \"denomination\": \"5000\",\n" +
                "      \"category\": \"Airtime\",\n" +
                "      \"ewallet\": false,\n" +
                "      \"paylater\": true,\n" +
                "      \"priority\": 1,\n" +
                "      \"externalInquiryRefnum\": \"2448863\",\n" +
                "      \"transactionAmount\": 5000,\n" +
                "      \"feeAmount\": 1500,\n" +
                "      \"totalAmount\": 6500,\n" +
                "      \"additionalDataList\": null\n" +
                "    },\n" +
                "    {\n" +
                "      \"transactionStatus\": \"NOT_AVAILABLE\",\n" +
                "      \"state\": \"INQUIRY\",\n" +
                "      \"productCode\": \"XL5\",\n" +
                "      \"productDesc\": \"XL 5000\",\n" +
                "      \"billerName\": \"XL\",\n" +
                "      \"hcCode\": \"P001\",\n" +
                "      \"denomination\": \"5000\",\n" +
                "      \"category\": \"Airtime\",\n" +
                "      \"ewallet\": true,\n" +
                "      \"paylater\": false,\n" +
                "      \"priority\": 2,\n" +
                "      \"externalInquiryRefnum\": \"0005277886102\",\n" +
                "      \"transactionAmount\": 5000,\n" +
                "      \"feeAmount\": 2500,\n" +
                "      \"totalAmount\": 7500,\n" +
                "      \"additionalDataList\": null\n" +
                "    }\n" +
                "  ],\n" +
                "  \"hcPayAccount\": {\n" +
                "    \"customerName\": \"Zakia Approved\",\n" +
                "    \"hcPayStatus\": \"LINKED\",\n" +
                "    \"insuranceStatus\": false,\n" +
                "    \"hcPayAccountBalance\": {\n" +
                "      \"accountStatus\": \"0\",\n" +
                "      \"balance\": 237800,\n" +
                "      \"pocketId\": 2\n" +
                "    }\n" +
                "  },\n" +
                "  \"payLaterAccount\": {\n" +
                "    \"cuid\": \"2657720\",\n" +
                "    \"customerName\": \"Zakia Approved\",\n" +
                "    \"payLaterStatus\": \"ACTIVE\",\n" +
                "    \"pastDueStatus\": \"true\",\n" +
                "    \"insuranceStatus\": false,\n" +
                "    \"billingStatus\": \"ON_GOING\",\n" +
                "    \"payLaterAccountBalance\": {\n" +
                "      \"contractNumber\": \"4100009591\",\n" +
                "      \"dueDate\": \"2022-04-15\",\n" +
                "      \"availableBalance\": 51050,\n" +
                "      \"totalBalance\": 800000,\n" +
                "      \"usedBalance\": 748950\n" +
                "    }\n" +
                "  },\n" +
                "  \"installmentPlanOffer\": [\n" +
                "    {\n" +
                "      \"offerCode\": \"d1851e26-c663-1e66-e053-0c123c0a3780\",\n" +
                "      \"transactionType\": \"PLTRX\",\n" +
                "      \"tenor\": 1,\n" +
                "      \"monthlyInstallmentAmount\": {\n" +
                "        \"value\": 6500,\n" +
                "        \"currency\": \"IDR\"\n" +
                "      },\n" +
                "      \"installmentFeeAmount\": {\n" +
                "        \"value\": 5000,\n" +
                "        \"currency\": \"IDR\"\n" +
                "      },\n" +
                "      \"monthlyInterestRate\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"offerCode\": \"d1851e26-c662-1e66-e053-0c123c0a3780\",\n" +
                "      \"transactionType\": \"PLTRX\",\n" +
                "      \"tenor\": 3,\n" +
                "      \"monthlyInstallmentAmount\": {\n" +
                "        \"value\": 181238,\n" +
                "        \"currency\": \"IDR\"\n" +
                "      },\n" +
                "      \"installmentFeeAmount\": {\n" +
                "        \"value\": 5000,\n" +
                "        \"currency\": \"IDR\"\n" +
                "      },\n" +
                "      \"monthlyInterestRate\": 3.49\n" +
                "    },\n" +
                "    {\n" +
                "      \"offerCode\": \"d1851e29-48ed-750d-e053-0c123c0ad016\",\n" +
                "      \"transactionType\": \"PLTRX\",\n" +
                "      \"tenor\": 6,\n" +
                "      \"monthlyInstallmentAmount\": {\n" +
                "        \"value\": 95231,\n" +
                "        \"currency\": \"IDR\"\n" +
                "      },\n" +
                "      \"installmentFeeAmount\": {\n" +
                "        \"value\": 5000,\n" +
                "        \"currency\": \"IDR\"\n" +
                "      },\n" +
                "      \"monthlyInterestRate\": 3.49\n" +
                "    },\n" +
                "    {\n" +
                "      \"offerCode\": \"d1851e29-48ec-750d-e053-0c123c0ad016\",\n" +
                "      \"transactionType\": \"PLTRX\",\n" +
                "      \"tenor\": 9,\n" +
                "      \"monthlyInstallmentAmount\": {\n" +
                "        \"value\": 66690,\n" +
                "        \"currency\": \"IDR\"\n" +
                "      },\n" +
                "      \"installmentFeeAmount\": {\n" +
                "        \"value\": 5000,\n" +
                "        \"currency\": \"IDR\"\n" +
                "      },\n" +
                "      \"monthlyInterestRate\": 3.49\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/v2/inquiry/{referenceNumber}/{paymentMethod}/confirm", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> confirmTransaction(@PathVariable String referenceNumber, @PathVariable String paymentMethod) {
        String response = "";

        return ResponseEntity.ok(response);
    }

}
