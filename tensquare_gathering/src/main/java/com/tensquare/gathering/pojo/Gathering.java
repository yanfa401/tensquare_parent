package com.tensquare.gathering.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Tolerate;

/**
 * gathering实体类
 *
 * @author Administrator
 */

@Data
@ToString
@Builder
@Entity
@Table(name = "tb_gathering")
public class Gathering implements Serializable {
    
    private static final long serialVersionUID = -6677308690237377063L;
    
    @Tolerate
    public Gathering() {
        super();
    }
    
    @Id
    private String id;//编号
    
    
    private String name;//活动名称
    private String summary;//大会简介
    private String detail;//详细说明
    private String sponsor;//主办方
    private String image;//活动图片
    private java.util.Date starttime;//开始时间
    private java.util.Date endtime;//截止时间
    private String address;//举办地点
    private java.util.Date enrolltime;//报名截止
    private String state;//是否可见
    private String city;//城市
    
}
