package cn.longzzai.security.browser.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author longcho
 * 2017-12-16-9:49
 */
public class LongzzaiLogoutHandler implements LogoutSuccessHandler {
    private String logoutUrl;

    public LongzzaiLogoutHandler(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }


    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (StringUtils.isNotBlank(logoutUrl)){
            response.sendRedirect(logoutUrl);
        }else {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("用户"+ authentication.getName() + ",已成功退出");
        }
    }
}
