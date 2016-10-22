package model;

import dao.TaskDao;

import java.sql.SQLException;
import java.util.List;

public final class TaskManagerDao implements TaskManager {


    private final TaskDao taskDao;

    public TaskManagerDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public boolean add(Task task) {
        try {
            taskDao.add(task);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public List<Task> getAll() {
        try {
            return taskDao.getAll();
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Task get(int id) {
        try {
            return taskDao.get(id);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            return taskDao.delete(id);
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean makeTaskDone(int id) {
        Task task = null;
        try {
            task = taskDao.get(id);
        } catch (SQLException e) {
            return false;
        }
        if (task != null) {
            task.setDone(true);
            try {
                taskDao.update(task.getId(), task);
                return true;
            } catch (SQLException e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public List<Task> getAllDone() {
        try {
            return taskDao.getAllIsDone(true);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public List<Task> getAllUndone() {
        try {
            return taskDao.getAllIsDone(false);
        } catch (SQLException e) {
            return null;
        }
    }
}
