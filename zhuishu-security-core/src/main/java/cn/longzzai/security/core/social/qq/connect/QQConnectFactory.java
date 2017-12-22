package cn.longzzai.security.core.social.qq.connect;

import cn.longzzai.security.core.social.qq.api.QQApi;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @author longcho
 * 2017-12-06-23:09
 */
public class QQConnectFactory extends OAuth2ConnectionFactory<QQApi>{


    /**
     * Create a {@link OAuth2ConnectionFactory}.
     *
     * @param providerId      the provider id e.g. "facebook"
     * @param serviceProvider the ServiceProvider model for conducting the authorization flow and obtaining a native service API instance.
     * @param apiAdapter      the ApiAdapter for mapping the provider-specific service API model to the uniform {@link Connection} interface.
     */
    public QQConnectFactory(String providerId, String appid, String appSecret) {
        super(providerId, new QQSreviceProvider(appid , appSecret), new QQAdapter());
    }
}
