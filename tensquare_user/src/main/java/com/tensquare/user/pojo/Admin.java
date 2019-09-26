package com.tensquare.user.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Tolerate;

/**
 * admin实体类
 *
 * @author xielei
 */
@Entity
@Table(name = "tb_admin")
@Data
@ToString
@Builder
public class Admin implements Serializable {
    
    @Tolerate
    public Admin() {
        super();
    }
    
    private static final long serialVersionUID = 9163874997454667235L;
    
    @Id
    private String id;//ID
    
    //登陆名称
    private String loginname;
    
    //密码
    private String password;
    
    //状态
    private String state;
    
    
}
