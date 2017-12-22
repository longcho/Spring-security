package cn.longzzai.security.app.config;

import cn.longzzai.security.core.properties.Oauth2ClientProperties;
import cn.longzzai.security.core.properties.SecurityRootProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author longcho
 * 2017-12-17-14:22
 */
@Component
@EnableAuthorizationServer
public class LongzzaiAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private SecurityRootProperties securityRootProperties;
    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    @Autowired(required = false)
    private TokenEnhancer jwtTokenEnhancer;
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore)
                .userDetailsService(userDetailsService);

        if (jwtAccessTokenConverter !=null && jwtTokenEnhancer != null){
            TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
            List<TokenEnhancer> enhancerList = new ArrayList<>();
            //endpoints.accessTokenConverter(jwtAccessTokenConverter);
            TokenEnhancer tokenEnhancer = endpoints.getTokenEnhancer();
            if (tokenEnhancer !=null){
                enhancerList.add(tokenEnhancer);
            }
            //todo 顺序不一样，结果buyiyang
            enhancerList.add(jwtTokenEnhancer);
            enhancerList.add(jwtAccessTokenConverter);
            enhancerChain.setTokenEnhancers(enhancerList);
            endpoints.tokenEnhancer(enhancerChain);
        }


    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("company", "longzzai");
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        Oauth2ClientProperties[] clientsProperties = securityRootProperties.getOauth2().getClients();
        if (clientsProperties.length > 0){
            for (Oauth2ClientProperties clientPropertys : clientsProperties) {
                builder.withClient(clientPropertys.getClientId())
                        .secret(clientPropertys.getClientSecret())
                        .authorizedGrantTypes("refresh_token", "authorization_code", "password")
                        .accessTokenValiditySeconds(clientPropertys.getExpireIn())
                        .scopes(clientPropertys.getScopes());
            }
        }

    }
}
