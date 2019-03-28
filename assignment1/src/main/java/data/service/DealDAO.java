package data.service;

import models.Deal;

import java.util.List;

public interface DealDAO {

    Integer addDeal(Double price, String name, String type, Integer quantity);

    void listDeals();

    void updateDeal(Integer dealID, Double price, String name, String type, Integer quantity);

    void deleteDeal(Integer dealID);

    List<Deal> getDeals();

    List<Deal> getDealsByPrice(Double price);

    List<Deal> getDealsByName(String name);

    List<Deal> getDealsByType(String type);

    List<Deal> getDealsByPriceAndName(Double price, String name);

    List<Deal> getDealsByPriceAndType(Double price, String type);

    List<Deal> getDealsByNameAndType(String name, String type);

    List<Deal> getDealsByAll(Double price, String name, String type);

    Deal findByName(String name);
}
