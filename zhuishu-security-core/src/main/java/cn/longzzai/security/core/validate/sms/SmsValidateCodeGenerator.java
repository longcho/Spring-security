package cn.longzzai.security.core.validate.sms;

import cn.longzzai.security.core.DTO.CodeDTO;
import cn.longzzai.security.core.DTO.ImageCodeDTO;
import cn.longzzai.security.core.properties.SecurityRootProperties;
import cn.longzzai.security.core.validate.api.ValidateCodeGenerator;
import lombok.Data;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


/**
 * 图片验证码生成
 *
 * @author longcho
 * 2017-10-23-9:48
 */
@Data
public class SmsValidateCodeGenerator implements ValidateCodeGenerator {
    @Autowired
    private SecurityRootProperties securityRootProperties;
    private int exireIn;// 过期时间
    private int num;// 随机产生字符数量



    public SmsValidateCodeGenerator() {
    }


    /*
    * 获取随机的字符
    */
    public String getRandomString(int num) {
        return RandomStringUtils.randomNumeric(num);
    }


    /**
     * 生成随机图片
     */
    public CodeDTO getRandcode(ServletWebRequest request) {
       return new CodeDTO(getRandomString(securityRootProperties.getCode().getSms().getNum()) ,securityRootProperties.getCode().getSms().getExireIn());
    }

}