package cn.longzzai.security.core.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author longcho
 * 2017-12-06-23:24
 */
@Data
@Component
@ConfigurationProperties(prefix = "longzzai.security.social.weixin")
public class WeixinProperties extends SocialProperties{
    private String providerId = "weixin";
    private String filterProcessesUrl = "/auth";
}
