package cn.longzzai.security.core.social.qq.config;

import cn.longzzai.security.core.properties.QQProperties;
import cn.longzzai.security.core.properties.SecurityRootProperties;
import cn.longzzai.security.core.social.qq.connect.LongzzaiQQConnectView;
import cn.longzzai.security.core.social.qq.connect.QQConnectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

/**
 * @author longcho
 * 2017-12-07-9:17
 */
@Configuration
@ConditionalOnProperty("longzzai.security.social.qq.appId")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {
    @Autowired
    private SecurityRootProperties securityRootProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqProperties = securityRootProperties.getSocial().getQqProperties();
        return new QQConnectFactory(qqProperties.getProviderId() ,qqProperties.getAppId() ,qqProperties.getAppSecret());
    }

    @Bean({"connect/qqConnect", "connect/qqConnected"})
    @ConditionalOnMissingBean(name = "qqConnectedView")
    public View weixinConnectedView() {
        return new LongzzaiQQConnectView();
    }
}
