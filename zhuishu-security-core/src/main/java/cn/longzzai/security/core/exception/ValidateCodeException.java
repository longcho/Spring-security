package cn.longzzai.security.core.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 图片验证异常
 *
 * @author longcho
 * 2017-10-23-11:49
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
