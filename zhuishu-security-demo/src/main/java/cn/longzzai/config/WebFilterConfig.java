package cn.longzzai.config;

import cn.longzzai.filter.TimeFilter;
import cn.longzzai.interceptor.TimeDeferredResultProcessingInterceptor;
import cn.longzzai.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResultProcessingInterceptor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author longcho
 * 2017-10-16-15:18
 */
@Component
public class WebFilterConfig extends WebMvcConfigurerAdapter{

    @Autowired
    private TimeInterceptor timeInterceptor;
    @Autowired
    private TimeDeferredResultProcessingInterceptor timeDeferredResultProcessingInterceptor;

    //@Override
    //public void addInterceptors(InterceptorRegistry registry) {
    //    registry.addInterceptor(timeInterceptor);
    //    super.addInterceptors(registry);
    //}

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.registerDeferredResultInterceptors(timeDeferredResultProcessingInterceptor);
        super.configureAsyncSupport(configurer);
    }

    //@Bean
    //public FilterRegistrationBean timeFilter(){
    //    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
    //    TimeFilter timeFilter = new TimeFilter();
    //    filterRegistrationBean.setFilter(timeFilter);
    //    return filterRegistrationBean;
    //}
}
