package online.zongzi.parcel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @Author: zongzi
 * @Date: 2020/12/17
 * @Description: 寄送包裹的类
 **/
@Data
@AllArgsConstructor
public class Send_Parcel {
    private Integer sendId; //发送编号
    private Integer userId; //用户的ID
    private Date submitTime; //提交时间
    private Integer currentState; //当前状态
    private String address; //地址
}
