package controllers;

import dao.DaoFactory;
import dao.DoneTaskDao;
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
    private DoneTaskDao doneTaskDao;
    private View view;
    private int level;

    public Controller(DaoFactory daoFactory, View view) {
        this.daoFactory = daoFactory;
        this.view = view;
        connection = null;
        try {
            connection = daoFactory.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        taskDao = daoFactory.getTaskDao(connection);
        doneTaskDao = daoFactory.getDoneTaskDao(connection);
        level = 1;
    }

    private String mainMenu() {
        String mainMenu = "\n1) Добавить задачу;\n" +
                "2) Вывести список задач;\n" +
                "3) Выйти.";
        return mainMenu;
    }

    private String addTaskMenu() {
        String addMenu = "\n1) Название задачи: \n" +
                "2) Когда должна быть выполнена;\n" +
                "3) Приоритет.";
        return addMenu;
    }

    private String taskListMenu() {
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
        return menu.toString();
    }

    private String doneListTasksMenu() {
        StringBuilder menu = new StringBuilder()
                .append("\n\nСписок завершенных заданий:");

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
        return menu.toString();
    }

    private void printCurrentMenu() {
        String menuToMeShowed;

        switch (level) {
            case 1:
                menuToMeShowed = mainMenu();
                break;
            case 2:
                menuToMeShowed = addTaskMenu();
                break;
            case 3:
                menuToMeShowed = taskListMenu();
                break;
            case 4:
                menuToMeShowed = doneListTasksMenu();
                break;
            default:
                menuToMeShowed = mainMenu();
        }

        view.showMenu(menuToMeShowed);
    }

    public void run() {
        do {
            printCurrentMenu();
        } while (!view.prompt().equals("q"));
    }


}
