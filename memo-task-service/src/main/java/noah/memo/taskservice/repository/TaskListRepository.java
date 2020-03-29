package noah.memo.taskservice.repository;

import noah.memo.taskapi.bean.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 * 任务列表持久层
 *
 * @author Noah
 * @create 2020-01-28 19:35
 */
@Repository
public interface TaskListRepository extends JpaRepository<TaskList, Long> {

    /**
     * 根据账号id，查找未删除的父任务列表
     *
     * @param accountId
     * @return
     */
    @Query(value = "SELECT t.* FROM task_list AS t WHERE t.account_id=1 AND t.is_deleted=0 AND t.pid=0 ORDER BY sequence ASC;", nativeQuery = true)
    List<TaskList> getAccountTaskList(@Param(value = "accountId") int accountId);
}
