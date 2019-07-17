package com.tensquare.base.pojo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * 描述：标签实体类
 *
 * @author xielei
 * @date 2019/07/17
 */

@Entity
@Table(name = "tb_label")
@Data
@Builder
public class Label implements Serializable {
    
    private static final long serialVersionUID = -3418022436712795069L;
    
    @Tolerate
    public Label() {}
    
    @Id
    private String id;
    
    private String labelname;
    
    private String state;
    
    private Long count;
    
    private String recommend;
    
    private Long fans;
}
