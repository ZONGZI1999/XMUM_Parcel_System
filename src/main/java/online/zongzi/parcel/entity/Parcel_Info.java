package online.zongzi.parcel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: zongzi
 * @Date: 2020/12/9
 * @Description:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parcel_Info {
    private Integer parcelId; //包裹的ID
    private Integer userId; //用户的ID
    private String tackingNumber; //包裹查询的编号
    /*
     * 当前状态
     * 0: 已入库
     * 1: 已取件
     * 2: 异常件
     */
    private Integer currentState; //当前的状态
    private Date receiveTime; //入库时间
    private Date pickUpTime;//取件时间
    /*
     * 是否代取
     * 0: 未代取
     * 1: 请求中
     * 2: 同意代取
     * 3: 已代取
     */
    private Integer isConsignee; //是否代取
    private Integer consigneeId; //代取人的ID
}
