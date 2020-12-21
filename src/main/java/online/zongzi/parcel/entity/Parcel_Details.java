package online.zongzi.parcel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: zongzi
 * @Date: 2020/12/9
 * @Description: 从数据库获取的包裹信息格式
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parcel_Details {
    private Integer parcelId; //parcel ID
    private Date detailTime; //发生时间
    private Integer state; //当前状态
    private Integer operatorId; //操作员的ID
}
