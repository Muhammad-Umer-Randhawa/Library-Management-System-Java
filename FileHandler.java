import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {

    public static void saveBooks(ArrayList<Book> books) {
        try (FileWriter writer = new FileWriter("books.txt")) {
            writer.write("ID | Title | Author | Genre | Total Copies | Available Copies\n");
            for (Book book : books) {
                writer.write(book.getID() + " | " + book.getTitle() + " | " + book.getAuthor() + " | " + book.getGenre()
                        + " | "
                        + book.getTotalCopies() + " | " + book.getAvailableCopies() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    public static void saveBooks(Book book) {
        try (FileWriter writer = new FileWriter("books.txt", true)) {
            writer.write(
                    book.getID() + " | " + book.getTitle() + " | " + book.getAuthor() + " | " + book.getGenre() + " | "
                            + book.getTotalCopies() + " | " + book.getAvailableCopies() + "\n");
        } catch (IOException e) {
            System.out.println("Error saving single book: " + e.getMessage());
        }
    }

    public static void loadBooks() {
        try (BufferedReader reader = new BufferedReader(new FileReader("books.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 6) {
                    if (parts[0].trim().equalsIgnoreCase("ID")) {
                        continue;
                    }
                    try {
                        int id = Integer.parseInt(parts[0].trim());
                        String title = parts[1].trim();
                        String author = parts[2].trim();
                        String genre = parts[3].trim();
                        int totalCopies = Integer.parseInt(parts[4].trim());
                        int availableCopies = Integer.parseInt(parts[5].trim());
                        Book book = new Book(id, title, author, genre, totalCopies);
                        book.setAvailableCopies(availableCopies);
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing book data: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
    }

    public static void saveUsers(ArrayList<User> users) {
        try (FileWriter writer = new FileWriter("users.txt")) {
            for (User user : users) {
                writer.write(user.getID() + "," + user.getName() + "," + user.getUsername() + "," + user.getPassword()
                        + "," + user.getRole() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    public static void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    String username = parts[2].trim();
                    String password = parts[3].trim();
                    String role = parts[4].trim();
                    new User(id, name, username, password, role);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

    public static void saveIssueRecords(ArrayList<IssueRecord> issueRecords) {
        try (FileWriter writer = new FileWriter("issue.txt")) {
            for (IssueRecord issues : issueRecords) {
                writer.write(issues.getUserId() + "," + issues.getBookId() + "," + issues.getBookName() + ","
                        + issues.getIssueDate() + "," + issues.getDueDate() + "," + issues.getReturnDate() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    public static void loadIssueRecords() {
        try (BufferedReader reader = new BufferedReader(new FileReader("issue.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    int userId = Integer.parseInt(parts[0].trim());
                    int bookId = Integer.parseInt(parts[1].trim());
                    String bookName = "Unknown";
                    String issueDate = parts[2].trim();
                    String dueDate = parts[3].trim();
                    String returnDate = parts[4].trim();
                    IssueRecord record = new IssueRecord(userId, bookId, bookName, issueDate, dueDate, returnDate);
                    if (!returnDate.equals("Not Returned")) {
                        record.setReturned(true);
                    }
                } else if (parts.length >= 6) {
                    int userId = Integer.parseInt(parts[0].trim());
                    int bookId = Integer.parseInt(parts[1].trim());
                    String bookName = parts[2].trim();
                    String issueDate = parts[3].trim();
                    String dueDate = parts[4].trim();
                    String returnDate = parts[5].trim();
                    IssueRecord record = new IssueRecord(userId, bookId, bookName, issueDate, dueDate, returnDate);
                    if (!returnDate.equals("Not Returned")) {
                        record.setReturned(true);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading issued books: " + e.getMessage());
        }
    }
}
