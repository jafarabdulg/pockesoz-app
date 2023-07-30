package com.jafar.pockesoz.controller;

import com.jafar.pockesoz.model.request.TransactionRequest;
import com.jafar.pockesoz.model.response.CommonResponse;
import com.jafar.pockesoz.model.response.TransactionResponse;
import com.jafar.pockesoz.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody TransactionRequest request) {
        TransactionResponse transactionResponse = transactionService.create(request);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("success created transaction")
                .data(transactionResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commonResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAllTransaction() {
        List<TransactionResponse> transactionResponses = transactionService.getAll();
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("success get transaction")
                .data(transactionResponses)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable String id) {
        TransactionResponse transactionResponse = transactionService.getById(id);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("success get transaction")
                .data(transactionResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }
}
