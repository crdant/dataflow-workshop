package io.pivotal.pa.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for the Slack Sink module.
 *
 * @author Chuck D'Antonio
 */

@ConfigurationProperties("slack")
public class SlackSinkProperties {
    private String token ;
    private String channel ;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SlackSinkProperties that = (SlackSinkProperties) o;

        if (getToken() != null ? !getToken().equals(that.getToken()) : that.getToken() != null) return false;
        return getChannel() != null ? getChannel().equals(that.getChannel()) : that.getChannel() == null;
    }

    @Override
    public int hashCode() {
        int result = getToken() != null ? getToken().hashCode() : 0;
        result = 31 * result + (getChannel() != null ? getChannel().hashCode() : 0);
        return result;
    }
}
