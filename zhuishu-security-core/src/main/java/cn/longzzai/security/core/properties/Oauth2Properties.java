package cn.longzzai.security.core.properties;

import cn.longzzai.security.core.enums.StoreType;
import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author longcho
 * 2017-12-18-20:54
 */
@Data
@Component
@ConfigurationProperties(prefix = "longzzai.security.social.oauth2")
public class Oauth2Properties extends SocialProperties {
    private Oauth2ClientProperties[] clients;
    //accesstoken类型
    private StoreType storeType;
    //当accesstoken为jwt时的加密盐
    private String signingKey = "longzzai";
}