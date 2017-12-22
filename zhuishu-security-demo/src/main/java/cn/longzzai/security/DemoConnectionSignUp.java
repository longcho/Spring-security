package cn.longzzai.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

/**
 * 有了后，不会跳到登录界面
 * @author longcho
 * 2017-12-13-10:30
 */
//@Component
public class DemoConnectionSignUp implements ConnectionSignUp {

    @Override
    public String execute(Connection<?> connection) {
        return connection.getDisplayName();
    }
}
