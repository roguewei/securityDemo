package com.winston.validator;

import com.winston.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName MyConstraintValidator
 * @Description
 * @Author Winston
 * @Date 2019/4/14 15:51
 * @Version 1.0
 **/
// 不用手动添加@Component，实现了ConstraintValidator接口会自动注入spring中
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

    @Autowired
    private HelloService helloService;

    @Override
    public void initialize(MyConstraint myConstraint) {
        System.out.println("my validator init");
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        helloService.greeting("tome0");
        System.out.println(o);
        return false;
    }
}
