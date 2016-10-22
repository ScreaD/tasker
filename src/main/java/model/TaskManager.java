package model;

import java.util.List;

public interface TaskManager {

    boolean add(Task task);

    List<Task> getAll();

    Task get(int id);

    boolean delete(int id);

    boolean makeTaskDone(int id);

    List<Task> getAllDone();

    List<Task> getAllUndone();

}
