package Backend;

import Entity.Account;
import Entity.Gender;
import Entity.Position;
import Entity.PositionName;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public static void main(String[] args) {
        QLPosition obj = new QLPosition();
        obj.showPosition();
    }
}
