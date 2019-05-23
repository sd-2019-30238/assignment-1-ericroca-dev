package controller;

import business.interfaces.read.LoginRead;
import business.interfaces.write.LoginWrite;
import business.services.read.LoginReadService;
import business.services.write.LoginWriteService;
import models.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @RequestMapping(value = "/createAccount", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public String createAccount(@RequestBody User user) {
        LoginWrite loginWriteService = new LoginWriteService();
        return loginWriteService.createAccount(user.getUsername(), user.getPassword());
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String validateUser(@RequestBody User user) {
        LoginRead loginReadService = new LoginReadService();
        return loginReadService.validateUser(user.getUsername(), user.getPassword());
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public User getUserByUsername(@PathVariable("username") String username) {
        LoginRead loginReadService = new LoginReadService();
        return loginReadService.getUserByUsername(username);
    }
}
