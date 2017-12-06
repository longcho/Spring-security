package cn.longzzai.security.core.config;

import cn.longzzai.security.core.properties.SecurityRootProperties;
import cn.longzzai.security.core.validate.image.ImageValidateCodeGenerator;
import cn.longzzai.security.core.validate.api.ValidateCodeGenerator;
import cn.longzzai.security.core.validate.sms.DefaultSmsCodeSender;
import cn.longzzai.security.core.validate.sms.SmsCodeSender;
import cn.longzzai.security.core.validate.sms.SmsValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 图片验证码相关配置
 *
 * @author longcho
 * 2017-10-23-15:43
 */
@Configuration
public class ValidateCodeConfig {
    @Autowired
    private SecurityRootProperties securityRootProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")  //如果存在codeImageGenerator 就不赋值
    public ValidateCodeGenerator imageValidateCodeGenerator(){
        return new ImageValidateCodeGenerator(securityRootProperties);
    }
    @Bean
    @ConditionalOnMissingBean(name = "smsValidateCodeGenerator")  //如果存在smsValidateCodeGenerator 就不赋值
    public SmsValidateCodeGenerator smsValidateCodeGenerator(){
        return new SmsValidateCodeGenerator();
    }
    @Bean
    @ConditionalOnMissingBean(name = "smsCodeSender")
    public SmsCodeSender smsCodeSender(){
       return new DefaultSmsCodeSender();
    }
}
