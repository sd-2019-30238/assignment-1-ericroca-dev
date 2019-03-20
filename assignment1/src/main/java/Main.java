import data.UserDAO;

public class Main {

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

        Integer userID1 = userDAO.addUser("demo1", "pass1", "user");
        Integer userID2 = userDAO.addUser("demo2", "pass2", "staff");
        Integer userID3 = userDAO.addUser("demo3", "pass3", "user");

        userDAO.listUsers();

        userDAO.updateUser(userID1, "demo1", "pass1", "staff");

        userDAO.deleteUser(userID2);

        userDAO.listUsers();
    }
}
