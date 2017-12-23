package cn.longzzai.security.browser.validator;

import cn.longzzai.security.core.DTO.CodeDTO;
import cn.longzzai.security.core.validate.common.ValidateCodeRepository;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author longcho
 * 2017-12-17-20:01
 */
@Component
public class SessionValidateCodeRepository implements ValidateCodeRepository {
    /**
     * 操作session的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    public void save(ServletWebRequest request, String key, CodeDTO code) {

        sessionStrategy.setAttribute(request, key, code);
    }

    @Override
    public CodeDTO get(ServletWebRequest request, String key) {
        return (CodeDTO) sessionStrategy.getAttribute(request, key);
    }

    @Override
    public void remove(ServletWebRequest request, String key) {

    }
}
