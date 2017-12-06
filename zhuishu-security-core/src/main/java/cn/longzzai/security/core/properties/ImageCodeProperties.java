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
@ConfigurationProperties(prefix = "longzzai.security.authentication.code.image")
public class ImageCodeProperties {
    private int width = 80;// 图片宽
    private int height = 26;// 图片高
    private int exireIn = 60;// 过期时间
    private int lineSize = 40;// 干扰线数量
    private int stringNum = 4;// 随机产生字符数量
    private int fontSize = 18;// 随机产生字符数量
    private String url ="";// 需要验证码的url   例：/user,/user/*
    private  String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";// 随机产生的字符串
}
