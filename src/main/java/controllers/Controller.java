package controllers;

import model.Task;
import model.TaskManager;
import views.View;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;

public abstract class Controller {

    protected View view;
    protected int level;
    protected TaskManager taskManager;

    private String mainMenu() {
        String mainMenu = "\n1) Добавить задачу;\n" +
                "2) Вывести список задач;\n" +
                "3) Выйти.";
        return mainMenu;
    }

    private String addTaskMenu() {
        String addMenu = "\nAdd task menu." +
                "Name;Date(YYYY-DD-MM format);Priority(low, medium, high)";
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

        List<Task> tasks = taskManager.getAllUndone();

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

        List<Task> tasks = taskManager.getAllDone();

        for (Task task : tasks) {
            menu.append("id: ")
                    .append("\n\nName: " + task.getName())
                    .append("\nEnd date: " + task.getDate())
                    .append("\nPriority: " + task.getPriority());
        }
        return menu.toString();
    }

    protected void printCurrentMenu() {
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
            case 5:
                menuToMeShowed = closeTaskMenu();
                break;
            default:
                menuToMeShowed = mainMenu();
                break;
        }

        view.showMenu(menuToMeShowed);
    }

    private String closeTaskMenu() {
        return "Enter task id: ";
    }

    protected void userInputResolver(String userResponse) {
        userResponse = parseResponse(userResponse);

        switch (level) {
            case 1:
                mainMenuResolver(userResponse);
                break;
            case 2:
                addTaskMenuResolver(userResponse);
                break;
            case 3:
                taskListMenuResolver(userResponse);
                break;
            case 4:
                doneListTasksMenuResolver(userResponse);
                break;
            case 5:
                closeTaskMenuResolver(userResponse);
                break;
        }
    }

    private void closeTaskMenuResolver(String userResponse) {
        userResponse = parseResponse(userResponse);
        Integer id = Integer.parseInt(userResponse);

        if (!taskManager.makeTaskDone(id)) {
            incorrectInput();
        }

        level = 3;
    }

    private void doneListTasksMenuResolver(String userResponse) {
        userResponse = parseResponse(userResponse);

        switch (userResponse) {
            case "1":
                level = 3;
                break;
            case "2":
                level = 1;
                break;
            default:
                incorrectInput();
                break;
        }
    }

    private void taskListMenuResolver(String userResponse) {
        userResponse = parseResponse(userResponse);

        switch (userResponse) {
            case "1":
                level = 5;
                break;
            case "2":
                level = 4;
                break;
            case "3":
                level = 1;
                break;
            default:
                incorrectInput();
                break;
        }
    }

    private void addTaskMenuResolver(String userResponse) {
        userResponse = parseResponse(userResponse);
        Task task = new Task();

        String[] params = userResponse.split(";");
        if (params.length == 3) {
            task.setName(params[0]);
            task.setDate(parseDate(params[1]));
            task.setPriority(params[2]);
            taskManager.add(task);
        } else {
            incorrectInput();
            level = 2;
        }

        level = 1;
    }

    private Date parseDate(String param) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = null;
        try {
            parsed = format.parse(param);
        } catch (ParseException e) {
            incorrectInput();
        }
        return new Date(parsed.getTime());
    }

    private void mainMenuResolver(String userResponse) {
        switch (userResponse) {
            case "1":
                level = 2;
                break;
            case "2":
                level = 3;
                break;
            case "3":
                level = 0;
                break;
            default:
                incorrectInput();
                level = 1;
                break;
        }
    }

    private void incorrectInput() {
        view.showMenu("Incorrect input");
    }

    protected abstract String parseResponse(String userResponse);

    public abstract void run();

}
