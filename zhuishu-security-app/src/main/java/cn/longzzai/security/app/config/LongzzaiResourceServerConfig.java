package cn.longzzai.security.app.config;

import cn.longzzai.security.app.valicate.social.openid.OpenIdProviderIdAuthenticationConfig;
import cn.longzzai.security.core.constant.SecurityConstant;
import cn.longzzai.security.core.properties.SecurityRootProperties;
import cn.longzzai.security.core.validate.common.ValidateCodeFilterConfig;
import cn.longzzai.security.core.validate.sms.SmsCodeAuthenticationSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author longcho
 * 2017-12-17-15:13
 */
@Component
@EnableResourceServer
public class LongzzaiResourceServerConfig extends ResourceServerConfigurerAdapter{
    @Autowired
    private SecurityRootProperties securityProperties;

    @Autowired
    private ValidateCodeFilterConfig validateCodeFilterConfig;

    @Autowired
    private SimpleUrlAuthenticationFailureHandler longzzaiAuthenticationfialHandler;
    @Autowired
    private SavedRequestAwareAuthenticationSuccessHandler longzzaiAuthenticationSuccessHandler;


    @Autowired
    private SpringSocialConfigurer longzzaiSocialSecurityConfig;
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private OpenIdProviderIdAuthenticationConfig openIdProviderIdAuthenticationConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage(SecurityConstant.DEFAULT_LOGIN_PAGE_URL)
                .loginProcessingUrl(SecurityConstant.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(longzzaiAuthenticationSuccessHandler)
                .failureHandler(longzzaiAuthenticationfialHandler);

        http.apply(validateCodeFilterConfig)
               .and()
                .apply(longzzaiSocialSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(openIdProviderIdAuthenticationConfig)
                .and()
                .authorizeRequests()
                .antMatchers(
                        SecurityConstant.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstant.DEFAULT_LOGIN_PAGE_URL,
                        SecurityConstant.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstant.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
                        securityProperties.getBrowser().getSignUpPage(),
                        securityProperties.getSession().getSessionInvalidUrl(),
                        "/user/regist","/social/signUp")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();

    }
}
