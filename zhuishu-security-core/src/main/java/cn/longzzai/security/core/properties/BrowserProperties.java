package cn.longzzai.security.core.properties;

import cn.longzzai.security.core.enums.LoginTypeEnum;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author longcho
 * 2017-10-21-9:48
 */
@Data
@Component
@ConfigurationProperties(prefix = "longzzai.security.browser")
public class BrowserProperties {
    private String loginPage = "/login-browser.html";
    private LoginTypeEnum loginType =  LoginTypeEnum.JSON;
    private int rememberMeSeconds =  3600;
}
