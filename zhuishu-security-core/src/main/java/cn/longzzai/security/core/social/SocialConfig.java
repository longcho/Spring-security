package cn.longzzai.security.core.social;

import cn.longzzai.security.core.properties.SecurityRootProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;


/**
 * @author longcho
 * 2017-12-07-9:44
 */
@Configuration
@EnableSocial
@Order(10)
public class SocialConfig extends SocialConfigurerAdapter {
    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;
    @Autowired
    private SecurityRootProperties securityRootProperties;
    @Autowired(required = false)
    private ConnectionSignUp connectionSignUp;
    @Autowired(required = false)
    private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository jdbcUsersConnectionRepository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        //jdbcUsersConnectionRepository.setTablePrefix("longzzai_");
        if(connectionSignUp != null) {
            jdbcUsersConnectionRepository.setConnectionSignUp(connectionSignUp);
        }
        return jdbcUsersConnectionRepository;
    }

    @Bean
    public SpringSocialConfigurer longzzaiSocialSecurityConfig(){
        LongzzaiSpringSocialConfigurer longzzaiSpringSocialConfigurer = new LongzzaiSpringSocialConfigurer(securityRootProperties.getSocial().getQqProperties().getFilterProcessesUrl());
        longzzaiSpringSocialConfigurer.signupUrl(securityRootProperties.getBrowser().getSignUpPage());
        longzzaiSpringSocialConfigurer.setSocialAuthenticationFilterPostProcessor(socialAuthenticationFilterPostProcessor);
        return longzzaiSpringSocialConfigurer;
    }

    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator){
        return new ProviderSignInUtils(connectionFactoryLocator, getUsersConnectionRepository(connectionFactoryLocator)) {
         };
    }

}
