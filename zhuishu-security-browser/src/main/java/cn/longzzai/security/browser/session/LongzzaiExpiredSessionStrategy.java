package cn.longzzai.security.browser.session;

import cn.longzzai.security.core.support.SimpleResponse;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * 自定义session失效配置
 *
 * @author longcho
 * 2017-12-14-18:16
 */
public class LongzzaiExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {

    public LongzzaiExpiredSessionStrategy(String tagetUrl) {
        super(tagetUrl);
    }

    @Override
    public Object setResponseContent() {
        SimpleResponse response = new SimpleResponse("您的账户已在其他地方登录！");
        return response;
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent eventØ) throws IOException, ServletException {
        this.onSessionInvalid(eventØ.getRequest(), eventØ.getResponse());
    }
}
