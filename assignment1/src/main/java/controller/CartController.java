package controller;

import business.implementation.CartServiceImpl;
import business.service.CartService;
import models.Deal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    @RequestMapping(value = "/{username}/cart", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addToCart(@PathVariable("username") String username, @RequestBody String name) {
        CartService cartService = new CartServiceImpl();
        cartService.addToCart(username, name);
    }

    @RequestMapping(value = "{username}/cart", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Deal> getUserCart(@PathVariable("username") String username) {
        CartService cartService = new CartServiceImpl();
        return cartService.getUserCart(username);
    }

    @RequestMapping(value = "{username}/cart", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteItem(@PathVariable("username") String username, @RequestBody String name) {
        CartService cartService = new CartServiceImpl();
        cartService.deleteItem(username, name);
    }
}
