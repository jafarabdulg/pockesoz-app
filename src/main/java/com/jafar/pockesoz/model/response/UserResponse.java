package com.jafar.pockesoz.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserResponse {

    private String userId;
    private String name;
    private String email;
}
