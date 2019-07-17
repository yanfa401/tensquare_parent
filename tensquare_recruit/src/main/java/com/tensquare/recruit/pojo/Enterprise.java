package com.tensquare.recruit.pojo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Tolerate;

/**
 * 描述：企业
 *
 * @author xielei
 * @date 2019/07/17
 */
@Entity
@Table(name = "tb_enterprise")
@Data
@ToString
@Builder
public class Enterprise implements Serializable {
    
    @Tolerate
    public Enterprise() {
        super();
    }
    
    private static final long serialVersionUID = 9055302555376344760L;
    
    @Id
    private String id;//ID
    
    private String name;//企业名称
    private String summary;//企业简介
    private String address;//企业地址
    private String labels;//标签列表
    private String coordinate;//坐标
    private String ishot;//是否热门
    private String logo;//LOGO
    private Integer jobcount;//职位数
    private String url;//URL
    
}
