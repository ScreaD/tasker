package runner;

import com.mysql.jdbc.Driver;
import controllers.ConsoleController;
import controllers.Controller;
import dao.TaskDao;
import dao.TaskDaoMySql;
import model.TaskManager;
import model.TaskManagerDefault;
import views.ConsoleView;
import views.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public final class Runner {

    public static void main(String[] args) throws SQLException {
        // load properties
        Properties properties = loadProperties("database.properties");
        final String user = properties.getProperty("user");
        final String password = properties.getProperty("password");
        final String url = properties.getProperty("url");
        final Driver driver = new com.mysql.jdbc.Driver();
        // create model
        TaskDao taskDao = new TaskDaoMySql(user, password, url, driver);
        TaskManager taskManager = new TaskManagerDefault(taskDao);
        // create view
        View view = new ConsoleView();
        // create controller
        Controller controller = new ConsoleController(taskManager, view);
        // run program
        controller.run();
    }

    public static Properties loadProperties(String fileName) {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream(new File(fileName));

            // load a properties file
            prop.load(input);

            return prop;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
