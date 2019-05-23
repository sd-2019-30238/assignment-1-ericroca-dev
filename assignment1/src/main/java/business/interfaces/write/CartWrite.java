package business.interfaces.write;

public interface CartWrite {

    void addToCart(String username, String name);

    void deleteItem(String username, String name);
}
