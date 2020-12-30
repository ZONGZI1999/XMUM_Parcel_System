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
public class Parcel_Info_To_Client implements Serializable {
    private static final long serialVersionUID = 4087132641904761116L;
    private Integer parcelId;
    private Integer userId;
    private String userName;
    private String trackingNumber;
    /*
     * -2 代取被取消 Apply for consignee pick up is canceled.
     * -1 代取被拒绝 Apply for consignee pick up is rejected.
     * 0 (被快递站接收) Parcel is received by parcel station.
     * 1 (自己取件了) Pickup by user
     * 2 (异常件) Abnormal parcel
     * 3 (申请代取中) Applying for consignee picking up
     * 4 (申请成功) Accept apply
     * 5 (代取成功) Pick up by consignee
     */
    private Integer currentStateByNumber;
    private String currentStateByText;
    //最新流转状态时间 current state time.
    private Date currentStateTime;

    //若无则为 null
    private Integer consigneeId;
    private String consigneeName;
    private String OperatorName;
}
