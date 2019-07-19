package com.tensquare.article.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * channel实体类
 *
 * @author xielei
 */
@Entity
@Table(name = "tb_channel")
@Data
@ToString
public class Channel implements Serializable {
    
    @Id
    private String id;//ID
    
    private String name;//频道名称
    private String state;//状态
    
}
