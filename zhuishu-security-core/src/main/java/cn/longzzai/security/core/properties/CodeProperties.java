package cn.longzzai.security.core.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author longcho
 * 2017-11-04-11:21
 */
@Data
@Component
public class CodeProperties {
    @Autowired
    private ImageCodeProperties image;
    @Autowired
    private SmsCodeProperties sms;
}
