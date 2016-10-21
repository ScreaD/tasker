package dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DaoFactory {

    Connection getConnection() throws SQLException;
    DoneTaskDao getDoneTaskDao(Connection connection);
    TaskDao getTaskDao(Connection connection);

}
