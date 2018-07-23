package com.kids.crm.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResetPassword {
    private long userId;
    private String password;
    private String confirmPassword;
}
