package cn.longzzai.security.core.support;

import lombok.Data;

/**
 * @author longcho
 * 2017-12-12-20:31
 */
@Data
public class SocialUserInfo {
    private String providerId;
    private String providerUserId;
    private String nickname;
    private String headimg;
}
