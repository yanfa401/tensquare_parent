package com.tensquare.qa.pojo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Tolerate;

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
@Builder
public class Pl implements Serializable {
    
    @Tolerate
    public Pl() {
        super();
    }
    
    private static final long serialVersionUID = 522705053196864047L;
    
    @Id
    private String problemid;
    
    @Id
    private String lableid;
}
