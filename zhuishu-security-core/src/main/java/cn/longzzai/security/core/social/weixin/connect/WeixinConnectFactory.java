package cn.longzzai.security.core.social.weixin.connect;

import cn.longzzai.security.core.social.weixin.api.WeixinApi;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * @author longcho
 * 2017-12-13-19:06
 */
public class WeixinConnectFactory extends OAuth2ConnectionFactory<WeixinApi> {

    /**
     * 由于微信的openId是和accessToken一起返回的，所以在这里直接根据accessToken设置providerUserId即可，不用像QQ那样通过QQAdapter来获取
     */
    @Override
    protected String extractProviderUserId(AccessGrant accessGrant) {
        if(accessGrant instanceof WeixinAccessGrant) {
            return ((WeixinAccessGrant)accessGrant).getOpenid();
        }
        return null;
    }
    /**
     * Create a {@link OAuth2ConnectionFactory}.
     *
     * @param providerId      the provider id e.g. "facebook"
     * @param serviceProvider the ServiceProvider model for conducting the authorization flow and obtaining a native service API instance.
     * @param apiAdapter      the ApiAdapter for mapping the provider-specific service API model to the uniform {@link Connection} interface.
     */


    public WeixinConnectFactory(String providerId, String appid, String appSecret) {
        super(providerId, new WeixinServiceProvider( appid, appSecret), new WeixinAdapter());
    }

    //写入openid

    @Override
    public Connection<WeixinApi> createConnection(AccessGrant accessGrant) {
        return new OAuth2Connection<WeixinApi>(getProviderId(), extractProviderUserId(accessGrant), accessGrant.getAccessToken(),
                accessGrant.getRefreshToken(), accessGrant.getExpireTime(), getOAuth2ServiceProvider(), getApiAdapter(extractProviderUserId(accessGrant)));
    }

    @Override
    public Connection<WeixinApi> createConnection(ConnectionData data) {
        return new OAuth2Connection<WeixinApi>(data, getOAuth2ServiceProvider(), getApiAdapter(data.getProviderUserId()));
    }

    protected ApiAdapter<WeixinApi> getApiAdapter(String providerUserId) {

        return new WeixinAdapter(providerUserId);
    }

    private OAuth2ServiceProvider<WeixinApi> getOAuth2ServiceProvider() {
        return (OAuth2ServiceProvider<WeixinApi>) getServiceProvider();
    }
}
