package com.jafar.pockesoz.model.response;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder(toBuilder = true)
public class PocketResponse {

    private String id;

    private String name;

    private Long balance;

    private String type;

    private UserResponse userResponse;
}
