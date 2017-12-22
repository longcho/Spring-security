package cn.longzzai.security.browser.session;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author longcho
 * 2017-12-14-17:21
 */
@Slf4j
public abstract class AbstractSessionStrategy{
    private String soureUrl;
    private String tagetUrl;
    /**
     * 跳转前是否创建新的session
     */
    private boolean createNewSession = true;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public AbstractSessionStrategy(String tagetUrl) {
        this.tagetUrl = tagetUrl;
    }



    public void onSessionInvalid(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (createNewSession) {
            request.getSession();
        }
        soureUrl = request.getRequestURI();
        if (StringUtils.endsWithIgnoreCase(soureUrl, ".html")) {
            log.info("session失效,跳转到{}",tagetUrl);
            redirectStrategy.sendRedirect(request, response, tagetUrl);
        } else {
            Object content = setResponseContent();
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(new ObjectMapper().writeValueAsString(content));
        }
    }

    public abstract Object setResponseContent();
}

