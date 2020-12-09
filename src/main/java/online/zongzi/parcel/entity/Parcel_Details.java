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
public class Parcel_Details {
    private Integer parcelId;
    private Date detailTime;
    private Integer state;
}
