import dao.TaskDAO;
import model.Task;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class TaskDAOTest {

    @Test
    public void shouldBeAddedTask_whenAddNewTask() {
        TaskDAO mock = Mockito.mock(TaskDAO.class);
        int id = new Random().nextInt();
        Task task = new Task(id);

        Mockito.when(mock.get(Mockito.anyInt())).thenReturn(task);

        assertEquals(task, mock.get(id));
    }

}
