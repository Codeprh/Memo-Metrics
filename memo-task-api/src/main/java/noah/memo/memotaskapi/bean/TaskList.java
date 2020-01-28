package noah.memo.memotaskapi.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
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
public class TaskList implements Serializable {

    private static final long serialVersionUID = 4539120952423798777L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "pid")
    private String pid;

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
