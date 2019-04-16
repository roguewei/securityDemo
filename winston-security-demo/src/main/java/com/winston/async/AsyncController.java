package com.winston.async;

import com.winston.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * @ClassName AsyncController
 * @Description 异步处理restful
 * @Author Winston
 * @Date 2019/4/16 10:30
 * @Version 1.0
 **/
@RestController
@Slf4j
public class AsyncController {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    // 使用Callable异步处理restful服务
    @RequestMapping("/order")
    public Callable<Result> order() throws InterruptedException {
        log.info("主线程开始");
        Callable<Result> result = new Callable<Result>() {
            @Override
            public Result call() throws Exception {
                log.info("异步线程开始");
                Thread.sleep(1000);
                log.info("异步线程结束");
                return Result.success("succes");
            }
        };
        log.info("主线程返回");
        return result;
    }

    @RequestMapping("/order1")
    public DeferredResult<String> order1() throws InterruptedException {
        log.info("主线程开始");

        String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber);

        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber, result);

        log.info("主线程返回");
        return result;
    }

}
