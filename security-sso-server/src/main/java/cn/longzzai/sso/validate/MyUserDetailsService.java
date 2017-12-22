package cn.longzzai.sso.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @author longcho
 * 2017-10-18-8:13
 */
@Component
public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = passwordEncoder.encode("123456");
        System.out.println("密码是："+password);
        return new User(username ,password , true ,true ,true ,true ,
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        String password = passwordEncoder.encode("123456");
        System.out.println("密码是："+password);
        return new SocialUser(userId ,password , true ,true ,true ,true ,
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
    }
}
