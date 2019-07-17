package entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Tolerate;

/**
 * 描述：结果实体包装类
 *
 * @author xielei
 * @date 2019/07/17
 */

@Data
@ToString
//@Builder
public class Result<T> {
    
    @Tolerate
    public Result(){
        super();
    }
    
    public Result(boolean flag, Integer code, String message, T data){
        super();
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    //是否成功
    private boolean flag;
    
    //返回码
    private Integer code;
    
    //返回消息
    private String message;
    
    //返回数据
    private T data;
}
