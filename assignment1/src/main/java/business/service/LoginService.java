package business.service;

public interface LoginService {

    String createAccount(String username, String password);

    String validateUser(String username, String password);
}
