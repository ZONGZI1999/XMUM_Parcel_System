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
    private Integer parcelId; //包裹的ID Parcel ID
    private Integer userId; //用户的ID User ID
    private String trackingNumber; //包裹查询的编号 Tracking Number
    private Integer consigneeId; //代取人的ID(默认: 0) Consignee ID (default: 0)
}
