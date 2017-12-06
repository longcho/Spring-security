package cn.longzzai.security.core.validate.api;

import cn.longzzai.security.core.DTO.CodeDTO;
import org.springframework.web.context.request.ServletWebRequest;


/**
 * 验证码生成接口
 *
 * @author longcho
 * 2017-10-23-14:53
 */
public interface ValidateCodeGenerator {
    /**
     * 生成随机验证码
     */
    CodeDTO getRandcode(ServletWebRequest request);
}
