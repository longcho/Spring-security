package cn.longzzai.security.core.validate.image;

import cn.longzzai.security.core.DTO.ImageCodeDTO;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/**
 * 短信验证码发送
 *
 * @author longcho
 * 2017-11-04-12:26
 */
public interface ImageCodeSender {
    void send(ServletWebRequest request, ImageCodeDTO imageCodeDTO) throws IOException;
}
