package springtest;

import business.interfaces.read.LoginRead;
import business.services.read.LoginReadService;
import models.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringTest {

    @RequestMapping("/test")
    public User test(@RequestParam(value="username") String username) {
        LoginRead loginReadService = new LoginReadService();
        return loginReadService.getUserByUsername(username);
    }
}
