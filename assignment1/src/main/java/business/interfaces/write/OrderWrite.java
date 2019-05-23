package business.interfaces.write;

import java.util.List;

public interface OrderWrite {

    void checkout(String username, List<String> names, List<String> prices);

    void updateStatus(Integer ID, String status);
}
