package com.jafar.pockesoz.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TransactionResponse {

    private String transId;

    private Long amount;

    private  String description;

    private String transType;

    private LocalDateTime time;

    private String thisPocketId;

    private String otherPocketId;
}
