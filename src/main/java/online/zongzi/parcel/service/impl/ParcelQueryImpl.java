package online.zongzi.parcel.service.impl;

import online.zongzi.parcel.dao.ParcelDetailsDAO;
import online.zongzi.parcel.dao.ParcelInfoDAO;
import online.zongzi.parcel.dao.UserDAO;
import online.zongzi.parcel.dto.Parcel_Details_To_Client;
import online.zongzi.parcel.dto.Parcel_Info_To_Client;
import online.zongzi.parcel.entity.Parcel_Details;
import online.zongzi.parcel.entity.Parcel_Info;
import online.zongzi.parcel.enums.ConsigneeState;
import online.zongzi.parcel.enums.CurrentState;
import online.zongzi.parcel.enums.DetailState;
import online.zongzi.parcel.service.ParcelQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.*;

/**
 * @Author: zongzi
 * @Date: 2020/12/9
 * @Description:
 **/
@Service
public class ParcelQueryImpl implements ParcelQuery {

    //包裹信息的数据库实例
    @Autowired
    private ParcelInfoDAO parcelInfoDAO;

    //用户信息的数据库实例
    @Autowired
    private UserDAO userDAO;

    //包裹流转状态的数据库实例
    @Autowired
    private ParcelDetailsDAO parcelDetailsDAO;


    //获取所有包裹
    @Override
    public List<Parcel_Info_To_Client> queryAllParcel(Integer userId, Integer offset, Integer currentState) {
        //从数据库中获取原数据
        List<Parcel_Info> result = parcelInfoDAO.queryParcelByUserId(userId, offset, currentState);
        //新建一个List 用于储存结果返回给用户
        List<Parcel_Info_To_Client> toClients = new ArrayList<>();
        //对从数据库获取的所有结果进行转化, 转化为用户可读的数据
        for (Parcel_Info parcelInfo: result) {
            Parcel_Details currentStateObject = parcelDetailsDAO.queryParcelStateTime(
                    parcelInfo.getParcelId(),
                    currentState);
            Parcel_Info_To_Client parcelInfoToClient  = new Parcel_Info_To_Client(
                    parcelInfo.getParcelId(),
                    parcelInfo.getUserId(),
                    userDAO.queryUserInfo(parcelInfo.getUserId()).getFullName(),
                    parcelInfo.getTrackingNumber(),
                    currentStateObject.getState(),
                    DetailState.stateOf(currentStateObject.getState()).getStateInfo(),
                    currentStateObject.getDetailTime(),
                    parcelInfo.getReceiveTime(),
                    parcelInfo.getPickUpTime(),
                    parcelInfo.getConsigneeId(),
                    parcelInfo.getConsigneeId().equals(0)?
                            null:
                            userDAO.queryUserInfo(parcelInfo.getConsigneeId()).getFullName()

            );
            toClients.add(parcelInfoToClient);
        }
        return toClients;
    }

    //获取包裹的详细流转信息, 然后转化为用户可读的数据
    @Override
    public List<Parcel_Details_To_Client> queryParcelDetails(Integer parcelId) {

        List<Parcel_Details> result = parcelDetailsDAO.queryParcelById(parcelId); //从数据库中获得原始数据
        List<Parcel_Details_To_Client> toClients = new ArrayList<>(); //新建一个List 用于储存结果返回给用户
        DateFormat df3 = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.CHINA); //时间格式化的工具


        for (Parcel_Details parcelDetails: result){
            Parcel_Details_To_Client parcelDetailsToClient = new Parcel_Details_To_Client(
                    df3.format(parcelDetails.getDetailTime()), //获取时间, 然后对时间进行格式化
                    DetailState.stateOf(parcelDetails.getState()).getStateInfo()); //将原始数据状态转化为用户可读的信息
                                                                                    //例如0->Your parcel has been received by Warehouse!
            toClients.add(parcelDetailsToClient); //添加到返回给用户的结果中
        }
        //将所有获取到的信息按时间从早到晚进行排序,
        toClients.sort(Comparator.comparing(Parcel_Details_To_Client::getDetailTime));
        return toClients; //返回给Controller 再由Controller进行包装, 最后返回JSON给用户
    }
}
