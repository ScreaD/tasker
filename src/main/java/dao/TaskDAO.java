package dao;

import model.Task;

import java.util.List;

public interface TaskDAO {

    void add(Task task);

    List<Task> getAll();

    Task get(int id);

    boolean delete(int id);
}
