package cn.longzzai.security.core.DTO;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 图片验证码
 * @param image 验证码图片
 * @param code 验证码
 * @param expireTime 过期时间
 * @author longcho
 * 2017-10-23-9:55
 */
@Data
public class ImageCodeDTO extends CodeDTO{
    //验证码图片
    private BufferedImage image;

    public ImageCodeDTO(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code , expireTime);
        this.image = image;
    }
    public ImageCodeDTO(BufferedImage image, String code, int expireIn) {
        super(code , LocalDateTime.now().plusSeconds(expireIn));
        this.image = image;
    }
}
