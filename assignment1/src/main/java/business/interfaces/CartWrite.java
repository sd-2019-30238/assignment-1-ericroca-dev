package business.interfaces;

public interface CartWrite {

    void addToCart(String username, String name);

    void deleteItem(String username, String name);
}
