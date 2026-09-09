package Backend;

import Entity.Account;
import Entity.Gender;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public static void main(String[] args) {
        QLAccount obj = new QLAccount();
        obj.showAccount();
    }
}
