package io.pivotal.pa.demo;

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;

import java.io.IOException;

@EnableBinding(Sink.class)
public class SlackSink {
    public static final String BOT_TOKEN = "xoxb-156181098692-lESybSEuluO2uANTRep4434O";
    public static final String CHANNEL = "pa-newengland";
    private static final Logger logger = LoggerFactory.getLogger(SlackSink.class);

    @ServiceActivator(inputChannel=Sink.INPUT)
    public void send(String payload) {
        try {
            SlackSession session = SlackSessionFactory.createWebSocketSlackSession(BOT_TOKEN);
            session.connect();
            SlackChannel channel = session.findChannelByName(CHANNEL);
            session.sendMessage(channel, payload);
        } catch ( IOException ioEx ) {
            System.out.println("IOException communicating with Slack: " + ioEx.getMessage() );
        }
    }
}
