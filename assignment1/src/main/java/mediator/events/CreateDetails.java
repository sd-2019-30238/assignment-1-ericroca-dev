package mediator.events;

import models.Deal;

import java.util.List;

public class CreateDetails {

    public String createDetails(List<String> names, List<Deal> dealList) {
        String details = "";
        for (int i = 0; i < names.size(); ++i) {
            details += "Name: " + names.get(i) + ", Price: " + dealList.get(i).getPrice() + "; ";
        }
        return details;
    }
}
