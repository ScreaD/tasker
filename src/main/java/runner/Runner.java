package runner;

import com.mysql.jdbc.Driver;
import controllers.ConsoleController;
import controllers.Controller;
import dao.TaskDao;
import dao.TaskDaoMySql;
import model.TaskManager;
import model.TaskManagerDao;
import views.ConsoleView;
import views.View;

import java.sql.SQLException;

public final class Runner {

    public static void main(String[] args) throws SQLException {
        // load properties
        final String user = "root";
        final String password = "1234";
        final String url = "jdbc:mysql://localhost:3306/tasker?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        final Driver driver = new com.mysql.jdbc.Driver();
        // create model
        TaskDao taskDao = new TaskDaoMySql(user, password, url, driver);
        TaskManager taskManager = new TaskManagerDao(taskDao);
        // create view
        View view = new ConsoleView();
        // create controller
        Controller controller = new ConsoleController(taskManager, view);
        // run program
        controller.run();
    }
}
