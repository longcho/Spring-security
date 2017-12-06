package cn.longzzai.security.core.validate.common;

import cn.longzzai.security.core.constant.SecurityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * @author longcho
 * 2017-11-04-21:27
 */
public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SimpleUrlAuthenticationFailureHandler longzzaiAuthenticationfialHandler;
    @Autowired
    private SavedRequestAwareAuthenticationSuccessHandler longzzaiAuthenticationSuccessHandler;

    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityConstant.DEFAULT_LOGIN_PAGE_URL)
                .loginProcessingUrl(SecurityConstant.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(longzzaiAuthenticationSuccessHandler)
                .failureHandler(longzzaiAuthenticationfialHandler);
    }

}