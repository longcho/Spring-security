package cn.longzzai.client1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author longcho
 * 2017-12-19-21:20
 */
@SpringBootApplication
@RestController
@EnableOAuth2Sso
public class Client1Application {
    @GetMapping("/user")
    public Authentication getUser(Authentication authentication){
        return authentication;
    }
    public static void main(String[] args) {
        SpringApplication.run(Client1Application.class, args);
    }
}
