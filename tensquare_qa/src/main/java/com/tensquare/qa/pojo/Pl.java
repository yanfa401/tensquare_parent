package com.tensquare.qa.pojo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

/**
 * 描述：问题标签中间表实体类
 *
 * @author xielei
 * @date 2019/07/19
 */

@Entity
@Table(name = "tb_pl")
@Data
@ToString
public class Pl implements Serializable {
    
    private static final long serialVersionUID = 522705053196864047L;
    
    @Id
    private String problemid;
    
    @Id
    private String lableid;
}
