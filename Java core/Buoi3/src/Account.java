import java.util.Date;

public class Account {
    int accountId;
    String email;
    String username;
    String fullName;
    Department department;
    Position position;
    Date createDate;
    float salary;

    public Account(int accountId, String email, String username, String fullName, Department department, Position position, Date createDate, float salary) {
        this.accountId = accountId;
        this.email = email;
        this.username = username;
        this.fullName = fullName;
        this.department = department;
        this.position = position;
        this.createDate = createDate;
        this.salary = salary;
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
