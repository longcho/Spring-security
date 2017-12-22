package cn.longzzai.security.app.valicate.social.openid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @author longcho
 * 2017-12-18-9:50
 */
@Component
public class OpenIdProviderIdAuthenticationConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private AuthenticationSuccessHandler longzzaiAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler longzzaiAuthenticationfialHandler;

    @Autowired
    private SocialUserDetailsService userDetailsService;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        OpenIdProviderIdAuthenticationFilter openIdProviderIdAuthenticationFilter = new OpenIdProviderIdAuthenticationFilter();
        openIdProviderIdAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        openIdProviderIdAuthenticationFilter.setAuthenticationSuccessHandler(longzzaiAuthenticationSuccessHandler);
        openIdProviderIdAuthenticationFilter.setAuthenticationFailureHandler(longzzaiAuthenticationfialHandler);

        OpenIdProviderIdAuthenticationProvider openIdProviderIdAuthenticationProvider = new OpenIdProviderIdAuthenticationProvider();
        openIdProviderIdAuthenticationProvider.setUserDetailsService(userDetailsService);
        openIdProviderIdAuthenticationProvider.setUsersConnectionRepository(usersConnectionRepository);

        http.authenticationProvider(openIdProviderIdAuthenticationProvider)
                .addFilterAfter(openIdProviderIdAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
