package noah.memo.authorityapi.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 描述:
 * 用户实体类
 *
 * @author Noah
 * @create 2020-01-28 11:19
 */
@Entity
@Table(name = "account")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account implements Serializable {

    private static final long serialVersionUID = -26247508816203883L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "login_name")
    private String loginName;

    @Column(name = "login_password")
    private String loginPassword;

    @Column(name = "email")
    private String email;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "update_time")
    private Timestamp updateTime;


}
