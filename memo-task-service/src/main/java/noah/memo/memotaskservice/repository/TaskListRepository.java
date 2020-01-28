package noah.memo.memotaskservice.repository;

import noah.memo.memotaskapi.bean.TaskList;
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
public interface TaskListRepository extends JpaRepository<TaskList, Integer> {

    @Query(value = "SELECT * FROM task_list tl where tl.pid is NULL  and account_id = :accountId ORDER BY sequence ASC", nativeQuery = true)
    List<TaskList> getAccountTaskList(@Param(value = "accountId") int accountId);
}
