package com.winston.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName UserQueryCondition
 * @Description
 * @Author Winston
 * @Date 2019/4/14 13:41
 * @Version 1.0
 **/
@Data
public class UserQueryCondition {
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "用户年龄start")
    private Integer age;
    @ApiModelProperty(value = "用户年龄end")
    private Integer ageTo;
    private String xx;
}
