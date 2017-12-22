/*
package cn.longzzai.security.core.validate.sms;

import cn.longzzai.security.core.constant.SecurityConstant;
import cn.longzzai.security.core.exception.CodeValidateFailException;
import cn.longzzai.security.core.DTO.ImageCodeDTO;
import cn.longzzai.security.core.properties.SecurityRootProperties;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 图片验证码验证
 *
 * @author longcho
 * 2017-10-23-11:46
 *//*

@Data
public class SmsCodeValicateFilter extends OncePerRequestFilter implements InitializingBean{
    private SecurityRootProperties securityRootProperties;
    private AuthenticationFailureHandler authenticationHandler;
    private Set<String> urls = new HashSet<>();
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    //@Override
    //public void afterPropertiesSet() throws ServletException {
    //    super.afterPropertiesSet();
    //    String[] urlsConfig =StringUtils.splitByWholeSeparatorPreserveAllTokens(securityRootProperties.getImageCode().getUrl() , ",");
    //    for (String s : urlsConfig) {
    //        urls.add(s);
    //    }
    //    //基本的login请求url
    //    urls.add("/authentication/form");
    //}

    public SmsCodeValicateFilter() {
    }

    public SmsCodeValicateFilter(SecurityRootProperties securityRootProperties, AuthenticationFailureHandler authenticationHandler) {
        this.securityRootProperties = securityRootProperties;
        this.authenticationHandler = authenticationHandler;
        String[] urlsConfig =StringUtils.splitByWholeSeparatorPreserveAllTokens(securityRootProperties.getCode().getImage().getUrl() , ",");
            for (String s : urlsConfig) {
                urls.add(s);
            }
            //基本的login请求url
            urls.add("/authentication/mobile");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        boolean action = false;
        for (String url : urls) {
            if (antPathMatcher.match(url , request.getRequestURI())){
                action = true;
            }
        }
        if (action){
            try {
                valicate(request);

            } catch (CodeValidateFailException e) {
                authenticationHandler.onAuthenticationFailure(request ,response ,e);
                return;
            }
        }
        filterChain.doFilter(request , response);
    }


}
*/
