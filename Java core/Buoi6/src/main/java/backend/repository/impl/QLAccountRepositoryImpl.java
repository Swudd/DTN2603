package backend.repository.impl;

import backend.repository.IQLAccountRepository;
import entity.*;
import utils.JDBCUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QLAccountRepositoryImpl implements IQLAccountRepository {

    @Override
    public List<Account> GetAllAccounts() {

        List<Account> accounts= new ArrayList<>();
        String sql = "select a.account_id, a.email, a.username, a.full_name, a.department_id, d.department_name, a.position_id, p.position_name, a.create_date, a.gender from account a\n" +
                "left join department d on a.department_id = d.department_id\n" +
                "left join position p on a.position_id = p.position_id\n";

        try {
            Connection connection = JDBCUtils.getConnection();
            Statement statement =  connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int  id = resultSet.getInt("account_id");
                String email = resultSet.getString("email");
                String username1 = resultSet.getString("username");
                String fullname = resultSet.getString("full_name");
                int departmentId = resultSet.getInt("department_id");
                int positionId = resultSet.getInt("position_id");
                PositionName positionName = PositionName.valueOf(resultSet.getString("position_name"));
                String departmentName = resultSet.getString("department_name");
                LocalDate date = resultSet.getTimestamp("create_date")
                        .toLocalDateTime()
                        .toLocalDate();
                Gender gender = Gender.valueOf(resultSet.getString("gender"));
                Department department = new Department(departmentId, departmentName);
                Position position = new Position(positionId, positionName);
                Account a = new Account(id, email, username1, fullname, department, position, date, gender);
                accounts.add(a);
            }
            JDBCUtils.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }

    @Override
    public List<Account> FindAccountByName(String name) {
        List<Account> accounts= new ArrayList<>();
        String sql = "select a.account_id, a.email, a.username, a.full_name, a.department_id, d.department_name, a.position_id, p.position_name, a.create_date, a.gender from account a\n" +
                "left join department d on a.department_id = d.department_id\n" +
                "left join position p on a.position_id = p.position_id\n" +
                "where full_name like ?";

        try {
            Connection connection = JDBCUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,"%"+name+"%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int  id = resultSet.getInt("account_id");
                String email = resultSet.getString("email");
                String username1 = resultSet.getString("username");
                String fullname = resultSet.getString("full_name");
                int departmentId = resultSet.getInt("department_id");
                int positionId = resultSet.getInt("position_id");
                PositionName positionName = PositionName.valueOf(resultSet.getString("position_name"));
                String departmentName = resultSet.getString("department_name");
                LocalDate date = resultSet.getTimestamp("create_date")
                        .toLocalDateTime()
                        .toLocalDate();
                Gender gender = Gender.valueOf(resultSet.getString("gender"));
                Department department = new Department(departmentId, departmentName);
                Position position = new Position(positionId, positionName);
                Account a = new Account(id, email, username1, fullname, department, position, date, gender);
                accounts.add(a);
            }
            JDBCUtils.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }

    @Override
    public boolean AddAccount(Account account) {
        String sql = "INSERT INTO account (email, username, full_name, department_id, position_id, gender) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = JDBCUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, account.getEmail());
            statement.setString(2, account.getUsername());
            statement.setString(3, account.getFullname());
            statement.setString(4, String.valueOf(account.getDepartment().getDepartmentId()));
            statement.setInt(5, account.getPosition().getPositionId());
            statement.setString(6, account.getGender().name());

            int c =  statement.executeUpdate();
            JDBCUtils.closeConnection(connection);
            return c>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean DeleteAccount(int accountId) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "DELETE FROM account WHERE account_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId);
            int c = preparedStatement.executeUpdate();
            JDBCUtils.closeConnection(connection);
            return c>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean UpdateAccount(String fullName, int accountId) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "UPDATE `account` SET username = ? where account_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, fullName);
            preparedStatement.setInt(2, accountId);
            int c = preparedStatement.executeUpdate();
            JDBCUtils.closeConnection(connection);
            return c>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean CheckUsernameExist(String username) {
        Connection connection = null;
        try{
            connection = JDBCUtils.getConnection();

            String sql = "select * from `account` where username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return false;
    }

    @Override
    public boolean CheckEmailExist(String email) {
        Connection connection = null;
        try{
            connection = JDBCUtils.getConnection();

            String sql = "select * from `account` where email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,email);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return false;
    }

    @Override
    public boolean CheckAccountIdExist(int accountId) {
        Connection connection = null;
        try{
            connection = JDBCUtils.getConnection();

            String sql = "select * from `account` where account_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,accountId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return false;
    }
}
