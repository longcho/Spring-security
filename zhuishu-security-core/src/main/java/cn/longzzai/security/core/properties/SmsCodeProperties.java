package cn.longzzai.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 验证码相关参数
 *
 * @author longcho
 * 2017-10-23-14:44
 */
@Data
@Component
@ConfigurationProperties(prefix = "longzzai.security.authentication.code.sms")
public class SmsCodeProperties {
    private int num = 5;// 随机产生字符数量
    private int exireIn = 60;// 过期时间
    private String url ="";// 需要验证码的url   例：/user,/user/*
}
