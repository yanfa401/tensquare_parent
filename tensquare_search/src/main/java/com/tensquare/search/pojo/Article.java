package com.tensquare.search.pojo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;
import lombok.ToString;

/**
 * 描述： 文章实体类,对应es服务中article
 *
 * @author xielei
 * @date 2019/09/04
 */
@Data
@ToString
@Document(indexName = "articleindex", type = "article")
public class Article implements Serializable {
    
    @Id
    private String id; //ID
    
    @Field(index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String title; //文章标题
    
    @Field(index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String content; //文章内容
    
    private String state; //审核状态
}
