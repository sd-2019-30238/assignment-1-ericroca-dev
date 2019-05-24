package business.services.read;

import business.interfaces.read.CartRead;
import mediator.ConcreteMediator;
import mediator.Mediator;
import models.Deal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartReadService implements CartRead {

    private Mediator mediator;

    public CartReadService() {
        mediator = new ConcreteMediator();
    }

    @Override
    public List<Deal> getUserCart(String username) {
        return mediator.getUserCart(username);
    }
}
