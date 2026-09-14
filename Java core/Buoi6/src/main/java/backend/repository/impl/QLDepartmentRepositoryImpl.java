package backend.repository.impl;

import backend.repository.IQLDepartmentRepository;
import entity.Department;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QLDepartmentRepositoryImpl implements IQLDepartmentRepository {
    @Override
    public List<Department> GetAllDepartments() {
        List<Department> departments = new ArrayList<>();
        String sql = "Select * from department";

        try {
            Connection connection = JDBCUtils.getConnection();
            Statement statement =  connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("department_id");
                String name = resultSet.getString("department_name");
                Department department = new Department(id, name);
                departments.add(department);
            }
            JDBCUtils.closeConnection(connection);
            return departments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
