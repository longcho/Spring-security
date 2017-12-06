package cn.longzzai.async;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 模拟消息队列
 *
 * @author longcho
 * 2017-10-16-20:53
 */
@Slf4j
@Data
@Component
public class MockQueue {
    private String placeOrder;
    private String completeOrder;

    public void setPlaceOrder(String placeOrder) throws InterruptedException {
        new Thread(() -> {
            log.info("消息队列接收到新订单 ，订单号：{}" ,placeOrder);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.completeOrder = placeOrder;
            log.info("消息队列接收到新订单并处理 ，订单号：{}" ,placeOrder);
        }).start();

    }
}
