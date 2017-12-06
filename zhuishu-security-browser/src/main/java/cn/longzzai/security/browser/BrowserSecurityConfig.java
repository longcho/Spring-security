/*
package cn.longzzai.security.browser;

import cn.longzzai.security.browser.validator.ImageCodeValicateFilter;
import cn.longzzai.security.core.properties.SecurityRootProperties;
import cn.longzzai.security.core.validate.sms.SmsCodeAuthenticationSecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

*/
/**
 * @author longcho
 * 2017-10-18-8:34
 *//*

@Slf4j
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityRootProperties securityRootProperties;
    @Autowired
    private SimpleUrlAuthenticationFailureHandler longzzaiAuthenticationfialHandler;
    @Autowired
    private SavedRequestAwareAuthenticationSuccessHandler longzzaiAuthenticationSuccessHandler;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        try {
            //          jdbcTokenRepository.setCreateTableOnStartup(true);
        } catch (Exception e) {
            log.info("数据库创建失败，e={}", e.getMessage());
        }
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //新建一个一次性验证码拦截器
        ImageCodeValicateFilter imageCodeValicateFilter = new ImageCodeValicateFilter(securityRootProperties, longzzaiAuthenticationfialHandler);

        http
                .apply(smsCodeAuthenticationSecurityConfig).and()
                .addFilterBefore(imageCodeValicateFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage(BrowserConstant.LOGIN_ACTION_URL)
                .loginProcessingUrl("/authentication/form")
                .successHandler(longzzaiAuthenticationSuccessHandler)
                .failureHandler(longzzaiAuthenticationfialHandler)
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityRootProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests()    //下面是授权的配置
                .mvcMatchers(BrowserConstant.LOGIN_ACTION_URL,
                        securityRootProperties.getBrowser().getLoginPage(),
                        "/code/*"
                ).permitAll()
                .anyRequest()           //任何请求
                .authenticated()
                .and()
                .csrf().disable();       //都需要授权
    }
}
*/
