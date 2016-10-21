package runner;

import controllers.Controller;
import dao.DaoFactoryImp;

public class Runner {

    public static void main(String[] args) {
        Controller controller = new Controller(new DaoFactoryImp());
        controller.run();
    }
}
