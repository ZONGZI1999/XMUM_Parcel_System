package online.zongzi.parcel.service;

import online.zongzi.parcel.dto.Parcel_Details_To_Client;
import online.zongzi.parcel.dto.Parcel_Info_To_Client;

import java.util.List;

/**
 * @Author: zongzi
 * @Date: 2020/12/9
 * @Description:
 **/

public interface ParcelQuery {
    List<Parcel_Info_To_Client> queryAllParcel(Integer userId, Integer offset, Integer currentState);
    List<Parcel_Details_To_Client> queryParcelDetails(Integer parcelId);
}
