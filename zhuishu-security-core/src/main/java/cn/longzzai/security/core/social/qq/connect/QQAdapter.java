package cn.longzzai.security.core.social.qq.connect;

import cn.longzzai.security.core.social.qq.api.QQApi;
import cn.longzzai.security.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @author longcho
 * 2017-12-06-23:30
 */
public class QQAdapter implements ApiAdapter<QQApi> {

    @Override
    public boolean test(QQApi api) {
        return true;
    }

    @Override
    public void setConnectionValues(QQApi api, ConnectionValues values) {
        QQUserInfo qqUserInfo = api.getQQUserInfo();
        values.setDisplayName(qqUserInfo.getNickname());
        values.setImageUrl(qqUserInfo.getFigureurl_qq_1());
        values.setProfileUrl(null);
        values.setProviderUserId(qqUserInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQApi api) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateStatus(QQApi api, String message) {
        //do noting
    }
}
