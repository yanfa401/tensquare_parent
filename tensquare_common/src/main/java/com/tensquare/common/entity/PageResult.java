package com.tensquare.common.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 描述：分页结果实体类
 *
 * @author xielei
 * @date 2019/07/17
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    
    private Long total;
    
    private List<T> rows;
    
}
