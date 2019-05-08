package springtest;

import business.implementation.LoginServiceImpl;
import business.service.LoginService;
import models.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringTest {

    @RequestMapping("/test")
    public User test(@RequestParam(value="username") String username) {
        LoginService loginService = new LoginServiceImpl();
        return loginService.getUserByUsername(username);
    }
}
