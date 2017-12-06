package cn.longzzai.validator;

import cn.longzzai.security.core.DTO.CodeDTO;
import cn.longzzai.security.core.DTO.ImageCodeDTO;
import cn.longzzai.security.core.validate.api.ValidateCodeGenerator;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author longcho
 * 2017-10-23-17:56
 */
//@Component("codeImageGenerator")
public class MyImageValidateCodeGenerator implements ValidateCodeGenerator {


    @Override
    public CodeDTO getRandcode(ServletWebRequest request) {
        return null;
    }
}
