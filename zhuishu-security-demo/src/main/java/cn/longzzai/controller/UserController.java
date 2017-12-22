package cn.longzzai.controller;

import cn.longzzai.dto.User;
import cn.longzzai.dto.UserTestQuery;
import cn.longzzai.security.core.properties.SecurityRootProperties;
import cn.longzzai.security.core.social.utils.AppSignUpUtils;
import com.fasterxml.jackson.annotation.JsonView;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author longcho
 * 2017-10-15-10:17
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ProviderSignInUtils providerSignInUtils;
   @Autowired(required = false)
   private AppSignUpUtils appSignUpUtils;
   @Autowired
   private SecurityRootProperties securityRootProperties;

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

    @GetMapping("/me")
    public Object getCurrentUser(Authentication authentication, HttpServletRequest request) throws UnsupportedEncodingException {
        String header = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(header,"bearer ");
        Object info = null;
        if (StringUtils.isNotBlank(token)) {
            Claims body = Jwts.parser().setSigningKey(securityRootProperties.getOauth2().getSigningKey().getBytes("UTF-8"))
                    .parseClaimsJws(token).getBody();
            info = body.get("company");
        }
       if (info !=null){
            log.info("company:{}" ,(String)info);
       }
        return authentication;
    }

    @PostMapping("/regist")
    public void regist(User user, HttpServletRequest request){
            String userId = user.getUsername();
            //当用session储存时
        // providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
            //当用redis储存时
       appSignUpUtils.doPostSignUp(userId, new ServletWebRequest(request));
    }
}
