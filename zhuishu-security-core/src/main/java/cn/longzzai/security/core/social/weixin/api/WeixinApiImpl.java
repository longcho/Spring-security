package cn.longzzai.security.core.social.weixin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author longcho
 * 2017-12-06-17:15
 */
@Slf4j
public class WeixinApiImpl extends AbstractOAuth2ApiBinding implements WeixinApi {
    private static final String URL_GET_WEIXIN_USER_OPENID = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=CODE&grant_type=authorization_code";
    private static final String URL_GET_WEIXIN_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";
    private String accessToken;
    private ObjectMapper objectMapper = new ObjectMapper();

    public WeixinApiImpl(String accessToken) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.accessToken = accessToken;
    }

    @Override
    public WeixinUserInfo getWeixinUserInfo(String openid) {
        String format = String.format(URL_GET_WEIXIN_USER_INFO, accessToken, openid);
        String result = getRestTemplate().getForObject(format, String.class);
        log.info("getuserinfo  result={}", result);
        try {
            WeixinUserInfo WeixinUserInfo = objectMapper.readValue(result, WeixinUserInfo.class);
            return WeixinUserInfo;
        } catch (IOException e) {
            log.error("获取用户信息失败 e={}" , e.getMessage());
            throw  new RuntimeException("获取用户信息失败");
        }
    }

    /**
     * 默认注册的StringHttpMessageConverter字符集为ISO-8859-1，而微信返回的是UTF-8的，所以覆盖了原来的方法。
     */
    @Override
    protected List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
        messageConverters.remove(0);
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return messageConverters;
    }
}
