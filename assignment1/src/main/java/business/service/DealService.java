package business.service;

import models.Deal;

import java.util.List;

public interface DealService {

    List<Deal> getDeals();

    List<Deal> getFilteredDealsByPrice(Double price);

    List<Deal> getFilteredDealsByName(String name);

    List<Deal> getFilteredDealsByType(String type);

    List<Deal> getFilteredDealsByPriceAndName(Double price, String name);

    List<Deal> getFilteredDealsByPriceAndType(Double price, String type);

    List<Deal> getFilteredDealsByNameAndType(String name, String type);

    List<Deal> getFilteredDeals(Double price, String name, String type);

}
