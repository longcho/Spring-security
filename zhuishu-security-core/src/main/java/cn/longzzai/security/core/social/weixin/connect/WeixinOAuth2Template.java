package cn.longzzai.security.core.social.weixin.connect;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author longcho
 * 2017-12-13-16:48
 */
@Slf4j
@Data
public class WeixinOAuth2Template extends OAuth2Template {
    private final String clientId;

    private final String clientSecret;

    private final String accessTokenUrl;

    private String authenticateUrl;


    public WeixinOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        this.accessTokenUrl = accessTokenUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        setUseParametersForClientAuthentication(true);
    }

    @Override
    public AccessGrant exchangeForAccess(String authorizationCode, String redirectUri, MultiValueMap<String, String> additionalParameters) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.set("appid", clientId);
        params.set("secret", clientSecret);
        params.set("code", authorizationCode);
        params.set("grant_type", "authorization_code");
        params.set("redirect_uri", redirectUri);
        if (additionalParameters != null) {
            params.putAll(additionalParameters);
        }
        return postForAccessGrant(accessTokenUrl, params);
    }

    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
       Map map = getRestTemplate().postForObject(accessTokenUrl, parameters, Map.class);
       // Map<String, Object> map = getRestTemplate().getForObject(accessTokenUrl, Map.class, parameters);
        return createAccessGrant((String) map.get("access_token"),
                (String) map.get("scope"),
                (String) map.get("refresh_token"),
                getIntegerValue(map, "expires_in"),
                map);
    }

    @Override
    protected AccessGrant createAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn, Map<String, Object> response) {
        return  new WeixinAccessGrant(accessToken, scope, refreshToken, expiresIn,  (String) response.get("openid"));
    }

    //解决微信登录返回txt/plain数据的处理
    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        HttpMessageConverter messageConverter = new WeixinMappingJackson2HttpMessageConverter();
        restTemplate.getMessageConverters().add(messageConverter);
        return restTemplate;
    }

    /**
     * 构建获取授权码的请求。也就是引导用户跳转到微信的地址。
     */
    //todo  构建获取授权码的请求。也就是引导用户跳转到微信的地址。
    @Override
    public String buildAuthenticateUrl(OAuth2Parameters parameters) {
        String url = super.buildAuthenticateUrl(parameters);
        url = url + "&appid="+clientId+"&scope=snsapi_login";
        return url;
    }
    @Override
    public String buildAuthorizeUrl(OAuth2Parameters parameters) {
        return buildAuthenticateUrl(parameters);
    }
    private Long getIntegerValue(Map<String, Object> map, String key) {
        try {
            return Long.valueOf(String.valueOf(map.get(key))); // normalize to String before creating integer value;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
