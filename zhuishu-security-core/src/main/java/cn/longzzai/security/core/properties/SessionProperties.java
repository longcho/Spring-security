package cn.longzzai.security.core.properties;

import cn.longzzai.security.core.constant.SecurityConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author longcho
 * 2017-10-21-9:48
 */
@Data
@Component
@ConfigurationProperties(prefix = "longzzai.security.session")
public class SessionProperties {
    /**
     * 同一个用户在系统中的最大session数，默认1
     */
    private int maximumSessions = 1;
    /**
     * 达到最大session时是否阻止新的登录请求，默认为false，不阻止，新的登录会将老的登录失效掉
     */
    private boolean maxSessionsPreventsLogin;
    /**
     * session失效时跳转的地址
     */
    private String sessionInvalidUrl = SecurityConstant.DEFAULT_SESSION_INVALID_URL;
}
