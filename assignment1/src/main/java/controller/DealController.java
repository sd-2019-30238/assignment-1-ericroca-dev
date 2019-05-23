package controller;

import business.interfaces.read.DealRead;
import business.interfaces.write.DealWrite;
import business.services.read.DealReadService;
import business.services.write.DealWriteService;
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
        DealRead dealReadService = new DealReadService();
        return dealReadService.getDeals();
    }

    @RequestMapping(value = "/deal/filterByPrice", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Deal> getFilteredDealsByPrice(@RequestParam("price") Double price) {
        DealRead dealReadService = new DealReadService();
        return dealReadService.getFilteredDealsByPrice(price);
    }

    @RequestMapping(value = "/deal/filterByName", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Deal> getFilteredDealsByName(@RequestParam("name") String name) {
        DealRead dealReadService = new DealReadService();
        return dealReadService.getFilteredDealsByName(name);
    }

    @RequestMapping(value = "/deal/filterByType", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Deal> getFilteredDealsByType(@RequestParam("type") String type) {
        DealRead dealReadService = new DealReadService();
        return dealReadService.getFilteredDealsByType(type);
    }

    @RequestMapping(value = "/deal/filterByPriceAndName", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Deal> getFilteredDealsByPriceAndName(@RequestParam("price") Double price,
                                                     @RequestParam("name") String name) {
        DealRead dealReadService = new DealReadService();
        return dealReadService.getFilteredDealsByPriceAndName(price, name);
    }

    @RequestMapping(value = "/deal/filterByPriceAndType", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Deal> getFilteredDealsByPriceAndType(@RequestParam("price") Double price,
                                                     @RequestParam("type") String type) {
        DealRead dealReadService = new DealReadService();
        return dealReadService.getFilteredDealsByPriceAndType(price, type);
    }

    @RequestMapping(value = "/deal/filterByNameAndType", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Deal> getFilteredDealsByNameAndType(@RequestParam("name") String name,
                                                     @RequestParam("type") String type) {
        DealRead dealReadService = new DealReadService();
        return dealReadService.getFilteredDealsByNameAndType(name, type);
    }

    @RequestMapping(value = "/deal/filter", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Deal> getFilteredDeals(@RequestParam("price") Double price,
                                       @RequestParam("name") String name,
                                       @RequestParam("type") String type) {
        DealRead dealReadService = new DealReadService();
        return dealReadService.getFilteredDeals(price, name, type);
    }

    @RequestMapping(value = "/deal", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addDeal(@RequestBody Deal deal) {
        DealWrite dealWriteService = new DealWriteService();
        dealWriteService.addDeal(deal.getPrice(), deal.getName(), deal.getType(), deal.getQuantity());
    }

    @RequestMapping(value = "/deal", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void editDeal(@RequestBody Deal deal) {
        DealWrite dealWriteService = new DealWriteService();
        dealWriteService.editDeal(deal.getId(), deal.getPrice(), deal.getName(), deal.getType(), deal.getQuantity());
    }

    @RequestMapping(value = "/deal", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteDeal(@RequestBody String id) {
        DealWrite dealWriteService = new DealWriteService();
        dealWriteService.deleteDeal(Integer.valueOf(id));
    }

    @RequestMapping(value = "/deal/discount", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void applyDiscount(@RequestBody String id) {
        DealWrite dealWriteService = new DealWriteService();
        DiscountFactory discountFactory = new DiscountFactory();
        Discount halfOffDiscount = discountFactory.getDiscount("HALFOFF");
        dealWriteService.applyDiscount(Integer.valueOf(id), halfOffDiscount);
    }
}
