import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileHandler.loadUsers();
        FileHandler.loadBooks();
        FileHandler.loadIssueRecords();
        printTitle();

        System.out.println("""
                =============================
                1. Admin Login
                2. User Login
                3. Register (New User)
                4. Exit
                =============================
                """);
        System.out.println("""
                Press 1 for Admin Login
                Press 2 for User Login
                Press 3 for Register (New User)
                Press 4 for Exit
                """);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Enter your credentials: ");
                System.out.print("Enter username: ");
                String username = sc.nextLine();
                System.out.print("Enter password: ");
                String password = sc.nextLine();
                AuthService authService1 = new AuthService();
                authService1.loginAdmin(username, password);
                if (authService1.loginAdmin(username, password) != null) {
                    System.out.println("Login successful");
                } else {
                    System.out.println("Login failed");
                }
                FileHandler.saveUsers(User.getUserList());
                AdminService adminService = new AdminService();
                adminService.adminDashboard();
                break;
            case 2:
                System.out.println("Enter credentials: ");
                System.out.print("Enter username: ");
                String username2 = sc.nextLine();
                System.out.print("Enter password: ");
                String password2 = sc.nextLine();
                AuthService authService2 = new AuthService();
                User loggedInUser = authService2.loginUser(username2, password2);
                if (loggedInUser != null) {
                    System.out.println("Login successful");
                    UserService userService = new UserService(loggedInUser);
                    userService.userDashboard();
                } else {
                    System.out.println("Login failed");
                }
                break;
            case 3:
                System.out.println("Enter your credentials: ");
                System.out.print("Enter your name: ");
                String name3 = sc.nextLine();
                System.out.print("Enter username: ");
                String username3 = sc.nextLine();
                System.out.print("Enter password: ");
                String password3 = sc.nextLine();
                AuthService authService3 = new AuthService();
                authService3.register(name3, username3, password3);
                System.out.println("Registration Successful!!!");
                break;
            case 4:
                System.out.println("Exit");
                break;
            default:
                System.out.println("Invalid choice");
        }
        sc.close();
    }

    public static void printTitle() {
        // LIBRARY
        System.out.println(" ██╗     ██╗██████╗ ██████╗  █████╗ ██████╗ ██╗   ██╗");
        System.out.println(" ██║     ██║██╔══██╗██╔══██╗██╔══██╗██╔══██╗╚██╗ ██╔╝");
        System.out.println(" ██║     ██║██████╔╝██████╔╝███████║██████╔╝ ╚████╔╝ ");
        System.out.println(" ██║     ██║██╔══██╗██╔══██╗██╔══██║██╔══██╗  ╚██╔╝  ");
        System.out.println(" ███████╗██║██████╔╝██║  ██║██║  ██║██║  ██║   ██║   ");
        System.out.println(" ╚══════╝╚═╝╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝  ");
        System.out.println();
        System.out.println(" ---------------------------------------------------- ");
        System.out.println();

        // MANAGEMENT
        System.out.println(
                " ███╗   ███╗ █████╗ ███╗   ██╗ █████╗  ██████╗ ███████╗███╗   ███╗███████╗███╗   ██╗████████╗");
        System.out.println(
                " ████╗ ████║██╔══██╗████╗  ██║██╔══██╗██╔════╝ ██╔════╝████╗ ████║██╔════╝████╗  ██║╚══██╔══╝");
        System.out.println(
                " ██╔████╔██║███████║██╔██╗ ██║███████║██║  ███╗█████╗  ██╔████╔██║█████╗  ██╔██╗ ██║   ██║   ");
        System.out.println(
                " ██║╚██╔╝██║██╔══██║██║╚██╗██║██╔══██║██║   ██║██╔══╝  ██║╚██╔╝██║██╔══╝  ██║╚██╗██║   ██║   ");
        System.out.println(
                " ██║ ╚═╝ ██║██║  ██║██║ ╚████║██║  ██║╚██████╔╝███████╗██║ ╚═╝ ██║███████╗██║ ╚████║   ██║   ");
        System.out.println(
                " ╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝ ╚═════╝ ╚══════╝╚═╝     ╚═╝╚══════╝╚═╝  ╚═══╝   ╚═╝  ");
        System.out.println();
        System.out.println(" ---------------------------------------------------- ");
        System.out.println();

        // SYSTEM
        System.out.println(" ███████╗██╗   ██╗███████╗████████╗███████╗███╗   ███╗");
        System.out.println(" ██╔════╝╚██╗ ██╔╝██╔════╝╚══██╔══╝██╔════╝████╗ ████║");
        System.out.println(" ███████╗ ╚████╔╝ ███████╗   ██║   █████╗  ██╔████╔██║");
        System.out.println(" ╚════██║  ╚██╔╝  ╚════██║   ██║   ██╔══╝  ██║╚██╔╝██║");
        System.out.println(" ███████║   ██║   ███████║   ██║   ███████╗██║ ╚═╝ ██║");
        System.out.println(" ╚══════╝   ╚═╝   ╚══════╝   ╚═╝   ╚══════╝╚═╝     ╚═╝");
        System.out.println();
    }
}
