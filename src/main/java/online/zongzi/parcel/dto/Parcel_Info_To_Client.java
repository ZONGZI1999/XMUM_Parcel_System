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
public class Parcel_Info_To_Client implements Serializable {
    private static final long serialVersionUID = 4087132641904761116L;
    private Integer parcelId;
    private Integer userId;
    private String userName;
    private String trackingNumber;
    /*
     * 0 (被快递站接收)
     * 1 (自己取件了)
     * 2 (异常件)
     * 3 (申请代取中)
     * 4 (申请成功)
     * 5 (代取成功)
     */
    private Integer currentStateByNumber;
    private String currentStateByText;
    //最新流转状态时间
    private Date currentStateTime;

    private Date receiveTime;
    private Date pickUpTime;
    //若无则为 null
    private Integer consigneeId;
    private String consigneeName;

}
