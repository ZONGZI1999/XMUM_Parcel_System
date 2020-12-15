package online.zongzi.parcel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


/**
 * @Author: zongzi
 * @Date: 2020/12/9
 * @Description: 包裹详细走件信息
 **/
@Data
@AllArgsConstructor
public class ParcelQueryResult {
    private boolean Success;
    private boolean isEmpty;
    private List<Parcel_Details_To_Client> message;
}
