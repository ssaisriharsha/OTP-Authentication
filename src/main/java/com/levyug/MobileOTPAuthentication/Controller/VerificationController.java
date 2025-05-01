package com.levyug.MobileOTPAuthentication.Controller;

import com.levyug.MobileOTPAuthentication.DTO.PhNo;
import com.levyug.MobileOTPAuthentication.DTO.GetOtpResponse;
import com.levyug.MobileOTPAuthentication.Service.VerificationService;
import com.levyug.MobileOTPAuthentication.DTO.Validated;
import com.levyug.MobileOTPAuthentication.DTO.Validator;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins="https://levyug.com")
@RequestMapping("/api/v1/")
public class VerificationController {
    private final VerificationService service;
    public VerificationController(VerificationService service) {
        this.service=service;
    }
    @PostMapping("/get-otp")
    public ResponseEntity<?> sendOtp(@RequestBody PhNo phoneNumber) {
        service.sendOtp(phoneNumber.getPhoneNumber());
        GetOtpResponse res=new GetOtpResponse();
        res.setStatus("Sent");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @PostMapping("/validate-otp")
    public ResponseEntity<Validated> validateOtp(@RequestBody Validator validator) {
        VerificationCheck vc=service.validateOtp(validator.getPhoneNumber(), validator.getOtp());
        Validated validated=new Validated();
        validated.setStatus(vc.getStatus());
        return ResponseEntity.ok().body(validated);
    }
}
