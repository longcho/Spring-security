package cn.longzzai.async;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * 消息队列监听器
 *
 * @author longcho
 * 2017-10-16-21:27
 */
@Component
public class MockQueueListener implements ApplicationListener<ContextRefreshedEvent>{
    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        new Thread(() ->{
            while (true){
                if (StringUtils.isNotBlank(mockQueue.getCompleteOrder())) {
                    DeferredResult<String> result = deferredResultHolder.getMap().get(mockQueue.getCompleteOrder());
                    result.setResult("order success");
                }else {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
