package controller;

import business.interfaces.read.OrderRead;
import business.interfaces.write.OrderWrite;
import business.services.read.OrderReadService;
import business.services.write.OrderWriteService;
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
        OrderRead orderReadService = new OrderReadService();
        return orderReadService.getOrders();
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void checkout(CheckoutHolder holder) {
        OrderWrite orderWriteService = new OrderWriteService();
        orderWriteService.checkout(holder.getUsername(), holder.getNames(), holder.getPrices());
    }

    @RequestMapping(value = "/{username}/order", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Order> getUserOrders(@PathVariable("username") String username) {
        OrderRead orderReadService = new OrderReadService();
        return orderReadService.getUserOrders(username);
    }

    @RequestMapping(value = "/{username}/delivered", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Order> getUserDeliveredOrderes(@PathVariable("username") String username) {
        OrderRead orderReadService = new OrderReadService();
        return orderReadService.getUserDeliveredOrders(username);
    }

    @RequestMapping(value = "/order", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateStatus(@RequestBody Order order) {
        OrderWrite orderWriteService = new OrderWriteService();
        orderWriteService.updateStatus(order.getId(), order.getStatus());
    }
}
