package cn.longzzai.security.core.DTO;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * txt验证码
 * @param code 验证码
 * @param expireTime 过期时间
 * @author longcho
 * 2017-10-23-9:55
 */
@Data
public class CodeDTO implements Serializable{
    //验证码
    private String code;
    //过期时间
    private LocalDateTime expireTime;

    public CodeDTO() {
    }
    public CodeDTO(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }
    public CodeDTO( String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }
    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expireTime);
    }
}
