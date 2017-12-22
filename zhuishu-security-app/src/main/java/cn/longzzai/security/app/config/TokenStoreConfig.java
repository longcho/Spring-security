package cn.longzzai.security.app.config;

import cn.longzzai.security.app.jwt.LongzzaiTokenEnhancer;
import cn.longzzai.security.core.properties.SecurityRootProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author longcho
 * 2017-12-18-20:50
 */
@Configuration
public class TokenStoreConfig {
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Bean
    @ConditionalOnProperty(prefix = "longzzai.security.social.oauth2", name = "storeType", havingValue = "REDISTOKEN")
    public TokenStore redisTokenStore(){
        return new RedisTokenStore(redisConnectionFactory);
    }
    @Configuration
    public static class JwtTokenStoreConfig{
        @Autowired
        private SecurityRootProperties securityRootProperties;
        @Bean
        @ConditionalOnProperty(prefix = "longzzai.security.social.oauth2", name = "storeType", havingValue = "JWTTOKEN" , matchIfMissing = true)
        public JwtTokenStore jwtTokenStore(){
            JwtTokenStore jwtTokenStore = new JwtTokenStore(jwtAccessTokenConverter());
            return jwtTokenStore;
        }
        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter(){
            JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
            jwtAccessTokenConverter.setSigningKey(securityRootProperties.getOauth2().getSigningKey());
            return jwtAccessTokenConverter;
        }
        @Bean
        @ConditionalOnMissingBean(name = "jwtTokenEnhancer")
        public TokenEnhancer jwtTokenEnhancer(){
            return new LongzzaiTokenEnhancer();
        }
    }
}
