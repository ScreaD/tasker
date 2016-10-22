package controllers;

import model.Task;
import model.TaskManager;
import views.View;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public abstract class Controller {

    protected View view;
    protected int level;
    protected TaskManager taskManager;

    private String mainMenu() {
        String mainMenu = "Main menu" +
                "\n_________________________" +
                "\n1) Add task;\n" +
                "2) Show all tasks;\n" +
                "3) Exit.";
        return mainMenu;
    }

    private String addTaskMenu() {
        String addMenu = "\nAdd new task menu" +
                "\n_________________________" +
                "\nPlease enter information such as: " +
                "name, end date and priority " +
                "\n\nRequirements:" +
                "\nAll info should be split with \";\" symbol" +
                "\nInput date format: YYYY-MM-DD" +
                "\nAvailable priority: low, medium, high" +
                "\n\nExample: Buy tickets;2016-24-10;medium\n";
        return addMenu;
    }

    private String taskListMenu() {
        StringBuilder menu = new StringBuilder()
                .append("\nTask list:")
                .append("\n___________________");

        List<Task> tasks = taskManager.getAllUndone();

        for (Task task : tasks) {
            menu.append("\nId:  " + task.getId())
                    .append("\nName: " + task.getName())
                    .append("\nEnd date: " + task.getDate())
                    .append("\nPriority: " + task.getPriority());

            if (new Date(new java.util.Date().getTime()).getTime() > task.getDate().getTime()) {
                menu.append("\nTask is overdue!");
            }

            menu.append("\n___________________");
        }

        menu.append("\n1) Indicate completed task; \n")
                .append("2) Show all completed tasks; \n")
                .append("3) Back to main menu. \n");

        return menu.toString();
    }

    private String doneListTasksMenu() {
        StringBuilder menu = new StringBuilder()
                .append("\nList completed tasks:")
                .append("\n___________________");


        List<Task> tasks = taskManager.getAllDone();

        for (Task task : tasks) {
            menu.append("\nId: " + task.getId())
                    .append("\nName: " + task.getName())
                    .append("\nEnd date: " + task.getDate())
                    .append("\nPriority " + task.getPriority())
                    .append("\n___________________");
        }

        menu.append("\n1) Back to previous menu;")
                .append("\n2) Back to main menu.");

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

        view.showMessage(menuToMeShowed);
    }

    private String closeTaskMenu() {
        return "Enter task id, that you have completed: ";
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
                completeTaskMenuResolver(userResponse);
                break;
        }
    }

    private void completeTaskMenuResolver(String userResponse) {
        userResponse = parseResponse(userResponse);
        Integer id = Integer.parseInt(userResponse);

        if (!taskManager.makeTaskDone(id)) {
            incorrectInput();
        }

        view.showMessage("Task with id = " + id + " now completed!");
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
            try {
                task.setDate(parseDate(params[1]));
            } catch (ParseException e) {
                incorrectInput();
                level = 2;
                return;
            }
            task.setPriority(params[2]);

            taskManager.add(task);
            view.showMessage("Task were added successfully!\n");
            level = 1;
        } else {
            incorrectInput();
            level = 2;
        }
    }

    private Date parseDate(String param) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = format.parse(param);
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
        view.showMessage("Incorrect input. Try again");
    }

    protected abstract String parseResponse(String userResponse);

    public abstract void run();

}
