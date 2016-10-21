package controllers;

import dao.DaoFactory;
import dao.TaskDao;
import model.Task;
import views.View;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private DaoFactory daoFactory;
    private Connection connection;
    private TaskDao taskDao;
    private View view;

    public Controller(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
        connection = null;
        try {
            connection = daoFactory.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        taskDao = daoFactory.getTaskDao(connection);
        view = new View();
    }

    public void mainMenu() {
        String mainMenu = "\n1) Добавить задачу;\n" +
                "2) Вывести список задач;\n" +
                "3) Выйти.";
        view.showMenu(mainMenu);
    }

    public void addTaskMenu() {
        String addMenu = "\n1) Название задачи: \n" +
                "2) Когда должна быть выполнена;\n" +
                "3) Приоритет.";
        view.showMenu(addMenu);
    }

    public void TaskListMenu() {
        StringBuilder menu = new StringBuilder()
                .append("\n1) Выводиться список задач, если задача просрочена она помечается как просроченная. \n")
                .append("2) На екране можно указать какая задача закончена, когда задача заканчивается она \n")
                .append("переноситься в другую таблицу и видна при выборе всех завершенных. \n")
                .append("3) На екране можно запросить вывод всех завершённых задач \n")
                .append("4) На екране можно вернуться в основе меню/екран")
                .append("\n\nСписок заданий:");

        List<Task> tasks = new ArrayList<>();
        try {
            tasks = taskDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Task task : tasks) {
            menu.append("\n\nName: " + task.getName())
                    .append("\nEnd date: " + task.getDate())
                    .append("\nPriority: " + task.getPriority());
        }
        view.showMenu(menu.toString());
    }

}
