package com.tensquare.article.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * column实体类
 *
 * @author xielei
 */
@Entity
@Table(name = "tb_column")
@Data
@ToString
public class Column implements Serializable {
    
    @Id
    private String id;//ID
    
    private String name;//专栏名称
    private String summary;//专栏简介
    private String userid;//用户ID
    private java.util.Date createtime;//申请日期
    private java.util.Date checktime;//审核日期
    private String state;//状态
    
}
