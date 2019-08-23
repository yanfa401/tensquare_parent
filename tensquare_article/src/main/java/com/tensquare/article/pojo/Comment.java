package com.tensquare.article.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import lombok.Data;
import lombok.ToString;

/**
 * 描述：评论,对应MongoDB中articledb库中collection
 *
 * @author xielei
 * @date 2019/08/23
 */
@Data
@ToString
public class Comment implements Serializable {
    
    private static final long serialVersionUID = 2928033539946694814L;
    
    //评论id
    @Id
    private String _id;
    
    private String articleid;
    
    private String content;
    
    private String userid;
    
    private String parentid;
    
    private Date publishdate;
}
