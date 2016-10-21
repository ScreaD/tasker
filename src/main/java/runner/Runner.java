package runner;

import controllers.Controller;
import dao.DaoFactoryImp;
import views.ConsoleView;

public class Runner {

    public static void main(String[] args) {
        Controller controller = new Controller(new DaoFactoryImp(), new ConsoleView());
        controller.run();
    }
}
