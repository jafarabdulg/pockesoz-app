package com.jafar.pockesoz.entity.constant;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public enum TransType {
    CREDIT,
    DEBIT;

    public static TransType get(String value) {
        for (TransType transType : TransType.values()) {
            if (transType.name().equalsIgnoreCase(value)) return transType;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "trans type not found");
    }
}
