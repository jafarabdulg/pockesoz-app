package com.jafar.pockesoz.model.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TransactionRequest {

    @NotNull(message = "amount is required")
    @Min(value = 0, message = "amount must be greater than 0")
    private Long amount;

    private String description;

    @NotBlank(message = "transType is required")
    private String transType;

    @NotBlank(message = "thisPocketId is required")
    private String thisPocketId;

    private String otherPocketId;
}
