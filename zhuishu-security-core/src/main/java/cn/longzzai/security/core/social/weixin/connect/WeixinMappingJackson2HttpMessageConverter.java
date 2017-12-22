package cn.longzzai.security.core.social.weixin.connect;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加txt/plain支持
 *
 * @author longcho
 * 2017-12-14-9:30
 */
public class WeixinMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {

    @Override
    public void setSupportedMediaTypes(List<MediaType> supportedMediaTypes) {
        List newSupportedMediaTypes = new ArrayList(supportedMediaTypes);
        newSupportedMediaTypes.add(MediaType.TEXT_PLAIN);
        super.setSupportedMediaTypes(newSupportedMediaTypes);
    }
}
