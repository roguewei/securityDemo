package com.winston.async;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName MockQueue
 * @Description
 * @Author Winston
 * @Date 2019/4/16 10:43
 * @Version 1.0
 **/
@Data
@Component
@Slf4j
public class MockQueue {

    private String placeOrder;

    private String completeOrder;

    public void setPlaceOrder(String placeOrder){
        new Thread(() -> {
            log.info("接到下单请求，"+placeOrder);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.completeOrder = placeOrder;
            log.info("下单请求处理完成，"+ placeOrder);
        }).start();
    }
}
