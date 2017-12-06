package cn.longzzai.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * 方法耗时filter
 *
 * @author longcho
 * 2017-10-16-15:10
 */
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter init");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long date = new Date().getTime();
        filterChain.doFilter(servletRequest , servletResponse);
        System.out.println("方法耗时：" +(new Date().getTime()-date)+"ms");
    }

    @Override
    public void destroy() {
        System.out.println("time filter destory");
    }
}
