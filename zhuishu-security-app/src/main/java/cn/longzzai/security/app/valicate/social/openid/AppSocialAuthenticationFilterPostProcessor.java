package cn.longzzai.security.app.valicate.social.openid;

import cn.longzzai.security.app.authentication.LongzzaiAuthenticationSuccessHandler;
import cn.longzzai.security.core.social.SocialAuthenticationFilterPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author longcho
 * 2017-12-18-14:46
 */
@Component
public class AppSocialAuthenticationFilterPostProcessor implements SocialAuthenticationFilterPostProcessor {
    @Autowired
    private LongzzaiAuthenticationSuccessHandler longzzaiAuthenticationSuccessHandler;
    //对filter配置成功处理器
    public void process(SocialAuthenticationFilter socialAuthenticationFilter){
        socialAuthenticationFilter.setAuthenticationSuccessHandler(longzzaiAuthenticationSuccessHandler);
    }
}
