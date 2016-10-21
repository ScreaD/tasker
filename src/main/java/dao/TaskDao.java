package dao;

import model.Task;

import java.sql.SQLException;
import java.util.List;

public interface TaskDao {

    void add(Task task);

    List<Task> getAll() throws SQLException;

    Task get(int id);

    boolean delete(int id);
}
