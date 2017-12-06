package cn.longzzai.security.core.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author longcho
 * 2017-10-21-15:31
 */
@Data
@Component
public class SecurityRootProperties {
    @Autowired
    private BrowserProperties browser;

    @Autowired
    private CodeProperties Code;
}
