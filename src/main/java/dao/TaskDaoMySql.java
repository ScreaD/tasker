package dao;

import model.Priority;
import model.Task;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public final class TaskDaoMySql implements TaskDao {

    private Connection connection;

    public TaskDaoMySql(String user, String password, String url, Driver driver) {
        try {
            DriverManager.registerDriver(driver);
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(Task task) throws SQLException {
        String sql = "INSERT INTO tasks (name, estimation_time, priority) VALUES (?, ?, ?);";

        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, task.getName());
        stm.setDate(2, task.getDate());
        stm.setString(3, task.getPriority().toString());

        stm.executeUpdate();
    }

    public List<Task> getAll() throws SQLException {
        String sql = "SELECT * FROM tasks;";
        PreparedStatement stm = connection.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();

        return getTasksFromResultSet(rs);
    }

    public Task get(int id) throws SQLException {
        String sql = "SELECT * FROM tasks WHERE id = (?);";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();

        if (rs.next()) {
            Priority priority = Priority.valueOf(rs.getString("priority"));
            return new Task(id, rs.getString("name"), rs.getDate("estimation_time"), priority);
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

    @Override
    public void update(int id, Task toTask) throws SQLException {
        String sql = "UPDATE tasks SET name = ?, estimation_time = ?, priority = ?, done = ? WHERE id = ?;";
        PreparedStatement stm = connection.prepareStatement(sql);

        stm.setString(1, toTask.getName());
        stm.setDate(2, toTask.getDate());
        stm.setString(3, toTask.getPriority().toString());
        stm.setBoolean(4, toTask.isDone());
        stm.setInt(5, id);

        stm.executeUpdate();
    }

    @Override
    public List<Task> getAllIsDone(boolean done) throws SQLException {
        String sql = "SELECT * FROM tasks WHERE done = ?;";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setBoolean(1, done);
        ResultSet rs = stm.executeQuery();

        return getTasksFromResultSet(rs);
    }

    private List<Task> getTasksFromResultSet(ResultSet rs) throws SQLException {
        List<Task> result = new LinkedList<>();

        while (rs.next()) {
            Task task = new Task();
            task.setId(rs.getInt("id"));
            task.setName(rs.getString("name"));
            task.setDate(rs.getDate("estimation_time"));
            task.setPriority(Priority.valueOf(rs.getString("priority")));
            task.setDone(rs.getBoolean("done"));
            result.add(task);
        }

        return result;
    }
}
