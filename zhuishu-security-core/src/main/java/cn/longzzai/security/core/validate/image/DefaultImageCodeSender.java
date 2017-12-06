package cn.longzzai.security.core.validate.image;

import cn.longzzai.security.core.DTO.ImageCodeDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * 短信验证码生成
 *
 * @author longcho
 * 2017-11-04-12:27
 */
@Component
public class DefaultImageCodeSender implements ImageCodeSender {


    @Override
    public void send(ServletWebRequest request, ImageCodeDTO imageCodeDTO) throws IOException {
        ImageIO.write(imageCodeDTO.getImage() ,"jpg" ,  request.getResponse().getOutputStream());
    }
}
