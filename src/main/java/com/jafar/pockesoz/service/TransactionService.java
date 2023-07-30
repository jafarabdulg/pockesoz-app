package com.jafar.pockesoz.service;

import com.jafar.pockesoz.model.request.TransactionRequest;
import com.jafar.pockesoz.model.response.TransactionResponse;

import java.util.List;

public interface TransactionService {
    TransactionResponse create(TransactionRequest request);
    List<TransactionResponse> getAll();
    TransactionResponse getById(String id);
}
