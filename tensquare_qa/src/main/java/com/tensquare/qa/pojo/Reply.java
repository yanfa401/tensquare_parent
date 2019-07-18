package com.tensquare.qa.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

import lombok.Data;

/**
 * reply实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="tb_reply")
@Data
public class Reply implements Serializable{

    @Id
    private String id;//编号
    
    private String problemid;//问题ID
    private String content;//回答内容
    private java.util.Date createtime;//创建日期
    private java.util.Date updatetime;//更新日期
    private String userid;//回答人ID
    private String nickname;//回答人昵称

}
