package business.interfaces.write;

import java.util.List;

public interface OrderWrite {

    void checkout(String username, List<String> names);

    void updateStatus(Integer id, String status);
}
