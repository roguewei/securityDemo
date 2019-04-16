package com.winston.async;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName DefferedResultHolder
 * @Description
 * @Author Winston
 * @Date 2019/4/16 10:46
 * @Version 1.0
 **/
@Component
@Data
public class DeferredResultHolder {

    private Map<String, DeferredResult<String >> map = new HashMap<>();


}
