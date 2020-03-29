package noah.memo.taskapi.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 描述:
 * 任务列表实体类
 *
 * @author Noah
 * @create 2020-01-28 18:03
 */
@Entity
@Table(name = "task_list")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskList extends AbstractPersistable<Long> {

    private static final long serialVersionUID = 4539120952423798777L;

    @Column(name = "name")
    private String name;

    @Column(name = "pid")
    private String pid;

    @Column(name = "status")
    private Integer status;

    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "describe")
    private String describe;

    @Column(name = "sequence")
    private Integer sequence;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "update_time")
    private Timestamp updateTime;

}
