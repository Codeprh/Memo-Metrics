package noah.memo.memotaskservice.controller;

import noah.memo.memoframework.bean.response.RspData;
import noah.memo.memotaskservice.service.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * 任务列表控制器
 *
 * @author Noah
 * @create 2020-01-28 18:49
 */
@RestController
public class TaskListController {

    @Autowired
    TaskListService taskListService;

    /**
     * 根据用户id查找所有taskList
     *
     * @return
     */

    public RspData getAccountTaskList() {
        //taskListService.getAccountTaskList()
        return null;
    }

}
