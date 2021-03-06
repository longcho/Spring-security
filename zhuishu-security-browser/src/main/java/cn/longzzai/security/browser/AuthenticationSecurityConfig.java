package cn.longzzai.security.browser;

import cn.longzzai.security.core.authorize.AuthorizeConfigProviderManager;
import cn.longzzai.security.core.properties.SecurityRootProperties;
import cn.longzzai.security.core.validate.common.AbstractChannelSecurityConfig;
import cn.longzzai.security.core.validate.common.ValidateCodeFilterConfig;
import cn.longzzai.security.core.validate.sms.SmsCodeAuthenticationSecurityConfig;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.stereotype.Component;


/**
 * 认证相关配置
 *
 * @author longcho
 * 2017-11-04-19:12
 */
@Component
public class AuthenticationSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    private SecurityRootProperties securityProperties;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeFilterConfig validateCodeFilterConfig;

    @Autowired
    private SpringSocialConfigurer longzzaiSocialSecurityConfig;

    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private AuthorizeConfigProviderManager authorizeConfigProviderManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        applyPasswordAuthenticationConfig(http);

        http.apply(validateCodeFilterConfig)
                .and()
                .apply(longzzaiSocialSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                    .userDetailsService(userDetailsService)
                .and()

                    .sessionManagement()
                        .invalidSessionStrategy(invalidSessionStrategy) //session过期策略配置
                        .maximumSessions(securityProperties.getSession().getMaximumSessions())  //最大连接数配置
                        .maxSessionsPreventsLogin(securityProperties.getSession().isMaxSessionsPreventsLogin())
                        .expiredSessionStrategy(sessionInformationExpiredStrategy) //连接过期配置
                .and()
                .and()
                    .logout()
                        .logoutSuccessHandler(logoutSuccessHandler)
                .and()
                .csrf().disable();
        authorizeConfigProviderManager.config(http.authorizeRequests());

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//		tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }
}