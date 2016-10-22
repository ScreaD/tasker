import dao.TaskDao;
import model.Task;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class TaskDaoTest {

    @Test
    public void shouldBeAddedTask_whenAddNewTask() throws SQLException {
        TaskDao mock = Mockito.mock(TaskDao.class);
        int id = new Random().nextInt();
        Task task = new Task();

        Mockito.when(mock.get(Mockito.anyInt())).thenReturn(task);

        assertEquals(task, mock.get(id));
    }

}
