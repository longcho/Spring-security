package cn.longzzai.security.core.authorize;

import cn.longzzai.security.core.constant.SecurityConstant;
import cn.longzzai.security.core.properties.SecurityRootProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author longcho
 * 2017-12-22-22:52
 */
@Component
public class LongzzaiAuthorizeConfigProvider implements AuthorizeConfigProvider {
    @Autowired
    private SecurityRootProperties securityRootProperties;

    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config
        .antMatchers(
                SecurityConstant.DEFAULT_UNAUTHENTICATION_URL,
                SecurityConstant.DEFAULT_LOGIN_PAGE_URL,
                SecurityConstant.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                securityRootProperties.getBrowser().getLoginPage(),
                SecurityConstant.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
                securityRootProperties.getBrowser().getSignUpPage(),
                securityRootProperties.getSession().getSessionInvalidUrl())
                .permitAll();
        return false;
    }
}
