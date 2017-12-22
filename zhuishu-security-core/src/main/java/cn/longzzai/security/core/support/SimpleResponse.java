package cn.longzzai.security.core.support;

import lombok.Data;

/**
 * 简单的json包装
 *
 * @author longcho
 * 2017-10-21-9:29
 */
@Data
public class SimpleResponse {
    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }
}
