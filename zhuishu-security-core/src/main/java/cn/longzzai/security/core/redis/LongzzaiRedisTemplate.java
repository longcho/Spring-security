package cn.longzzai.security.core.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author longcho
 * 2017-12-23-19:18
 */
//@Component
public class LongzzaiRedisTemplate extends RedisTemplate {
   // @Autowired
    private StringRedisSerializer stringRedisSerializer;

    @Override
    public void setKeySerializer(RedisSerializer serializer) {
        serializer.serialize(stringRedisSerializer);
    }

    @Override
    public void setValueSerializer(RedisSerializer serializer) {
        serializer.serialize(stringRedisSerializer);
    }
}
