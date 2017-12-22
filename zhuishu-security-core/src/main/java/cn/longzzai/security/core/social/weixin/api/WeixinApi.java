package cn.longzzai.security.core.social.weixin.api;

/**
 * @author longcho
 * 2017-12-06-17:06
 */
public interface WeixinApi{
    WeixinUserInfo getWeixinUserInfo(String openid);
}
