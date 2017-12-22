package cn.longzzai.security.core.social.weixin.connect;

import cn.longzzai.security.core.social.weixin.api.WeixinApi;
import cn.longzzai.security.core.social.weixin.api.WeixinUserInfo;
import lombok.Data;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @author longcho
 * 2017-12-06-23:30
 */
@Data
public class WeixinAdapter implements ApiAdapter<WeixinApi> {

    private String openid;

    public WeixinAdapter() {
    }

    public WeixinAdapter(String openid) {
        this.openid = openid;
    }

    @Override
    public boolean test(WeixinApi api) {
        return true;
    }

    @Override
    public void setConnectionValues(WeixinApi api, ConnectionValues values) {
        WeixinUserInfo weixinUserInfo = api.getWeixinUserInfo(openid);
        values.setDisplayName(weixinUserInfo.getNickname());
        values.setImageUrl(weixinUserInfo.getHeadimgurl());
        values.setProfileUrl(null);
        values.setProviderUserId(weixinUserInfo.getOpenid());
    }

    @Override
    public UserProfile fetchUserProfile(WeixinApi api) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateStatus(WeixinApi api, String message) {
        //do noting
    }
}
