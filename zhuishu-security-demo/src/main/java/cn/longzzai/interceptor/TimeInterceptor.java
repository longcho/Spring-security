package cn.longzzai.interceptor;

import org.omg.PortableInterceptor.Interceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author longcho
 * 2017-10-16-15:52
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("prehandle =====");
        request.setAttribute("startTime" , new Date().getTime());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle =====");
        long startTime = (long) request.getAttribute("startTime");
        System.out.println("posthandle耗时：" + (new Date().getTime() - startTime) + "ms");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion =====");
        long startTime = (long) request.getAttribute("startTime");
        System.out.println("afterCompletion耗时：" + (new Date().getTime() - startTime) + "ms");
        System.out.println("ex ==" + ex);
    }
}
