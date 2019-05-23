package business.interfaces;

import models.Deal;

import java.util.List;

public interface CartRead {

    List<Deal> getUserCart(String username);
}
