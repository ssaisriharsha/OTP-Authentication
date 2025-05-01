package com.levyug.MobileOTPAuthentication.Service;

import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class VerificationService {
    private final String ACCOUNT_SID;
    private final String AUTH_TOKEN;
    private final String SERVICE_SID;
    public VerificationService(@Value("${twilio.account.sid}") String ACCOUNT_SID, @Value("${twilio.service.sid}") String SERVICE_SID, @Value("${twilio.auth.token}") String AUTH_TOKEN) {
        this.ACCOUNT_SID=ACCOUNT_SID;
        this.AUTH_TOKEN=AUTH_TOKEN;
        this.SERVICE_SID=SERVICE_SID;
    }
    @PostConstruct
    public void init() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }
    public void sendOtp(String mobileNumber) {
        Verification verification=Verification.creator(
                SERVICE_SID, mobileNumber, "sms"
        ).create();
    }
    public VerificationCheck validateOtp(String mobileNumber, String otp) {
        VerificationCheck verificationCheck=VerificationCheck.creator(SERVICE_SID)
                .setTo(mobileNumber)
                .setCode(otp)
                .create();
        return verificationCheck;
    }
}
