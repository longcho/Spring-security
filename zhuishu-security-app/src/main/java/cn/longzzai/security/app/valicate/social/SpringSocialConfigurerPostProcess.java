package cn.longzzai.security.app.valicate.social;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author longcho
 * 2017-12-18-17:26
 */
@Component
public class SpringSocialConfigurerPostProcess implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * @see cn.longzzai.security.core.social.SocialConfig
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (StringUtils.equals(beanName, "longzzaiSocialSecurityConfig")){
            SpringSocialConfigurer configurer = (SpringSocialConfigurer) bean;
            configurer.signupUrl("/social/signUp");
            return configurer;
        }
        return bean;
    }
}
