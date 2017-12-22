//package cn.longzzai.security.core.validate.common;
//
//import cn.longzzai.security.core.constant.SecurityConstant;
//import cn.longzzai.security.core.properties.SecurityRootProperties;
//import cn.longzzai.security.core.validate.sms.SmsCodeAuthenticationSecurityConfig;
//import org.apache.tomcat.jdbc.pool.DataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
//import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
//import org.springframework.stereotype.Component;
//
//
///**
// * 认证相关配置
// *
// * @author longcho
// * 2017-11-04-19:12
// */
//@Component
//public class AuthenticationSecurityConfig extends AbstractChannelSecurityConfig {
//
//    @Autowired
//    private SecurityRootProperties securityProperties;
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
//
//    @Autowired
//    private ValidateCodeFilterConfig validateCodeFilterConfig;
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        applyPasswordAuthenticationConfig(http);
//
//        http.apply(validateCodeFilterConfig)
//                .and()
//                .apply(smsCodeAuthenticationSecurityConfig)
//                .and()
//                .rememberMe()
//                .tokenRepository(persistentTokenRepository())
//                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
//                .userDetailsService(userDetailsService)
//                .and()
//                .authorizeRequests()
//                .antMatchers(
//                        SecurityConstant.DEFAULT_UNAUTHENTICATION_URL,
//                        SecurityConstant.DEFAULT_LOGIN_PAGE_URL,
//                        SecurityConstant.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
//                        securityProperties.getBrowser().getLoginPage(),
//                        SecurityConstant.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .csrf().disable();
//
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
//        tokenRepository.setDataSource(dataSource);
////		tokenRepository.setCreateTableOnStartup(true);
//        return tokenRepository;
//    }
//}