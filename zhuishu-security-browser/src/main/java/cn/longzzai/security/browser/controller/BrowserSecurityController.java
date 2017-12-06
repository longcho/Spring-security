package cn.longzzai.security.browser.controller;

import cn.longzzai.security.browser.support.SimpleResponse;
import cn.longzzai.security.core.constant.SecurityConstant;
import cn.longzzai.security.core.properties.SecurityRootProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.bind.annotation.*;
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
  /*  @Autowired
    private ValidateCodeGenerator validateCodeGenerator;*/

    /**
     * 需要认证的跳转处，判断跳转到html还是返回json
     * @return
     */
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @RequestMapping(SecurityConstant.DEFAULT_UNAUTHENTICATION_URL)
    public SimpleResponse require(HttpServletRequest request , HttpServletResponse response) throws IOException {
        if (request.getRequestURI().endsWith(".html")){
            redirectStrategy.sendRedirect(request ,response ,securityRootProperties.getBrowser().getLoginPage());
        }
            return new SimpleResponse("未认证，请到指定页面认证");

    }
}
