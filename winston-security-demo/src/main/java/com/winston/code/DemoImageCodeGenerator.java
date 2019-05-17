package com.winston.code;

import com.winston.vaildate.code.ImageCode;
import com.winston.vaildate.code.ValidateCodeGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @ClassName DemoImageCodeGenerator
 * @Description 示例：覆盖core项目中的图形验证码生成逻辑
 * @Author Winston
 * @Date 2019/4/16 21:38
 * @Version 1.0
 **/
// @Component("imageCodeGenerator")的名字需要跟core项目中注入spring中的图形验证码生成器类一致才能覆盖
//@Component("imageCodeGenerator")
//public class DemoImageCodeGenerator implements ValidateCodeGenerator {
//    @Override
//    public ImageCode createImageCode(ServletWebRequest request) {
//        System.out.println("比core项目中更高级的图形验证码生成逻辑");
//        return null;
//    }
//}
