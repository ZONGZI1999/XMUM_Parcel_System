package online.zongzi.parcel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: zongzi
 * @Date: 2020/12/9
 * @Description: 从数据库获取的包裹信息
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parcel_Info {
    private Integer parcelId; //包裹的ID
    private Integer userId; //用户的ID
    private String trackingNumber; //包裹查询的编号
    private Date receiveTime; //入库时间
    private Date pickUpTime;//取件时间
    private Integer consigneeId; //代取人的ID 若无则为null
}
