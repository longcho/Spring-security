package cn.longzzai.security.app.jwt;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author longcho
 * 2017-12-19-12:12
 */
public class LongzzaiTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> information = new HashMap<>();
        information.put("company" , "longzzaiCompany");
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(information);
        return accessToken;
    }
}
