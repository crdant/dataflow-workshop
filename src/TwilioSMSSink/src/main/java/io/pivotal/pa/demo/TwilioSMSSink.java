package io.pivotal.pa.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;

import com.twilio.Twilio ;
import com.twilio.rest.api.v2010.account.Message ;
import com.twilio.type.PhoneNumber ;

import java.util.List ;
import java.util.ArrayList ;

/**
 * A sink that sends an SMS
 *
 * @author Chuck D'Antonio
 */
@EnableBinding(Sink.class)
@EnableConfigurationProperties(TwilioSMSSinkProperties.class)
public class TwilioSMSSink {
    private static final Logger logger = LoggerFactory.getLogger(TwilioSMSSink.class);

    @Autowired
    private TwilioSMSSinkProperties properties;

    @ServiceActivator(inputChannel=Sink.INPUT)
    public void smsSink(String payload) {
        sendSms(payload);
    }

    private String sendSms(String text) {
        Twilio.init(properties.getSid(), properties.getToken());

        Message message = Message.creator(
            new PhoneNumber(properties.getTo()),
            new PhoneNumber(properties.getFrom()),
            text
        ).create() ;
        return message.getSid();
    }

}
