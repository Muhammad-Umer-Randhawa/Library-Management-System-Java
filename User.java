import java.util.ArrayList;

public class User {
    private static ArrayList<User> userList = new ArrayList<>();

    private int id;
    private String name;
    private String username;
    private String password;
    private String role;
    private ArrayList<Integer> issuedBookIDs;

    public User(int id, String name, String username, String password, String role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.issuedBookIDs = new ArrayList<>();
        userList.add(this);
    }

    public static ArrayList<User> getUserList() {
        return userList;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public ArrayList<Integer> getIssuedBookIDs() {
        return issuedBookIDs;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setIssuedBookIDs(ArrayList<Integer> issuedBookIDs) {
        this.issuedBookIDs = issuedBookIDs;
    }

    public void issueBook(int bookID) {
        issuedBookIDs.add(bookID);
    }

    public void returnBook(int bookID) {
        issuedBookIDs.remove(bookID);
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nName: " + name + "\nUsername: " + username + "\nRole: " + role;
    }

}
