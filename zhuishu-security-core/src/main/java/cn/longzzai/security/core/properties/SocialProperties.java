package cn.longzzai.security.core.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author longcho
 * 2017-12-07-9:57
 */
@Data
@Component
public class SocialProperties {
    private boolean isRegistStep = false; //是否跳过注册
    @Autowired
    private QQProperties qqProperties;

    @Autowired
    private WeixinProperties weixinProperties;
}
