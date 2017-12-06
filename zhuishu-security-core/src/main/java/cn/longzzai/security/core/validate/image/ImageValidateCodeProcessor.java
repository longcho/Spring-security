package cn.longzzai.security.core.validate.image;

import cn.longzzai.security.core.DTO.CodeDTO;
import cn.longzzai.security.core.DTO.ImageCodeDTO;
import cn.longzzai.security.core.validate.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/**
 * 短信验证码处理
 *
 * @author longcho
 * 2017-11-04-12:19
 */
@Component("imageValidateCodeProcessor")
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor{
    @Autowired
    private ImageCodeSender imageCodeSender;

    @Override
    protected void send(ServletWebRequest request, CodeDTO validateCode) throws ServletRequestBindingException, IOException {
        imageCodeSender.send(request , (ImageCodeDTO) validateCode);
    }
}
