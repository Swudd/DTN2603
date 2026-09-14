package Backend;

import Entity.Account;
import Entity.Gender;
import utils.JDBCUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLAccount implements IQLAccount {
    @Override
    public void showAccount() {

        String url = "jdbc:mysql://localhost:3306/testing_system";
        String username = "root";
        String password = "1234";
        Connection connection = null;
        List<Account> accounts= new ArrayList<>();

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        String sql = "Select * from account";

        try {
            Statement statement =  connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int  id = resultSet.getInt("account_id");
                String email = resultSet.getString("email");
                String username1 = resultSet.getString("username");
                String fullname = resultSet.getString("full_name");
                int departmentId = resultSet.getInt("department_id");
                int positionId = resultSet.getInt("position_id");
                LocalDate date = resultSet.getTimestamp("create_date")
                        .toLocalDateTime()
                        .toLocalDate();
                Gender  gender = Gender.valueOf(resultSet.getString("gender"));
                Account a = new Account(id, email, username1, fullname, departmentId, positionId, date, gender);
                accounts.add(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("+-----+-------------------------+--------------------+--------------------+-----+-----+----------+-----+");
        System.out.printf("|%5s|%25s|%20s|%20s|%5s|%5s|%10s|%5s|\n", "id", "email", "username", "full_name", "d_id", "p_id", "cdate", "gender");
        System.out.println("+-----+-------------------------+--------------------+--------------------+-----+-----+----------+-----+");
        for (Account a : accounts) {
            System.out.printf("|%5s|%25s|%20s|%20s|%5s|%5s|%10s|%5s|\n"
                    , a.getAcountId(), a.getEmail(), a.getUsername(), a.getFullname()
                    , a.getDepartmentId(), a.getPositionId(), a.getCreateDate(), a.getGender());
        }

        System.out.println("+-----+-------------------------+--------------------+--------------------+-----+-----+----------+-----+");
    }

    @Override
    public void addAccount() {
        Scanner sc = new Scanner(System.in);

        LocalDate date = LocalDate.now();
        Gender gender;

        System.out.println("Enter email: ");
        String email = sc.nextLine();
        System.out.println("Enter username: ");
        String username = sc.nextLine();
        System.out.println("Enter full name: ");
        String fullname = sc.nextLine();
        System.out.println("Enter department_id: ");
        int departmentId = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter position_id: ");
        int positionId = sc.nextInt();
        sc.nextLine();
        System.out.println("Pick gender: 1. Male    2. Female    other.Unknow");
        int pickGender = sc.nextInt();
        switch (pickGender) {
            case 1:
                gender = Gender.M;
                break;
            case 2:
                gender = Gender.F;
                break;
            default:
                gender = Gender.U;
                break;
        }

        String url = "jdbc:mysql://localhost:3306/testing_system";
        String dbusername = "root";
        String password = "1234";

        try{
            Connection connection = DriverManager.getConnection(url, dbusername, password);
            String sql = "INSERT INTO account (email, username, full_name, department_id, position_id, gender) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, fullname);
            preparedStatement.setInt(4, departmentId);
            preparedStatement.setInt(5, positionId);
            preparedStatement.setString(6, gender.name());

            int c = preparedStatement.executeUpdate();
            if (c > 0) {
                System.out.println(email + " has been added successfully");
            } else  {
                System.out.println(email + " has not been added successfully");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccountById() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter userID: ");
        int userID = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter username: ");
        String username = sc.nextLine();

        String url = "jdbc:mysql://localhost:3306/testing_system";
        String dbusername = "root";
        String password = "1234";

        try {
            Connection connection = DriverManager.getConnection(url, dbusername, password);
            String sql = "UPDATE `account` SET username = ? where account_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, userID);
            int c = preparedStatement.executeUpdate();
            if (c > 0) {
                System.out.println(username + " has been updated successfully");
            } else   {
                System.out.println(username + " has not been updated successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAccountById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter userID: ");
        int userID = sc.nextInt();
        sc.nextLine();

        JDBCUtils jdbcUtils = new JDBCUtils();

        try {
            Connection connection = jdbcUtils.getConnection();
            String sql = "DELETE FROM account WHERE account_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userID);
            int c = preparedStatement.executeUpdate();
            if (c > 0) {
                System.out.println("Account has been deleted successfully");
            } else   {
                System.out.println("Failed to delete account");
            }
            jdbcUtils.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void manageAccount() {
        Scanner sc = new Scanner(System.in);
        QLAccount qlAccount = new QLAccount();

        while (true) {
            System.out.println("1. Show account");
            System.out.println("2. Add account");
            System.out.println("3. Update account");
            System.out.println("4. Delete account");
            System.out.println("other: Back");
            System.out.print("Nhap lua chon: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    qlAccount.showAccount();
                    break;
                case 2:
                    qlAccount.addAccount();
                    break;
                case 3:
                    qlAccount.updateAccountById();
                    break;
                case 4:
                    qlAccount.deleteAccountById();
                    break;
                default:
                    return;
            }
        }
    }
}
