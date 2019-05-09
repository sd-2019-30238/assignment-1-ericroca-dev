package controller;

import business.implementation.DealServiceImpl;
import business.service.DealService;
import models.Deal;
import models.Discount;
import models.DiscountFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DealController {

    @RequestMapping(value = "/deal", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Deal> getDeals() {
        DealService dealService = new DealServiceImpl();
        return dealService.getDeals();
    }

    @RequestMapping(value = "/deal/filterByPrice", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Deal> getFilteredDealsByPrice(@RequestParam("price") Double price) {
        DealService dealService = new DealServiceImpl();
        return dealService.getFilteredDealsByPrice(price);
    }

    @RequestMapping(value = "/deal/filterByName", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Deal> getFilteredDealsByName(@RequestParam("name") String name) {
        DealService dealService = new DealServiceImpl();
        return dealService.getFilteredDealsByName(name);
    }

    @RequestMapping(value = "/deal/filterByType", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Deal> getFilteredDealsByType(@RequestParam("type") String type) {
        DealService dealService = new DealServiceImpl();
        return dealService.getFilteredDealsByType(type);
    }

    @RequestMapping(value = "/deal/filterByPriceAndName", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Deal> getFilteredDealsByPriceAndName(@RequestParam("price") Double price,
                                                     @RequestParam("name") String name) {
        DealService dealService = new DealServiceImpl();
        return dealService.getFilteredDealsByPriceAndName(price, name);
    }

    @RequestMapping(value = "/deal/filterByPriceAndType", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Deal> getFilteredDealsByPriceAndType(@RequestParam("price") Double price,
                                                     @RequestParam("type") String type) {
        DealService dealService = new DealServiceImpl();
        return dealService.getFilteredDealsByPriceAndType(price, type);
    }

    @RequestMapping(value = "/deal/filterByNameAndType", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Deal> getFilteredDealsByNameAndType(@RequestParam("name") String name,
                                                     @RequestParam("type") String type) {
        DealService dealService = new DealServiceImpl();
        return dealService.getFilteredDealsByNameAndType(name, type);
    }

    @RequestMapping(value = "/deal/filter", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Deal> getFilteredDeals(@RequestParam("price") Double price,
                                       @RequestParam("name") String name,
                                       @RequestParam("type") String type) {
        DealService dealService = new DealServiceImpl();
        return dealService.getFilteredDeals(price, name, type);
    }

    @RequestMapping(value = "/deal", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addDeal(@RequestBody Deal deal) {
        DealService dealService = new DealServiceImpl();
        dealService.addDeal(deal.getPrice(), deal.getName(), deal.getType(), deal.getQuantity());
    }

    @RequestMapping(value = "/deal", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void editDeal(@RequestBody Deal deal) {
        DealService dealService = new DealServiceImpl();
        dealService.editDeal(deal.getId(), deal.getPrice(), deal.getName(), deal.getType(), deal.getQuantity());
    }

    @RequestMapping(value = "/deal", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteDeal(@RequestBody String id) {
        DealService dealService = new DealServiceImpl();
        dealService.deleteDeal(Integer.valueOf(id));
    }

    @RequestMapping(value = "/deal/discount", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void applyDiscount(@RequestBody String id) {
        DealService dealService = new DealServiceImpl();
        DiscountFactory discountFactory = new DiscountFactory();
        Discount halfOffDiscount = discountFactory.getDiscount("HALFOFF");
        dealService.applyDiscount(Integer.valueOf(id), halfOffDiscount);
    }
}
