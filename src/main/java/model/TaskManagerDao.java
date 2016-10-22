package model;

import dao.TaskDao;

import java.sql.SQLException;
import java.util.List;

final public class TaskManagerDao implements TaskManager {


    private final TaskDao taskDao;

    public TaskManagerDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public void add(Task task) {
        try {
            taskDao.add(task);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Task> getAll() {
        try {
            return taskDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Task get(int id) {
        try {
            return taskDao.get(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            return taskDao.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean makeTaskDone(int id) {
        return false;
    }

    @Override
    public List<Task> getAllDone() {
        return null;
    }

    @Override
    public List<Task> getAllUndone() {
        return null;
    }
}
