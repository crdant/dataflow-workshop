package io.pivotal.pa.demo;

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;

import java.io.IOException;

/**
 * A Sink app that sends it's payload to a Slack channel.
 *
 * @author Chuck D'Antonio
 */

@EnableBinding(Sink.class)
@EnableConfigurationProperties(SlackSinkProperties.class)
public class SlackSink {
    private static final Logger logger = LoggerFactory.getLogger(SlackSink.class);

    @Autowired
    private SlackSinkProperties properties;

    @ServiceActivator(inputChannel=Sink.INPUT)
    public void send(String payload) {
        try {
            SlackSession session = SlackSessionFactory.createWebSocketSlackSession(properties.getToken());
            session.connect();
            SlackChannel channel = session.findChannelByName(properties.getChannel());
            session.sendMessage(channel, payload);
        } catch ( IOException ioEx ) {
            System.out.println("IOException communicating with Slack: " + ioEx.getMessage() );
        }
    }
}
