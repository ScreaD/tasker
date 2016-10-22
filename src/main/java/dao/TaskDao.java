package dao;

import model.Task;

import java.sql.SQLException;
import java.util.List;

public interface TaskDao {

    void add(Task task) throws SQLException;

    List<Task> getAll() throws SQLException;

    Task get(int id) throws SQLException;

    boolean delete(int id) throws SQLException;

    void update(int id, Task to) throws SQLException;

    List<Task> getAllIsDone(boolean done) throws SQLException;
}
