package data.service;

import models.Deal;

import java.util.List;

public interface DealDAO {

    Integer addDeal(Double price, String name, String type);

    void listDeals();

    void updateDeal(Integer dealID, Double price, String name, String type);

    void deleteDeal(Integer dealID);

    List<Deal> getDeals();
}
