package online.zongzi.parcel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Author: zongzi
 * @Date: 2020/12/17
 * @Description: 储存返回用户登录结果的类
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Boolean success; //是否成功
    private String msg; //消息
}
