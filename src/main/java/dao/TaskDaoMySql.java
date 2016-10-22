package dao;

import model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDaoMySql implements TaskDao {

    private Connection connection;

    // is it necessary to store this data, if need only connection
//    private final String user;
//    private final String password;
//    private final String url;
//    private final Driver driver;

    public TaskDaoMySql(String user, String password, String url, Driver driver) {
        try {
            DriverManager.registerDriver(driver);
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        this.user = user;
//        this.password = password;
//        this.url = url;
//        this.driver = driver;
    }

    public void add(Task task) throws SQLException {
        String sql = "INSERT INTO tasks (name, estimation_time, priority) VALUES (?, ?, ?);";

        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, task.getName());
        stm.setDate(2, task.getDate());
        stm.setString(3, task.getPriority());

        stm.executeUpdate();
    }

    public List<Task> getAll() throws SQLException {
        String sql = "SELECT * FROM tasks;";
        PreparedStatement stm = connection.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        List<Task> result = new ArrayList<>();

        while (rs.next()) {
            Task task = new Task();
            task.setId(rs.getInt("id"));
            task.setName(rs.getString("name"));
            task.setDate(rs.getDate("estimation_time"));
            task.setPriority(rs.getString("priority"));
            result.add(task);
        }

        return result;
    }

    public Task get(int id) throws SQLException {
        String sql = "SELECT * FROM tasks WHERE id = (?);";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();

        if (rs.next()) {
            return new Task(id, rs.getString("name"), rs.getDate("estimation_time"), rs.getString("priority"));
        }
        return null;
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM tasks WHERE id = " + id + ";";
        PreparedStatement stm = connection.prepareStatement(sql);
        int res = stm.executeUpdate();

        if (res > 0) {
            return true;
        }
        return false;
    }
}
