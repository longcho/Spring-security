package cn.longzzai.security.core.social.utils;

import cn.longzzai.security.core.exception.AppException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.social.connect.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.TimeUnit;

/**
 * @author longcho
 * 2017-12-18-17:04
 */
@Component
public class AppSignUpUtils {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
    @Autowired
    private UsersConnectionRepository usersConnectionRepository;
    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;
    public void saveConnectionDataOnRedis(ServletWebRequest request , ConnectionData connectionData){
        String key = getKey(request);
        redisTemplate.opsForValue().set( key, connectionData, 7200, TimeUnit.MINUTES);
    }

    public void doPostSignUp(String userId, ServletWebRequest request){
        String key = getKey(request);
        if(!redisTemplate.hasKey(key)){
            throw new AppException("未找到用户账号信息");
        }
        ConnectionData connectionData = (ConnectionData) redisTemplate.opsForValue().get(key);
        ConnectionFactory<?> connectionFactory = connectionFactoryLocator.getConnectionFactory(connectionData.getProviderId());
        ConnectionRepository connectionRepository = usersConnectionRepository.createConnectionRepository(userId);
        connectionRepository.addConnection(connectionFactory.createConnection(connectionData));
        redisTemplate.delete(key);
    }

    private String getKey(ServletWebRequest request) {
        String deviceId = request.getHeader("deviceId");
        if (StringUtils.isBlank(deviceId)){
            throw new AppException("没有设备信息");
        }
        return "spring:security:app.connect-deviceId" +deviceId;
    }
}
