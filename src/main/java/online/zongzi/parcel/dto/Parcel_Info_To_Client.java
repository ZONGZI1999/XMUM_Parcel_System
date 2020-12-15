package online.zongzi.parcel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : zongzi
 * @date : 2020/12/9
 * 展示给用户的包裹详细信息的数据格式
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parcel_Info_To_Client {
    private static final long serialVersionUID = 4087132641904761116L;
    private Integer parcelId;
    private Integer userId;
    private String userName;
    private String trackingNumber;
    /*
     * 当前状态
     * 0: 已入库
     * 1: 已取件
     * 2: 异常件
     */
    private Integer currentStateByNumber;
    private Date receiveTime;
    private Date pickUpTime;
    private String currentStateByText;
    /*
     * 是否代取
     * 0: 未代取
     * 1: 请求中
     * 2: 同意代取
     * 3: 已代取
     */
    private Integer isConsigneeByNumber;
    private String isConsigneeByText;
    private Integer consigneeId;
    private String consigneeName;

}
