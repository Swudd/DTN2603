package backend.repository.impl;

import backend.repository.IQLPositionRepository;
import entity.Position;
import entity.PositionName;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QLPositionReposirotyImpl implements IQLPositionRepository {
    @Override
    public List<Position> getAllPositions() {
        List<Position> positions = new ArrayList<>();
        String sql = "Select * from position";

        try {
            Connection connection = JDBCUtils.getConnection();
            Statement statement =  connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("position_id");
                PositionName positionName = PositionName.valueOf(resultSet.getString("position_name"));
                Position position = new Position(id, positionName);
                positions.add(position);
            }
            JDBCUtils.closeConnection(connection);
            return  positions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
