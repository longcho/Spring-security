package cn.longzzai.security.app.valicate.social.openid;

import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialUserDetailsService;

import java.util.HashSet;
import java.util.Set;

/**
 * @author longcho
 * 2017-12-18-9:23
 */
@Data
public class OpenIdProviderIdAuthenticationProvider implements AuthenticationProvider {
    private SocialUserDetailsService userDetailsService;

    private UsersConnectionRepository usersConnectionRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        OpenIdProviderIdAuthenticationToken token = (OpenIdProviderIdAuthenticationToken) authentication;
        Set<String> providerUserIds = new HashSet<>();
        providerUserIds.add((String) token.getPrincipal());
        Set<String> userIdsConnectedTo = usersConnectionRepository.findUserIdsConnectedTo(token.getProviderId(), providerUserIds);
        if (CollectionUtils.isEmpty(userIdsConnectedTo) || userIdsConnectedTo.size() < 1) {
            throw new UsernameNotFoundException("找不到该用户");
        }

        String userId = userIdsConnectedTo.iterator().next();

        UserDetails user = userDetailsService.loadUserByUserId(userId);
        if (user == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        return new OpenIdProviderIdAuthenticationToken(user.getAuthorities(), user);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OpenIdProviderIdAuthenticationToken.class.equals(authentication);
    }
}
