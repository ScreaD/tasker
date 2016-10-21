package runner;

import controllers.Controller;
import dao.DaoFactoryImp;

/**
 * Created by scread on 21.10.16.
 */
public class Runner {

    public static void main(String[] args) {
        Controller controller = new Controller(new DaoFactoryImp());
        controller.mainMenu();
        controller.addTaskMenu();
        controller.TaskListMenu();
    }
}
