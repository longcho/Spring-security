package cn.longzzai.security.browser.authentication;

import cn.longzzai.security.browser.support.SimpleResponse;
import cn.longzzai.security.core.enums.LoginTypeEnum;
import cn.longzzai.security.core.properties.SecurityRootProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author longcho
 * 2017-10-22-19:58
 */
@Slf4j
@Component("longzzaiAuthenticationfialHandler")
public class LongzzaiAuthenticationfialHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    private SecurityRootProperties securityRootProperties;
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("登录失败");
        if (securityRootProperties.getBrowser().getLoginType().equals(LoginTypeEnum.JSON)){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
        }else {
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
