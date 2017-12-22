package cn.longzzai.security.browser.session;


import cn.longzzai.security.core.support.SimpleResponse;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义过期策略
 *
 * @author longcho
 * 2017-12-14-17:04
 */
public class LongzzaiInvalidSessionStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy{

    public LongzzaiInvalidSessionStrategy(String tagetUrl) {
        super(tagetUrl);
    }

    @Override
    public Object setResponseContent() {
            SimpleResponse response = new SimpleResponse("session过期");
            return response;

    }

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.onSessionInvalid(request, response);
    }
}
