package cn.longzzai.security.core.social.weixin.config;

import cn.longzzai.security.core.properties.SecurityRootProperties;
import cn.longzzai.security.core.properties.WeixinProperties;
import cn.longzzai.security.core.social.LongzzaiWeixinConnectView;
import cn.longzzai.security.core.social.weixin.connect.WeixinConnectFactory;
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
@ConditionalOnProperty("longzzai.security.social.weixin.appId")
public class WeixinAutoConfig extends SocialAutoConfigurerAdapter {
    @Autowired
    private SecurityRootProperties securityRootProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        WeixinProperties weixinProperties = securityRootProperties.getSocial().getWeixinProperties();
        return new WeixinConnectFactory(weixinProperties.getProviderId(), weixinProperties.getAppId(), weixinProperties.getAppSecret());
    }

    @Bean({"connect/weixinConnect", "connect/weixinConnected"})
    @ConditionalOnMissingBean(name = "weixinConnectedView")
    public View weixinConnectedView() {
        return new LongzzaiWeixinConnectView();
    }
}
