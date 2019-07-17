package com.tensquare.recruit.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Tolerate;

/**
 * 描述： 职位
 *
 * @author xielei
 * @date 2019/07/17
 */
@Entity
@Table(name = "tb_recruit")
@Data
@ToString
@Builder
public class Recruit implements Serializable {
    
    @Tolerate
    public Recruit() {}
    
    private static final long serialVersionUID = 7259030016455059891L;
    
    @Id
    private String id;//ID
    
    private String jobname;//职位名称
    private String salary;//薪资范围
    private String condition;//经验要求
    private String education;//学历要求
    private String type;//任职方式
    private String address;//办公地址
    private String eid;//企业ID
    private Date createtime;//创建日期
    private String state;//状态
    private String url;//网址
    private String label;//标签
    private String content1;//职位描述
    private String content2;//职位要求
}
