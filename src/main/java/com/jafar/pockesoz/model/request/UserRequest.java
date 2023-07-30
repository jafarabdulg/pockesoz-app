package com.jafar.pockesoz.model.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserRequest {

    private String id;

    @NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "name is  required")
    private String name;

//    @NotBlank(message = "password is required")
//    private String password;
}
