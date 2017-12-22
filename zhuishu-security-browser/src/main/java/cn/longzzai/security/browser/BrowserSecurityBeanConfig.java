package cn.longzzai.security.browser;

import cn.longzzai.security.browser.session.LongzzaiExpiredSessionStrategy;
import cn.longzzai.security.browser.session.LongzzaiInvalidSessionStrategy;
import cn.longzzai.security.browser.validator.LongzzaiLogoutHandler;
import cn.longzzai.security.core.properties.SecurityRootProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

/**
 * @author longcho
 * 2017-12-14-18:20
 */
@Configuration
public class BrowserSecurityBeanConfig {
    @Autowired
    private SecurityRootProperties rootProperties;
    @Bean
    @ConditionalOnMissingBean(InvalidSessionStrategy.class)
    public InvalidSessionStrategy getInvalidSessionStrategy(){
        return new LongzzaiInvalidSessionStrategy(rootProperties.getSession().getSessionInvalidUrl());
    }
    @Bean
    @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public SessionInformationExpiredStrategy getSessionInformationExpiredStrategy(){
        return new LongzzaiExpiredSessionStrategy(rootProperties.getSession().getSessionInvalidUrl());
    }
    @Bean
    @ConditionalOnMissingBean(LogoutSuccessHandler.class)
    public LogoutSuccessHandler getLogoutSuccessHandler(){
        return new LongzzaiLogoutHandler(rootProperties.getBrowser().getSignOutPage());
    }
}
