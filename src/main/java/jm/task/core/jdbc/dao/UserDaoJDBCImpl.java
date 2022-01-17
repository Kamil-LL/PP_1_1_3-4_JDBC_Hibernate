package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Statement stat = connection.createStatement()) {
            stat.executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(250), last_name VARCHAR(250), age INT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Statement stat = connection.createStatement()) {
            stat.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)")) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users")) {
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"), resultSet.getString("last_name"), resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}