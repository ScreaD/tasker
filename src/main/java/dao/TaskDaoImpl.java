package dao;

import model.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDaoImpl implements TaskDao {

    private Connection connection;

    public TaskDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public void add(Task task) {

    }

    public List<Task> getAll() throws SQLException {
        String sql = "SELECT * FROM tasks;";
        PreparedStatement stm = connection.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        List<Task> result = new ArrayList<Task>();

        while (rs.next()) {
            Task t = new Task();
            t.setId(rs.getInt("id"));
            t.setName(rs.getString("name"));
            t.setDate(rs.getDate("estimation_time"));
            t.setPriority(rs.getString("priority"));
            result.add(t);
        }
        return result;
    }

    public Task get(int id) {
        return null;
    }

    public boolean delete(int id) {
        return false;
    }
}
