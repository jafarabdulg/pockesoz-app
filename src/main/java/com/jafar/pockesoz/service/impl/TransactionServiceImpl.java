package com.jafar.pockesoz.service.impl;

import com.jafar.pockesoz.entity.Pocket;
import com.jafar.pockesoz.entity.Transaction;
import com.jafar.pockesoz.entity.constant.TransType;
import com.jafar.pockesoz.model.request.TransactionRequest;
import com.jafar.pockesoz.model.response.TransactionResponse;
import com.jafar.pockesoz.repository.TransactionRepository;
import com.jafar.pockesoz.service.PocketService;
import com.jafar.pockesoz.service.TransactionService;
import com.jafar.pockesoz.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final PocketService pocketService;
    private final ValidationUtil validationUtil;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public TransactionResponse create(TransactionRequest request) {
        validationUtil.validate(request);

        Pocket thisPocket = pocketService.getPocketById(request.getThisPocketId());
        Pocket otherPocket = null;
        Transaction transaction = null;
        TransactionResponse transactionResponse = null;

        if (!request.getOtherPocketId().isBlank() && request.getOtherPocketId() != null ) {
            otherPocket = pocketService.getPocketById(request.getOtherPocketId());

            transaction = Transaction.builder()
                    .amount(request.getAmount())
                    .description(request.getDescription())
                    .type(TransType.get(request.getTransType()))
                    .time(LocalDateTime.now())
                    .thisPocket(thisPocket)
                    .otherPocket(otherPocket)
                    .build();
            transactionRepository.saveAndFlush(transaction);

            if (transaction.getType().name().equals("CREDIT")) {
                thisPocket.setBalance(thisPocket.getBalance() + transaction.getAmount());
                otherPocket.setBalance(otherPocket.getBalance() - transaction.getAmount());
            } else {
                thisPocket.setBalance(thisPocket.getBalance() - transaction.getAmount());
                otherPocket.setBalance(otherPocket.getBalance() + transaction.getAmount());
            }

            return TransactionResponse.builder()
                    .transId(transaction.getId())
                    .amount(transaction.getAmount())
                    .description(transaction.getDescription())
                    .transType(String.valueOf(transaction.getType()))
                    .time(transaction.getTime())
                    .thisPocketId(thisPocket.getId())
                    .otherPocketId(otherPocket.getId())
                    .build();

        } else {
            transaction = Transaction.builder()
                    .amount(request.getAmount())
                    .description(request.getDescription())
                    .type(TransType.get(request.getTransType()))
                    .time(LocalDateTime.now())
                    .thisPocket(thisPocket)
                    .build();
            transactionRepository.saveAndFlush(transaction);

            if (transaction.getType().name().equals("CREDIT")) {
                thisPocket.setBalance(thisPocket.getBalance() + transaction.getAmount());
            } else {
                thisPocket.setBalance(thisPocket.getBalance() - transaction.getAmount());
            }


            return TransactionResponse.builder()
                    .transId(transaction.getId())
                    .amount(transaction.getAmount())
                    .description(transaction.getDescription())
                    .transType(String.valueOf(transaction.getType()))
                    .time(transaction.getTime())
                    .thisPocketId(thisPocket.getId())
                    .build();
        }
    }

    @Override
    public List<TransactionResponse> getAll() {
        List<Transaction> transactions = transactionRepository.findAll();

        return transactions.stream()
                .map(this::trxToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionResponse getById(String id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "transaction not found"));

        return trxToResponse(transaction);
    }

    private TransactionResponse trxToResponse(Transaction transaction) {

        if (transaction.getOtherPocket() == null) {
            return TransactionResponse.builder()
                    .transId(transaction.getId())
                    .amount(transaction.getAmount())
                    .description(transaction.getDescription())
                    .transType(String.valueOf(transaction.getType()))
                    .time(transaction.getTime())
                    .thisPocketId(transaction.getThisPocket().getId())
                    .build();
        } else {
            return TransactionResponse.builder()
                    .transId(transaction.getId())
                    .amount(transaction.getAmount())
                    .description(transaction.getDescription())
                    .transType(String.valueOf(transaction.getType()))
                    .time(transaction.getTime())
                    .thisPocketId(transaction.getThisPocket().getId())
                    .otherPocketId(transaction.getOtherPocket().getId())
                    .build();
        }
    }
}
