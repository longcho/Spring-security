package cn.longzzai.security.core.constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author longcho
 * 2017-10-22-20:31
 */
@Data
@ConfigurationProperties(prefix = "longzzai.security.browser")
public class SecurityConstant {

    /**
     * 默认的处理验证码的url前缀
     */
    public static final String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";
    /**
     * 当请求需要身份认证时，默认跳转的url
     *
     * @see cn.longzzai.security.browser.controller.BrowserSecurityController
     */
    public static final String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";
    /**
     * 默认的用户名密码登录请求处理url
     */
    public static final String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/form";
    /**
     * 默认的手机验证码登录请求处理url
     */
    public static final String DEFAULT_LOGIN_PROCESSING_URL_MOBILE = "/authentication/mobile";
    /**
     * 默认登录页面
     *
     * @see cn.longzzai.security.browser.controller.BrowserSecurityController
     */
    public static final String DEFAULT_LOGIN_PAGE_URL = "/imooc-signIn.html";
    /**
     * 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";
    /**
     * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";
    /**
     * 发送短信验证码 或 验证短信验证码时，传递手机号的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";
}
