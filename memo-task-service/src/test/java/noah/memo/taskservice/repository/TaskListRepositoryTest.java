package noah.memo.taskservice.repository;

import noah.memo.taskapi.bean.TaskList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskListRepositoryTest {

    @Autowired
    TaskListRepository taskListRepository;

    @Test
    public void getAccountTaskList() {
        List<TaskList> accountTaskList = taskListRepository.getAccountTaskList(1);
        System.out.println(accountTaskList.toString());
    }

}