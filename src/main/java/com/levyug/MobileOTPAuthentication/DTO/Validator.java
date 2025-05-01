package com.levyug.MobileOTPAuthentication.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Validator {
    private String phoneNumber;
    private String otp;
}
