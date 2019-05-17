package com.winston.vaildate.code;

import com.winston.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @ClassName ValidateCodeController
 * @Description 测试图形验证码
 * @Author Winston
 * @Date 2019/4/15 21:13
 * @Version 1.0
 **/
@RestController
public class ValidateCodeController {

    // session的key
    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    // 操作session的工具类
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;

    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 1、根据随机数生成图片
        ImageCode imageCode = imageCodeGenerator.createImageCode(new ServletWebRequest(request));
        // 2、将随机数保存到session
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
        // 3、将生成的图片写到接口的响应中
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());

    }

}
