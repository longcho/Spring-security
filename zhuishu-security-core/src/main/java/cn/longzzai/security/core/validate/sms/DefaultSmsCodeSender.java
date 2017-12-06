package cn.longzzai.security.core.validate.sms;

/**
 * 短信验证码生成
 *
 * @author longcho
 * 2017-11-04-12:27
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机"+mobile+"发送短信验证码"+code);
    }
}
