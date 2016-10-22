package controllers;


import model.TaskManager;
import views.View;

public final class ConsoleController extends Controller {

    public ConsoleController(TaskManager taskManager, View view) {
        this.view = view;
        this.taskManager = taskManager;
        level = 1;
    }

    protected String parseResponse(String userResponse) {
        return userResponse;
    }

    public void run() {
        String userResponse;
        do {
            printCurrentMenu();

            userResponse = view.prompt();
            userInputResolver(userResponse);
        } while (level != 0);
    }
}
