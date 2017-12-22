package cn.longzzai.security.app.controller;

import cn.longzzai.security.core.social.utils.AppSignUpUtils;
import cn.longzzai.security.core.support.SocialUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author longcho
 * 2017-12-18-17:31
 */
@RestController
public class AppSecurityController {
    @Autowired
    private ProviderSignInUtils providerSignInUtils;
    @Autowired
    private AppSignUpUtils appSignUpUtils;
    @GetMapping("/social/signUp")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {
        Connection<?> connectionFromSession = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        SocialUserInfo socialUserInfo = new SocialUserInfo();
        socialUserInfo.setProviderId(connectionFromSession.getKey().getProviderId());
        socialUserInfo.setProviderUserId(connectionFromSession.getKey().getProviderUserId());
        socialUserInfo.setNickname(connectionFromSession.getDisplayName());
        socialUserInfo.setHeadimg(connectionFromSession.getImageUrl());
        appSignUpUtils.saveConnectionDataOnRedis(new ServletWebRequest(request), connectionFromSession.createData());
        return socialUserInfo;
    }
}
