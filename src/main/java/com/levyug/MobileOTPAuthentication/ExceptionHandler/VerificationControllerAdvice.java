package com.levyug.MobileOTPAuthentication.ExceptionHandler;

import com.twilio.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;

@RestControllerAdvice
public class VerificationControllerAdvice {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<HashMap<String, String>> handleApiException(ApiException e) {
        HashMap<String, String> map=new HashMap<>();
        map.put("message", "Invalid OTP. Please request a new OTP and try logging in again.");
        map.put("timestamp", LocalDateTime.now().toString());
        map.put("error", "The Requested Resource was not found.");
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }
}
