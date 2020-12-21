package online.zongzi.parcel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


/**
 * @Author: zongzi
 * @Date: 2020/12/9
 * @Description: 包裹单条状态的类
 **/
@Data
@AllArgsConstructor
public class ParcelQueryResult {
    private boolean Success; //是否成功
    private boolean isEmpty; //是否结果为空
    private List<Parcel_Details_To_Client> message; //返回的结果
}
