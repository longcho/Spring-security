package cn.longzzai.security.core.social.qq.connect;

import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 绑定解绑成功view
 *
 * @author longcho
 * 2017-12-14-11:10
 */
public class LongzzaiQQConnectView extends AbstractView {

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel
     * (java.util.Map, javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {

        response.setContentType("text/html;charset=UTF-8");
        if (model.get("connection") == null) {
            response.getWriter().write("<h3>QQ解绑成功</h3>");
        } else {
            response.getWriter().write("<h3>QQ绑定成功</h3>");
        }

    }

}