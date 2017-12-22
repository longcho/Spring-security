package cn.longzzai.security.browser.controller;

import cn.longzzai.security.core.constant.SecurityConstant;
import cn.longzzai.security.core.properties.SecurityRootProperties;
import cn.longzzai.security.core.support.SimpleResponse;
import cn.longzzai.security.core.support.SocialUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author longcho
 * 2017-10-21-9:28
 */
@RestController
public class BrowserSecurityController {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Autowired
    private SecurityRootProperties securityRootProperties;
    @Autowired
    private ProviderSignInUtils providerSignInUtils;
  /*  @Autowired
    private ValidateCodeGenerator validateCodeGenerator;*/

    /**
     * 需要认证的跳转处，判断跳转到html还是返回json
     *
     * @return
     */
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @RequestMapping(SecurityConstant.DEFAULT_UNAUTHENTICATION_URL)
    public SimpleResponse require(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getRequestURI().endsWith(".html")) {
            redirectStrategy.sendRedirect(request, response, securityRootProperties.getBrowser().getLoginPage());
        }
        return new SimpleResponse("未认证，请到指定页面认证");

    }

    @GetMapping("social/user")
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {
        Connection<?> connectionFromSession = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        SocialUserInfo socialUserInfo = new SocialUserInfo();
        socialUserInfo.setProviderId(connectionFromSession.getKey().getProviderId());
        socialUserInfo.setProviderUserId(connectionFromSession.getKey().getProviderUserId());
        socialUserInfo.setNickname(connectionFromSession.getDisplayName());
        socialUserInfo.setHeadimg(connectionFromSession.getImageUrl());
        return socialUserInfo;
    }
}
