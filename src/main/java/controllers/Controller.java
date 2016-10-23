package controllers;

import menu.Menu;
import model.Priority;
import model.Task;
import model.TaskManager;
import views.View;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static menu.Menu.*;

public abstract class Controller {

    protected View view;
    protected int level;
    protected TaskManager taskManager;

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

    private String mainMenu() {
        return MAIN_MENU;
    }

    private String addTaskMenu() {
        return ADD_MENU;
    }

    private String taskListMenu() {
        StringBuilder menu = new StringBuilder().append(Menu.TASK_LIST_HEADER);

        List<Task> tasks = taskManager.getAllUndone();

        for (Task task : tasks) {
            menu.append(TASK_ID + task.getId())
                    .append(TASK_NAME + task.getName())
                    .append(TASK_DATE + task.getDate())
                    .append(TASK_PRIORITY + task.getPriority());

            if (new Date(new java.util.Date().getTime()).getTime() > task.getDate().getTime()) {
                menu.append(TASK_OVERDUE);
            }

            menu.append(HORIZONTAL_LINE);
        }

        menu.append(TASK_LIST_MENU);

        return menu.toString();
    }

    private String doneListTasksMenu() {
        StringBuilder menu = new StringBuilder().append(LIST_COMPLETED_HEADER);


        List<Task> tasks = taskManager.getAllDone();

        for (Task task : tasks) {
            menu.append(TASK_ID + task.getId())
                    .append(TASK_NAME + task.getName())
                    .append(TASK_DATE + task.getDate())
                    .append(TASK_PRIORITY + task.getPriority())
                    .append(HORIZONTAL_LINE);
        }

        menu.append(LIST_COMPLETED_TASK_MENU);

        return menu.toString();
    }

    private String closeTaskMenu() {
        return CLOSE_TASK_MENU;
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

        view.showMessage(COMPLETED_TASK + id);
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
                return;
            }

            Priority priority;
            try {
                priority = Priority.valueOf(params[2].toUpperCase());
            } catch (IllegalArgumentException e) {
                incorrectInput();
                return;
            }
            task.setPriority(priority);

            if (taskManager.add(task)) {
                view.showMessage(SUCCESSFUL_ADDED_TASK);
                level = 1;
            } else {
                incorrectInput();
            }
        } else {
            incorrectInput();
        }
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
                break;
        }
    }

    private void incorrectInput() {
        view.showMessage(INCORRECT_INPUT);
    }

    private Date parseDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = format.parse(date);
        return new Date(parsed.getTime());
    }

    /**
     * This method is need for parsing user response.
     * App supports only certain data type.
     * Note. User response is depended of the type of app you will create.
     * Example: web app will produce XML (HTML) or json.
     * @param userResponse
     * @return parsed string, that contains only data
     */
    protected abstract String parseResponse(String userResponse);

    /**
     * Creating main loop of the app, where you should accept new users,
     * show menu and handle user response
     */
    public abstract void run();

}
