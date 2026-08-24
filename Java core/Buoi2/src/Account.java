import java.util.Date;

public class Account {
    int accountId;
    String email;
    String username;
    String fullName;
    Department department;
    Position position;
    Date createDate;

    public Account(int accountId, String email, String username, String fullName, Position position, Date createDate, Department department) {
        this.accountId = accountId;
        this.email = email;
        this.username = username;
        this.fullName = fullName;
        this.position = position;
        this.createDate = createDate;
        this.department = department;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public Department getDepartment() {
        return department;
    }

    public Position getPosition() {
        return position;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void showInfo()
    {
        System.out.println("Account ID: " + accountId);
        System.out.println("Email: " + email);
        System.out.println("Username: " + username);
        System.out.println("Full Name: " + fullName);
        System.out.println("Position: " + position.positionName);
        System.out.println("Create Date: " + createDate);
        System.out.println("Department: " + department.departmentName);
    }
}
