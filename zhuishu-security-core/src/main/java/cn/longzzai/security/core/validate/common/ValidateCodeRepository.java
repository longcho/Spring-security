package cn.longzzai.security.core.validate.common;

import cn.longzzai.security.core.DTO.CodeDTO;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 自定义验证数据处理
 *
 * @author longcho
 * 2017-12-17-19:49
 */
public interface ValidateCodeRepository {
    /**
     * 保存验证码
     * @param request
     * @param code
     */
    void save(ServletWebRequest request, String key, CodeDTO code);
    /**
     * 获取验证码
     * @param request
     * @return
     */
    CodeDTO get(ServletWebRequest request, String key);
    /**
     * 移除验证码
     * @param request
     */
    void remove(ServletWebRequest request, String key);

}
