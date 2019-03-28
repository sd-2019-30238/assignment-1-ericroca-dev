package data.service;

public interface DealDAO {

    Integer addDeal(Double price, String name, String type);

    void listDeals();

    void updateDeal(Integer dealID, Double price, String name, String type);

    void deleteDeal(Integer dealID);
}
