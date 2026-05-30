import java.util.ArrayList;

public class AuthService {

    public User loginUser(String username, String password) {
        ArrayList<User> users = User.getUserList();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)
                    && user.getRole().equalsIgnoreCase("User")) {
                return user;
            }
        }
        return null;
    }

    public User loginAdmin(String username, String password) {
        ArrayList<User> users = User.getUserList();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)
                    && user.getRole().equalsIgnoreCase("Admin")) {
                return user;
            }
        }
        return null;
    }

    public boolean register(String name, String username, String password) {
        ArrayList<User> users = User.getUserList();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists!");
                return false;
            }
        }

        int newId = 100;
        for (User user : users) {
            if (user.getID() >= newId) {
                newId = user.getID() + 1;
            }
        }
        new User(newId, name, username, password, "User");
        FileHandler.saveUsers(User.getUserList());
        return true;
    }

    public boolean changePassword(String username, String oldPassword, String newPassword) {
        ArrayList<User> users = User.getUserList();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(oldPassword)) {
                user.setPassword(newPassword);
                FileHandler.saveUsers(users);
                System.out.println("Password changed Successfully!!!");
                return true;
            }
        }
        return false;
    }
}
