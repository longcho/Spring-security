package cn.longzzai.security.core.validate.sms;

import cn.longzzai.security.core.DTO.CodeDTO;
import cn.longzzai.security.core.constant.SecurityConstant;
import cn.longzzai.security.core.validate.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信验证码处理
 *
 * @author longcho
 * 2017-11-04-12:19
 */
@Component("smsValidateCodeProcessor")
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor{
    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, CodeDTO validateCode) throws ServletRequestBindingException {
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), SecurityConstant.DEFAULT_PARAMETER_NAME_MOBILE);
        smsCodeSender.send(mobile , validateCode.getCode());
    }
}
