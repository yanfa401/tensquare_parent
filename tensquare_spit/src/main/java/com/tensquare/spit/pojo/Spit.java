package com.tensquare.spit.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.ToString;

/**
 * 描述：吐槽
 *
 * @author xielei
 * @date 2019/08/12
 */
@Data
@ToString
public class Spit implements Serializable {
    
    private static final long serialVersionUID = -4293664571117751629L;
    
    @Id
    private String _id;
    private String content;
    private Date publishtime;
    private String userid;
    private String nickname;
    private Integer visits;
    private Integer thumbup;
    private Integer share;
    private Integer comment;
    private String state;
    private String parentid;
    
}
