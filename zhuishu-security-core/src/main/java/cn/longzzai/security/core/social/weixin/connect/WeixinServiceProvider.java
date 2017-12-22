package cn.longzzai.security.core.social.weixin.connect;

import cn.longzzai.security.core.social.weixin.api.WeixinApi;
import cn.longzzai.security.core.social.weixin.api.WeixinApiImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;

/**
 * @author longcho
 * 2017-12-13-18:51
 */
public class WeixinServiceProvider extends AbstractOAuth2ServiceProvider<WeixinApi> {

    /**
     * 微信获取授权码的url
     */
    private static final String URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";
    /**
     * 微信获取accessToken的url
     */
    private static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";


    /**
     * Create a new {@link OAuth2ServiceProvider}.
     *
     * @param oauth2Operations the OAuth2Operations template for conducting the OAuth 2 flow with the provider.
     */
    public WeixinServiceProvider(String appId, String appSecret) {
        super(new WeixinOAuth2Template(appId, appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
    }

    @Override
    public WeixinApi getApi(String accessToken) {
        return new WeixinApiImpl(accessToken);
    }
}
