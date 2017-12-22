package cn.longzzai.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * @author longcho
 * 2017-12-18-14:46
 */
public interface SocialAuthenticationFilterPostProcessor {
    //对filter配置成功处理器
    void process(SocialAuthenticationFilter socialAuthenticationFilter);
}
