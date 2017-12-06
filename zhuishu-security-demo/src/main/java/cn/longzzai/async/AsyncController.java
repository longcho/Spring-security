package cn.longzzai.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * @author longcho
 * 2017-10-16-19:25
 */
@Slf4j
@RestController
public class AsyncController {
    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @GetMapping("/order")
    public Callable<String> getOrder() throws InterruptedException {
        log.info("主线程开始");
        Callable<String> result = new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("副线程开始");
                Thread.sleep(3000);
                log.info("副线程结束");

                return "success";
            }
        };
        log.info("主线程结束");
        return result;
    }

    @GetMapping("/order1")
    public DeferredResult<String> getOrder1() throws InterruptedException {
        log.info("主线程开始");

        DeferredResult<String> result = new DeferredResult<>();
        String placeOrder = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(placeOrder);
        deferredResultHolder.getMap().put(placeOrder , result);
        log.info("主线程结束");
        return result;
    }
}
