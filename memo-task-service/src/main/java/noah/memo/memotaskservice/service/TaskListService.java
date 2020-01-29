package noah.memo.memotaskservice.service;

import noah.memo.memoframework.annotation.CatAnnotation;
import noah.memo.memotaskapi.bean.TaskList;
import noah.memo.memotaskservice.feignclient.AuthorityApiFeignClient;
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

    @Autowired
    AuthorityApiFeignClient authorityApiFeignClient;

    /**
     * 根据用户id获取任务列表
     *
     * @return
     */
    public List<TaskList> getAccountTaskList(Integer accountId) {
        //测试用例
        System.out.println("当前用户信息" + authorityApiFeignClient.getCurrentAccount(accountId));
        sleep();
        return taskListRepository.getAccountTaskList(accountId);
    }

    @CatAnnotation
    public void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
