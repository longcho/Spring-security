package cn.longzzai.security.core.social;

import lombok.Data;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * 自定义config 实现自定义认证url
 * @author longcho
 * 2017-12-07-11:30
 */
@Data
public class LongzzaiSpringSocialConfigurer extends SpringSocialConfigurer{
    private String filterProcessesUrl;
    private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;

    public LongzzaiSpringSocialConfigurer(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter socialAuthenticationFilter = (SocialAuthenticationFilter)super.postProcess(object);
        socialAuthenticationFilter.setFilterProcessesUrl(filterProcessesUrl);

        //配置filter
        if (socialAuthenticationFilterPostProcessor != null){
            socialAuthenticationFilterPostProcessor.process(socialAuthenticationFilter);
        }
        return (T) socialAuthenticationFilter;
    }

}
