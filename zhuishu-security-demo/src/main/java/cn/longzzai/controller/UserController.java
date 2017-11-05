package cn.longzzai.controller;

import cn.longzzai.dto.User;
import cn.longzzai.dto.UserTestQuery;
import cn.longzzai.exception.MyException;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.experimental.var;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author longcho
 * 2017-10-15-10:17
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "用户信息查询")
    public List<User> getOne(@Valid UserTestQuery userTestQuery , BindingResult errors){
        if (errors.hasErrors()){
            errors.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
        }
        System.out.println(ReflectionToStringBuilder.toString(userTestQuery , ToStringStyle.MULTI_LINE_STYLE));
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@ApiParam(value = "用户编号")@PathVariable String id){
       // throw new MyException("未找到该页面" ,id);
        System.out.println("id:======" + id);
        return new User();
    }
}
