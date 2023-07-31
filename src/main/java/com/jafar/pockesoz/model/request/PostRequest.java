package com.jafar.pockesoz.model.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PostRequest {

    private String id;

    @NotBlank
    private String content;

    @NotBlank
    private String userId;
}
