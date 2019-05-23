package controller;

import business.interfaces.CartRead;
import business.interfaces.CartWrite;
import business.services.CartReadService;
import business.services.CartWriteService;
import models.Deal;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    @RequestMapping(value = "/{username}/cart", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addToCart(@PathVariable("username") String username, @RequestBody String name) {
        CartWrite cartWriteService = new CartWriteService();
        cartWriteService.addToCart(username, name);
    }

    @RequestMapping(value = "{username}/cart", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Deal> getUserCart(@PathVariable("username") String username) {
        CartRead cartReadService = new CartReadService();
        return cartReadService.getUserCart(username);
    }

    @RequestMapping(value = "{username}/cart", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteItem(@PathVariable("username") String username, @RequestBody String name) {
        CartWrite cartWriteService = new CartWriteService();
        cartWriteService.deleteItem(username, name);
    }
}
