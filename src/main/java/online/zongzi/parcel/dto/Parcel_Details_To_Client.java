package online.zongzi.parcel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * @Author: zongzi
 * @Date: 2020/12/9
 * @Description: 展示给用户包裹具体流程的数据格式
 * 例如：何时 已入库、何时 已取件
 **/
@Data
@AllArgsConstructor
public class Parcel_Details_To_Client {
    String detailTime; //状态发生时间
    String state; //当前状态
    String operatorName; //操作人的名字
}
