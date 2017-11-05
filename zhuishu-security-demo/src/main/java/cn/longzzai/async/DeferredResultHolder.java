package cn.longzzai.async;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * 异步处理订单
 *
 * @author longcho
 * 2017-10-16-21:21
 */
@Component
@Data
public class DeferredResultHolder {
    private Map<String , DeferredResult<String>> map = new HashMap<>();
}
