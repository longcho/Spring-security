package cn.longzzai.security.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import java.io.IOException;
/**
 * @author longcho
 * 2017-12-06-17:15
 */
@Slf4j
public class QQApiImpl extends AbstractOAuth2ApiBinding implements QQApi {
    private static final String URL_GET_QQ_USER_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
    private static final String URL_GET_QQ_USER_INFO = "https://graph.qq.com/user/get_user_info?&oauth_consumer_key=%s&openid=%s";
    private String appid;
    private String openid;
    private ObjectMapper objectMapper = new ObjectMapper();
    public QQApiImpl(String accessToken, String appid) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        String openidUrl = String.format(URL_GET_QQ_USER_OPENID, accessToken);
        String result = getRestTemplate().getForObject(openidUrl, String.class);
        log.info("getOpenId,result={}" , result);
        this.openid = StringUtils.substringBetween(result , "openid\":\"" ,"\"}" );   //callback( {"client_id":"YOUR_APPID","openid":"YOUR_OPENID"} );
        this.appid = appid;
    }

    @Override
    public QQUserInfo getQQUserInfo() {
        String format = String.format(URL_GET_QQ_USER_INFO, appid, openid);
        String result = getRestTemplate().getForObject(format, String.class);
        log.info("getuserinfo  result={}", result);
        try {
            QQUserInfo qqUserInfo = objectMapper.readValue(result, QQUserInfo.class);
            qqUserInfo.setOpenId(openid);
            return qqUserInfo;
        } catch (IOException e) {
            log.error("获取用户信息失败 e={}" , e.getMessage());
            throw  new RuntimeException("获取用户信息失败");
        }
    }
}
