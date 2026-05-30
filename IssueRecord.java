import java.util.ArrayList;

public class IssueRecord {
    private static ArrayList<IssueRecord> recordList = new ArrayList<>();
    private int userId;
    private int bookId;
    private String bookName;
    private String issueDate;
    private String dueDate;
    private String returnDate;
    private boolean isReturned;

    public IssueRecord(int userId, int bookId, String bookName, String issueDate, String dueDate, String returnDate) {

        this.userId = userId;
        this.bookId = bookId;
        this.bookName = bookName;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.isReturned = false;
        recordList.add(this);
    }

    public static ArrayList<IssueRecord> getRecordList() {
        return recordList;
    }

    public int getUserId() {
        return userId;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getReturnDate() {
        if (isReturned) {
            return returnDate;
        } else {
            return "Not Returned";
        }
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean isReturned) {
        this.isReturned = isReturned;
    }

    @Override
    public String toString() {
        return "User ID: " + userId + "\nBook ID: " + bookId + "\nIssue Date: " + issueDate + "\nDue Date: " + dueDate
                + "\nReturn Date: " + returnDate + "\nIs Returned: " + isReturned;
    }
}
