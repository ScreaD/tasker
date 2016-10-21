import dao.DaoFactory;
import dao.DaoFactoryImp;
import dao.TaskDao;
import model.Task;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class TaskDaoTest {

    @Test
    public void shouldBeAddedTask_whenAddNewTask() {
        TaskDao mock = Mockito.mock(TaskDao.class);
        int id = new Random().nextInt();
        Task task = new Task();

        Mockito.when(mock.get(Mockito.anyInt())).thenReturn(task);

        assertEquals(task, mock.get(id));
    }

    @Test
    public void testDbConnection() throws SQLException {
        DaoFactory daoFactory = new DaoFactoryImp();
        Connection connection = daoFactory.getConnection();
        TaskDao taskDao = daoFactory.getTaskDao(connection);
        int expectedSize = 7;

        List<Task> taskList = taskDao.getAll();
        assertEquals(expectedSize, taskList.size());
    }

}
