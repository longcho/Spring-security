package cn.longzzai.security.app.valicate.code.impl;

import cn.longzzai.security.core.DTO.CodeDTO;
import cn.longzzai.security.core.exception.ValidateCodeException;
import cn.longzzai.security.core.validate.common.ValidateCodeRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.TimeUnit;

/**
 * @author longcho
 * 2017-12-17-20:16
 */
@Component
public class RedisValidateCodeRepository implements ValidateCodeRepository {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public void save(ServletWebRequest request, String key, CodeDTO code) {
        redisTemplate.opsForValue().set(buildKey(request, key) , code, 30, TimeUnit.MINUTES);
    }

    @Override
    public CodeDTO get(ServletWebRequest request, String key) {
        Object code = redisTemplate.opsForValue().get(buildKey(request, key));
        if(code !=null){
            return (CodeDTO)code;
        }
        return null;
    }

    @Override
    public void remove(ServletWebRequest request, String key) {
        redisTemplate.delete(buildKey(request, key));
    }

    private String buildKey(ServletWebRequest request, String key){
        String deviceId = request.getHeader("deviceId");
        if (StringUtils.isBlank(deviceId)){
            throw new ValidateCodeException("请在请求头携带deviceId参数");
        }
        String id = request.getRequest().getSession().getId();
        return "code:" + key + "id:" + id + "deviceId:" + deviceId;
    }
}
