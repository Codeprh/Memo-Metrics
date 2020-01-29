package noah.memo.memotaskservice.service;

import noah.memo.memotaskapi.bean.TaskList;
import noah.memo.memotaskservice.repository.TaskListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述:
 * 任务列表业务
 *
 * @author Noah
 * @create 2020-01-28 19:26
 */
@Service
public class TaskListService {

    @Autowired
    TaskListRepository taskListRepository;

    /**
     * 根据用户id获取任务列表
     *
     * @return
     */
    public List<TaskList> getAccountTaskList(Integer accountId) {
        return taskListRepository.getAccountTaskList(accountId);
    }
}