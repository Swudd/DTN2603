package Backend;

import Entity.Department;
import Entity.Position;
import Entity.PositionName;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QLDepartment implements IQLDepartment {
    @Override
    public void showDepartment() {
        String url = "jdbc:mysql://localhost:3306/testing_system";
        String username = "root";
        String password = "1234";
        Connection connection = null;
        List<Department> departments= new ArrayList<>();

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        String sql = "Select * from department";

        try {
            Statement statement =  connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("department_id");
                String name = resultSet.getString("department_name");
                Department department = new Department(id, name);
                departments.add(department);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("+-----+----------+");
        System.out.printf("|%5s|%10s|\n", "d_id", "d_name");
        System.out.println("+-----+----------+");
        for (Department d : departments) {
            System.out.printf("|%5s|%10s|\n", d.getDepartmentId(), d.getDepartmentName());
        }

        System.out.println("+-----+----------+");
    }

    public static void main(String[] args) {
        QLDepartment obj = new QLDepartment();
        obj.showDepartment();
    }
}
