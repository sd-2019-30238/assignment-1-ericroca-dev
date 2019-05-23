package controller;

import business.services.LoginServiceImpl;
import business.interfaces.LoginService;
import models.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @RequestMapping(value = "/createAccount", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public String createAccount(@RequestBody User user) {
        LoginService loginService = new LoginServiceImpl();
        return loginService.createAccount(user.getUsername(), user.getPassword());
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String validateUser(@RequestBody User user) {
        LoginService loginService = new LoginServiceImpl();
        return loginService.validateUser(user.getUsername(), user.getPassword());
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public User getUserByUsername(@PathVariable("username") String username) {
        LoginService loginService = new LoginServiceImpl();
        return loginService.getUserByUsername(username);
    }
}
