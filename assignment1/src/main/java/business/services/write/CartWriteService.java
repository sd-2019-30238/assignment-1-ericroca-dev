package business.services.write;

import business.interfaces.write.CartWrite;
import mediator.ConcreteMediator;
import mediator.Mediator;
import org.springframework.stereotype.Service;

@Service
public class CartWriteService implements CartWrite {

    private Mediator mediator;

    public CartWriteService() {
        mediator = new ConcreteMediator();
    }

    @Override
    public void addToCart(String username, String name) {
        mediator.addToCart(username, name);
    }

    @Override
    public void deleteItem(String username, String name) {
        mediator.deleteItem(username, name);
    }
}
