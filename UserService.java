import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class UserService {
    Scanner sc = new Scanner(System.in);
    private User user;

    public UserService(User user) {
        this.user = user;
    }

    public void userDashboard() {
        System.out.println("""
                =========================
                    USER DASHBOARD
                =========================
                1. Browse All Books
                2. Search Book
                3. Issue a Book
                4. Return a Book
                5. My Issued Books
                6. Logout
                =========================
                """);
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                browseAllBooks();
                break;
            case 2:
                searchBook();
                break;
            case 3:
                issueBook();
                break;
            case 4:
                returnBook();
                break;
            case 5:
                myIssuedBooks();
                break;
            case 6:
                logout();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    public void browseAllBooks() {
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

    public void searchBook() {
        System.out.print("Enter book name: ");
        String name = sc.nextLine();
        for (Book book : Book.getBookList()) {
            if (book.getTitle().equalsIgnoreCase(name)) {
                System.out.println(book);
                return;
            }
        }
    }

    public void issueBook() {
        System.out.println("Enter book name: ");
        String name = sc.nextLine();

        for (Book book : Book.getBookList()) {
            if (book.getTitle().equalsIgnoreCase(name)) {
                if (book.getAvailableCopies() > 0) {
                    book.borrowBook();
                    String issueDate = LocalDate.now().toString();
                    String dueDate = LocalDate.now().plusDays(14).toString();

                    if (user == null) {
                        System.out.println("Error: User not logged in.");
                        return;
                    }
                    new IssueRecord(user.getID(), book.getID(), book.getTitle(), issueDate, dueDate,
                            "Not Returned");
                    FileHandler.saveBooks(Book.getBookList());
                    FileHandler.saveIssueRecords(IssueRecord.getRecordList());
                    System.out.println("Book issued successfully!");
                } else {
                    System.out.println("Sorry, the book is currently out of stock.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void returnBook() {
        if (user == null) {
            System.out.println("Error: User not logged in.");
            return;
        }

        System.out.print("Enter book name: ");
        String name = sc.nextLine();

        for (Book book : Book.getBookList()) {
            if (book.getTitle().equalsIgnoreCase(name)) {
                System.out.println(book);

                boolean recordFound = false;
                for (IssueRecord record : IssueRecord.getRecordList()) {
                    if (record.getUserId() == user.getID() && record.getBookId() == book.getID()
                            && !record.isReturned()) {
                        record.setReturned(true);
                        record.setReturnDate(LocalDate.now().toString());
                        recordFound = true;
                        break;
                    }
                }

                if (!recordFound) {
                    System.out.println("Error: You have not issued this book or it's already returned.");
                    return;
                }

                if (book.getAvailableCopies() < book.getTotalCopies()) {
                    book.returnBook();
                } else {
                    System.out.println(
                            "Warning: All copies of this book are already in the library, but updating your issue record anyway.");
                }
                FileHandler.saveBooks(Book.getBookList());
                FileHandler.saveIssueRecords(IssueRecord.getRecordList());
                System.out.println("Book returned successfully!");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void myIssuedBooks() { // to see the issued books
        System.out.println(
                "------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s | %-30s | %-15s | %-15s%n", "Book ID", "Title", "Issue Date", "Due Date");
        System.out.println(
                "------------------------------------------------------------------------------------------------------");

        boolean found = false;
        for (IssueRecord r : IssueRecord.getRecordList()) {
            if (r.getUserId() == user.getID() && !r.isReturned()) {
                found = true;
                String bookTitle = "Unknown";
                for (Book book : Book.getBookList()) {
                    if (book.getID() == r.getBookId()) {
                        bookTitle = book.getTitle();
                        break;
                    }
                }
                System.out.printf("%-10s | %-30s | %-15s | %-15s%n",
                        r.getBookId(), bookTitle, r.getIssueDate(), r.getDueDate());
            }
        }

        if (!found) {
            System.out.println("You have no issued books.");
        }
        System.out.println(
                "------------------------------------------------------------------------------------------------------");
    }

    public void logout() {
        System.out.println("Logging out...");
        sc.close();
    }
}
