package online.zongzi.parcel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @Author: zongzi
 * @Date: 2020/12/17
 * @Description:
 **/
@Data
@AllArgsConstructor
public class Send_Parcel {
    private Integer sendId;
    private Integer userId;
    private Date submitTime;
    private Integer currentState;
    private String address;
}
