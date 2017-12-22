package cn.longzzai.security.core.properties;

import lombok.Data;

/**
 * @author longcho
 * 2017-12-18-20:55
 */
@Data
public class Oauth2ClientProperties {
    private String clientId;
    private String clientSecret;
    //过期时间
    private int expireIn = 7200;
    //权限
    private String[] scopes = {"all"};
}
