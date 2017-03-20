package io.pivotal.pa.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for the Twilio SMS Sink module.
 *
 * @author Chuck D'Antonio
 */
@ConfigurationProperties("sms")
public class TwilioSMSSinkProperties {

    // Find your id, token, and phone number at at twilio.com/user/account

    private String sid;
    private String token ;
    private String from ;
    private String to ;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

}
