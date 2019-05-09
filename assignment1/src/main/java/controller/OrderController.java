package controller;

import business.implementation.OrderServiceImpl;
import business.service.OrderService;
import models.CheckoutHolder;
import models.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Order> getOrders() {
        OrderService orderService = new OrderServiceImpl();
        return orderService.getOrders();
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void checkout(CheckoutHolder holder) {
        OrderService orderService = new OrderServiceImpl();
        orderService.checkout(holder.getUsername(), holder.getNames(), holder.getPrices());
    }

    @RequestMapping(value = "/{username}/order", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Order> getUserOrders(@PathVariable("username") String username) {
        OrderService orderService = new OrderServiceImpl();
        return orderService.getUserOrders(username);
    }

    @RequestMapping(value = "/{username}/delivered", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Order> getUserDeliveredOrderes(@PathVariable("username") String username) {
        OrderService orderService = new OrderServiceImpl();
        return orderService.getUserDeliveredOrders(username);
    }

    @RequestMapping(value = "/order", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateStatus(@RequestBody Order order) {
        OrderService orderService = new OrderServiceImpl();
        orderService.updateStatus(order.getId(), order.getStatus());
    }
}
