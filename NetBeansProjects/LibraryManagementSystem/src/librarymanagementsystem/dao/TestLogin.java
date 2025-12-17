package librarymanagementsystem.dao;

public class TestLogin {

    public static void main(String[] args) {

        boolean result = LibrarianDAO.login("admin", "admin");

        if (result) {
            System.out.println("Login Successful!");
        } else {
            System.out.println("Invalid Username or Password");
        }
    }
}
