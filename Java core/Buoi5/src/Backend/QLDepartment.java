package Backend;

import Entity.Department;
import Entity.Gender;
import Entity.Position;
import Entity.PositionName;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    @Override
    public void addDepartment() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter department's name: ");
        String name = sc.nextLine();

        String url = "jdbc:mysql://localhost:3306/testing_system";
        String dbusername = "root";
        String password = "1234";

        try{
            Connection connection = DriverManager.getConnection(url, dbusername, password);
            String sql = "INSERT INTO department (department_name) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);

            int c = preparedStatement.executeUpdate();
            if (c > 0) {
                System.out.println("Department added successfully!");
            } else  {
                System.out.println("Department not added!");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateDepartmentById() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Department's id: ");
        int departmentId = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter department's name: ");
        String departmentName = sc.nextLine();

        String url = "jdbc:mysql://localhost:3306/testing_system";
        String dbusername = "root";
        String password = "1234";

        try {
            Connection connection = DriverManager.getConnection(url, dbusername, password);
            String sql = "UPDATE department SET department_name = ? where department_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, departmentName);
            preparedStatement.setInt(2, departmentId);
            int c = preparedStatement.executeUpdate();
            if (c > 0) {
                System.out.println(departmentName + " has been updated successfully");
            } else   {
                System.out.println(departmentName + " has not been updated successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDepartmentById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Department's id: ");
        int departmentId = sc.nextInt();
        sc.nextLine();

        String url = "jdbc:mysql://localhost:3306/testing_system";
        String dbusername = "root";
        String password = "1234";

        try {
            Connection connection = DriverManager.getConnection(url, dbusername, password);
            String sql = "DELETE FROM department WHERE department_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, departmentId);
            int c = preparedStatement.executeUpdate();
            if (c > 0) {
                System.out.println("Department has been deleted successfully");
            } else   {
                System.out.println("Failed to delete department!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void manageDepartment() {
        Scanner sc = new Scanner(System.in);
        QLDepartment qlDepartment = new QLDepartment();

        while (true) {
            System.out.println("1. Show departments");
            System.out.println("2. Add department");
            System.out.println("3. Update department");
            System.out.println("4. Delete department");
            System.out.println("other: Back");
            System.out.print("Nhap lua chon: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    qlDepartment.showDepartment();
                    break;
                case 2:
                    qlDepartment.addDepartment();
                    break;
                case 3:
                    qlDepartment.updateDepartmentById();
                    break;
                case 4:
                    qlDepartment.deleteDepartmentById();
                    break;
                default:
                    return;
            }
        }
    }
}
