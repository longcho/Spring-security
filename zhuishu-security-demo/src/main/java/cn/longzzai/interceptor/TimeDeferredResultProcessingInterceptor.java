package cn.longzzai.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.DeferredResultProcessingInterceptor;

import java.util.Date;


/**
 * @author longcho
 * 2017-10-16-21:48
 */
@Component
public class TimeDeferredResultProcessingInterceptor implements DeferredResultProcessingInterceptor {

    @Override
    public <T> void beforeConcurrentHandling(NativeWebRequest request, DeferredResult<T> deferredResult) throws Exception {
        System.out.println("beforeConcurrentHandling =====");
        request.setAttribute("startTime1" , new Date().getTime() , RequestAttributes.SCOPE_SESSION);
    }

    @Override
    public <T> void preProcess(NativeWebRequest request, DeferredResult<T> deferredResult) throws Exception {
        System.out.println("preProcess =====");
        request.setAttribute("startTime2" , new Date().getTime() , RequestAttributes.SCOPE_SESSION);
    }

    @Override
    public <T> void postProcess(NativeWebRequest request, DeferredResult<T> deferredResult, Object concurrentResult) throws Exception {
        System.out.println("postProcess =====");
        long startTime1 = (long) request.getAttribute("startTime1" ,RequestAttributes.SCOPE_SESSION);
        System.out.println("postProcess1耗时：" + (new Date().getTime() - startTime1) + "ms");
        long startTime2 = (long) request.getAttribute("startTime2" ,RequestAttributes.SCOPE_SESSION);
        System.out.println("postProcess2耗时：" + (new Date().getTime() - startTime2) + "ms");
    }

    @Override
    public <T> boolean handleTimeout(NativeWebRequest request, DeferredResult<T> deferredResult) throws Exception {
        return false;
    }

    @Override
    public <T> void afterCompletion(NativeWebRequest request, DeferredResult<T> deferredResult) throws Exception {
        System.out.println("afterCompletion =====");
    }
}
