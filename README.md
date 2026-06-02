# 📚 Library Management System

A console-based **Library Management System** built in Java as an end-of-semester project (2nd Semester). The application provides role-based access for **Admins** and **Users** to manage books, issue/return operations, and user accounts — all persisted through flat-file storage.

---

## ✨ Features

### 🔐 Authentication & Registration
- Admin and User login with role-based access control
- New user registration with auto-generated unique IDs
- Password change functionality

### 🛡️ Admin Dashboard
| Feature | Description |
|---------|-------------|
| **Add Book** | Add new books with ID, title, author, genre, and copy count |
| **Remove Book** | Remove a book from the library by its ID |
| **Update Book** | Update the total number of copies (available copies adjust automatically) |
| **View All Books** | Display the full book catalog in a formatted table |
| **View All Users** | List all registered users |
| **View Issued Books** | See all issue records including due dates and return status |

### 👤 User Dashboard
| Feature | Description |
|---------|-------------|
| **Browse All Books** | View the entire book catalog with availability |
| **Search Book** | Search for a book by its title |
| **Issue a Book** | Borrow a book (14-day borrowing period) |
| **Return a Book** | Return a borrowed book and update records |
| **My Issued Books** | View your currently borrowed books with due dates |

---

## 🏗️ Project Structure

```
Library-Management-System/
│
|--- Main.java            # Entry point — handles login/register flow
|--- AuthService.java     # Authentication logic (login, register, password change)
|--- AdminService.java    # Admin dashboard and admin-specific operations
|--- UserService.java     # User dashboard and user-specific operations
|--- Book.java            # Book model — fields, getters/setters, borrow/return logic
|--- User.java            # User model — fields, getters/setters, issued book tracking
|--- IssueRecord.java     # Issue record model — tracks borrow/return transactions
|--- FileHandler.java     # File I/O — loads and saves data to/from .txt files
│
|--- books.txt            # Book inventory data
|--- users.txt            # User account data
|--- issue.txt            # Book issue/return records
```

---

## 🛠️ Tech Stack

- **Language:** Java (JDK 17+)
- **Data Storage:** Flat-file persistence (`.txt` files with delimited formats)
- **Architecture:** Service-oriented with separate model, service, and I/O layers
- **UI:** Console-based interface with formatted ASCII art and tabular output

---

## 🚀 Getting Started

### Prerequisites
- **Java JDK 17** or higher installed
- A terminal or IDE that supports Java compilation

### Run the Project

1. **Clone the repository**
   ```bash
   git clone https://github.com/Muhammad-Umer-Randhawa/Library-Management-System-Java.git
   cd Library-Management-System-Java
   ```

2. **Compile all Java files**
   ```bash
   javac *.java
   ```

3. **Run the application**
   ```bash
   java Main
   ```

### Default Admin Credentials
| Username | Password |
|----------|----------|
| `admin`  | `admin123` |

---
### 🎨 UI
The System features a simple console-based program consisting of a classic ASCII character display for the main **Library Management System** interface, along with the text based dashboard enabling user to login as admin, user, or registring a new user or to even exit the program.

---

## 📖 How It Works

### Data Flow
```
+----------+     +--------------+     +--------------+
│  Main    │---->│ AuthService  │---->│  FileHandler │
│ (Entry)  │     │ (Auth Logic) │     │  (File I/O)  │
+----------+     +--------------+     +--------------+
      │                                      │
      │                                      │
      V                                      V
+--------------+                    +------------------+
│ AdminService │                    │  books.txt       │
│ UserService  │<------------------>|  users.txt       │
│ (Dashboards) │                    │  issue.txt       │
+--------------+                    +------------------+
```

1. **Startup** — `Main.java` loads all data from `.txt` files into memory via `FileHandler`
2. **Authentication** — `AuthService` validates credentials against the loaded user list
3. **Operations** — Admin/User services perform CRUD operations on in-memory data
4. **Persistence** — Changes are written back to `.txt` files after every operation

### File Formats

**books.txt** — Pipe-delimited (`|`)
```
ID | Title | Author | Genre | Total Copies | Available Copies
101 | Java Programming | Jane Smith | Programming | 75 | 75
```

**users.txt** — Comma-delimited (`,`)
```
101,Umer,Mr_Kraven987,123456,User
```

**issue.txt** — Comma-delimited (`,`)
```
userId,bookId,bookName,issueDate,dueDate,returnDate
```

---

## 📸 Sample Output

```
=============================
1. Admin Login
2. User Login
3. Register (New User)
4. Exit
=============================

Enter your choice: 2
Enter credentials:
Enter username: Mr_Kraven987
Enter password: 123456
Login successful

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
```

---

## 📝 Key Concepts Demonstrated

- **Object-Oriented Programming** — Encapsulation, classes, static members, and ArrayList usage
- **Role-Based Access Control** — Separate admin and user workflows
- **File I/O** — Reading and writing structured data with `BufferedReader` / `FileWriter`
- **Java Collections** — `ArrayList` for in-memory data management
- **String Formatting** — `printf` for clean tabular console output
- **Java Text Blocks** — Multi-line strings for menus (Java 15+ feature)

---
