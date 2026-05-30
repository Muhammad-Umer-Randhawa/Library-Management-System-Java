import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AdminService {
    Scanner sc = new Scanner(System.in);

    public void adminDashboard() {
        System.out.println("""
                =========================
                    ADMIN DASHBOARD
                =========================
                1. Add Book
                2. Remove Book
                3. Update Book
                4. View All Books
                5. View All Users
                6. View Issued Books
                7. Logout
                =========================
                """);
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                addBook();
                break;
            case 2:
                removeBook();
                break;
            case 3:
                updateBook();
                break;
            case 4:
                viewAllBooks();
                break;
            case 5:
                viewAllUsers();
                break;
            case 6:
                viewIssuedBooks();
                break;
            case 7:
                logout();
                break;
            default:
                System.out.println("Invalid choice");
        }

    }

    public void addBook() {
        System.out.println("Enter book details: ");
        System.out.print("Enter book id: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter book name: ");
        String name = sc.nextLine();
        System.out.print("Enter book author: ");
        String author = sc.nextLine();
        System.out.print("Enter book genre: ");
        String genre = sc.nextLine();
        System.out.print("Enter book total copies: ");
        int totalCopies = sc.nextInt();
        Book book = new Book(id, name, author, genre, totalCopies);
        FileHandler.saveBooks(book);
        System.out.println("Book added successfully!");
    }

    public void removeBook() {
        System.out.println("Enter book details: ");
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        for (Book book : Book.getBookList()) {
            if (book.getID() == id) {
                Book.getBookList().remove(book);
                FileHandler.saveBooks(Book.getBookList());
                System.out.println("Book removed successfully!");
                return;
            }
        }
    }

    public void updateBook() {
        System.out.println("Enter details: ");
        System.out.print("Enter id: ");
        int id = sc.nextInt();
        System.out.print("Enter it's # of copies: ");
        int copies = sc.nextInt();
        for (Book book : Book.getBookList()) {
            if (book.getID() == id) {
                int difference = copies - book.getTotalCopies();
                book.setTotalCopies(copies);
                book.setAvailableCopies(book.getAvailableCopies() + difference);
                if (book.getAvailableCopies() < 0) {
                    book.setAvailableCopies(0);
                }
                FileHandler.saveBooks(Book.getBookList());
                System.out.println("Book updated successfully!");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void viewAllBooks() {
        System.out.println(
                "----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s | %-30s | %-25s | %-15s | %-12s | %-16s%n", "Book ID", "Title", "Author", "Genre",
                "Total Copies", "Available Copies");
        System.out.println(
                "----------------------------------------------------------------------------------------------------------------------");
        try (BufferedReader reader = new BufferedReader(new FileReader("books.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 6) {
                    if (parts[0].trim().equalsIgnoreCase("ID")) {
                        continue;
                    }
                    System.out.printf("%-10s | %-30s | %-25s | %-15s | %-12s | %-16s%n",
                            parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(), parts[4].trim(),
                            parts[5].trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading books from file: " + e.getMessage());
        }
        System.out.println(
                "----------------------------------------------------------------------------------------------------------------------");
    }

    public void viewAllUsers() {
        for (User user : User.getUserList()) {
            System.out.println(user);
            System.out.println(
                    "----------------------------------------------------------------------------------------------------------------------");
        }
    }

    public void viewIssuedBooks() {
        System.out.println(
                "----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s | %-10s | %-30s | %-15s | %-15s | %-15s%n", "User ID", "Book ID", "Book Name", "Issue Date",
                "Due Date", "Return Date");
        System.out.println(
                "----------------------------------------------------------------------------------------------------------------------");
        for (IssueRecord record : IssueRecord.getRecordList()) {
            System.out.printf("%-10s | %-10s | %-30s | %-15s | %-15s | %-15s%n",
                    record.getUserId(), record.getBookId(), record.getBookName(), record.getIssueDate(),
                    record.getDueDate(),
                    record.getReturnDate());
        }
        System.out.println(
                "----------------------------------------------------------------------------------------------------------------------");
    }

    public void logout() {
        System.out.println("Logout successful");
        sc.close();
    }
}