package cn.longzzai.dto;

import cn.longzzai.validator.MyNotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author longcho
 * 2017-10-15-10:42
 */
@Data
public class UserTestQuery {
    @MyNotNull(message = "用户名不能为空")
    private String username;
    @ApiModelProperty(value = "年龄")
    private  int age;

}
