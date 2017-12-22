package cn.longzzai.security.core.social.qq.connect;

import cn.longzzai.security.core.social.qq.api.QQApi;
import cn.longzzai.security.core.social.qq.api.QQApiImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * @author longcho
 * 2017-12-06-23:36
 */
public class QQSreviceProvider extends AbstractOAuth2ServiceProvider<QQApi> {

    private String appid;
    private static final String URL_QQ_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    private static final String URL_QQ_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    /**
     * Create a new {@link OAuth2ServiceProvider}.
     *
     * @param
     */
    public QQSreviceProvider(String clientId, String clientSecret) {
        super(new QQOAuth2Template(clientId, clientSecret, URL_QQ_AUTHORIZE, URL_QQ_ACCESS_TOKEN));
        this.appid = clientId;
    }

    @Override
    public QQApi getApi(String accessToken) {
        return new QQApiImpl(accessToken ,appid);
    }
}
