package com.tensquare.article;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tensquare.article.pojo.Article;
import com.tensquare.article.service.ArticleService;

/**
 * 描述：
 *
 * @author xielei
 * @date 2019/07/28
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class Test {
    
    @Autowired
    private ArticleService articleService;
    
    @org.junit.Test
    public void test1() {
        Article article = articleService.findById("1");
        System.out.println(article);
    }
}
