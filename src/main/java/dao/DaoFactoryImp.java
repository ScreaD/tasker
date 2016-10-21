package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactoryImp implements DaoFactory {

    private String user = "root";
    private String password = "1234";
    private String url = "jdbc:mysql://localhost:3306/tasker?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String driver = "com.mysql.jdbc.Driver";

    public DaoFactoryImp() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public DoneTaskDao getDoneTaskDao(Connection connection) {
        return new DoneTaskDaoImpl(connection);
    }

    public TaskDao getTaskDao(Connection connection) {
        return new TaskDaoImpl(connection);
    }
}
