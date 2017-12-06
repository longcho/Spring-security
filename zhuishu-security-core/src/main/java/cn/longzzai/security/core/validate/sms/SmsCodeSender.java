package cn.longzzai.security.core.validate.sms;

/**
 * 短信验证码发送
 *
 * @author longcho
 * 2017-11-04-12:26
 */
public interface SmsCodeSender {
    void send(String mobile , String code);
}
