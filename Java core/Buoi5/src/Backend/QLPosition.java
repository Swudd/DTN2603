package Backend;

import Entity.Account;
import Entity.Gender;
import Entity.Position;
import Entity.PositionName;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLPosition implements IQLPosition {
    @Override
    public void showPosition() {
        String url = "jdbc:mysql://localhost:3306/testing_system";
        String username = "root";
        String password = "1234";
        Connection connection = null;
        List<Position> positions= new ArrayList<>();

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        String sql = "Select * from position";

        try {
            Statement statement =  connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("position_id");
                PositionName positionName = PositionName.valueOf(resultSet.getString("position_name"));
                Position position = new Position(id, positionName);
                positions.add(position);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("+-----+----------+");
        System.out.printf("|%5s|%10s|\n", "p_id", "p_name");
        System.out.println("+-----+----------+");
        for (Position p : positions) {
            System.out.printf("|%5s|%10s|\n", p.getPositionId(), p.getPositionName());
        }

        System.out.println("+-----+----------+");
    }

    @Override
    public void addPosition() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose position:  1. DEV   2. TEST   3. SCRUM_MASTER   other: PM");
        int choice = sc.nextInt();
        sc.nextLine();
        String positionName;
        switch (choice) {
            case 1:
                positionName = "DEV";
                break;
            case 2:
                positionName = "TEST";
                break;
            case 3:
                positionName = "SCRUM_MASTER";
                break;
            default:
                positionName = "PM";
        }

        String url = "jdbc:mysql://localhost:3306/testing_system";
        String dbusername = "root";
        String password = "1234";

        try{
            Connection connection = DriverManager.getConnection(url, dbusername, password);
            String sql = "INSERT INTO position (position_name) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,positionName);

            int c = preparedStatement.executeUpdate();
            if (c > 0) {
                System.out.println("Position added successfully!");
            } else  {
                System.out.println("Position not added!");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deletePositionById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Position's id: ");
        int positionId = sc.nextInt();
        sc.nextLine();

        String url = "jdbc:mysql://localhost:3306/testing_system";
        String dbusername = "root";
        String password = "1234";

        try {
            Connection connection = DriverManager.getConnection(url, dbusername, password);
            String sql = "DELETE FROM position WHERE position_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, positionId);
            int c = preparedStatement.executeUpdate();
            if (c > 0) {
                System.out.println("Position has been deleted successfully");
            } else   {
                System.out.println("Failed to delete position!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePositionById() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter position's id: ");
        int positionId = sc.nextInt();
        sc.nextLine();
        System.out.println("Choose position:  1. DEV   2. TEST   3. SCRUM_MASTER   other: PM");
        int choice = sc.nextInt();
        sc.nextLine();
        String positionName;
        switch (choice) {
            case 1:
                positionName = "DEV";
                break;
            case 2:
                positionName = "TEST";
                break;
            case 3:
                positionName = "SCRUM_MASTER";
                break;
            default:
                positionName = "PM";
        }

        String url = "jdbc:mysql://localhost:3306/testing_system";
        String dbusername = "root";
        String password = "1234";

        try {
            Connection connection = DriverManager.getConnection(url, dbusername, password);
            String sql = "UPDATE position SET position_name = ? where position_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, positionName);
            preparedStatement.setInt(2, positionId);
            int c = preparedStatement.executeUpdate();
            if (c > 0) {
                System.out.println("Position has been updated successfully");
            } else   {
                System.out.println("Position has not been updated successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void managePosition() {
        Scanner sc = new Scanner(System.in);
        QLPosition qlPosition = new QLPosition();

        while (true) {
            System.out.println("1. Show positions");
            System.out.println("2. Add position");
            System.out.println("3. Update position");
            System.out.println("4. Delete position");
            System.out.println("other: Back");
            System.out.print("Nhap lua chon: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    qlPosition.showPosition();
                    break;
                case 2:
                    qlPosition.addPosition();
                    break;
                case 3:
                    qlPosition.updatePositionById();
                    break;
                case 4:
                    qlPosition.deletePositionById();
                    break;
                default:
                    return;
            }
        }
    }
}
