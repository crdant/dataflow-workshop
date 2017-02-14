package io.pivotal.pa.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;

import com.twilio.Twilio ;
import com.twilio.rest.api.v2010.account.Message ;
import com.twilio.type.PhoneNumber ;

import java.util.List ;
import java.util.ArrayList ;

@EnableBinding(Sink.class)
public class TwilioSMSSink {
    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "<REDACTED>";
    public static final String AUTH_TOKEN = "<REDACTED>";

    private static final Logger logger = LoggerFactory.getLogger(TwilioSMSSink.class);

    @ServiceActivator(inputChannel=Sink.INPUT)
    public void smsSink(String payload) {
        sendSms(payload);
    }

    private String sendSms(String text) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
            new PhoneNumber("<REDACTED>"),
            new PhoneNumber("+17813325378"),
            text
        ).create() ;
        return message.getSid();
    }

}
