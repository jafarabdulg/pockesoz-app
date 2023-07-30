package com.jafar.pockesoz.entity.constant;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public enum PocketType {
    MAIN,
    SAVING,
    INVESTING;

    public static PocketType get(String value) {
        for (PocketType pocketType : PocketType.values()) {
            if (pocketType.name().equalsIgnoreCase(value)) return pocketType;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "pocket type not found");
    }
}
