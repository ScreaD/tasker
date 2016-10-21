import dao.DaoFactory;
import dao.DaoFactoryImp;
import dao.TaskDao;
import model.Task;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TaskDaoTest {

    @Test
    public void shouldBeAddedTask_whenAddNewTask() throws SQLException {
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

    @Test
    public void addTask() throws SQLException {
        DaoFactory daoFactory = new DaoFactoryImp();
        Connection connection = daoFactory.getConnection();
        TaskDao taskDao = daoFactory.getTaskDao(connection);
        Task expected = new Task(500, "task500", new Date(new java.util.Date().getTime()), "high");

        taskDao.add(expected);

        List<Task> taskList = taskDao.getAll();
        int id = 0;
        for(Task task: taskList) {
            if (task.getName().equals(expected.getName())) {
                id = task.getId();
                break;
            }
        }

        assertEquals(expected.getName(), taskDao.get(id).getName());

        assertTrue(taskDao.delete(id));
    }

}
