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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

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
            Parcel_Info_To_Client parcelInfoToClient  = new Parcel_Info_To_Client(
                    parcelInfo.getParcelId(), //获取包裹ID
                    parcelInfo.getUserId(), //获取用户ID
                    userDAO.queryUserInfo(parcelInfo.getUserId()).getFullName(), //获取收件人名字
                    parcelInfo.getTackingNumber(), //获取tracking Number
                    parcelInfo.getCurrentState(), //得到包裹当前的状态
                    parcelInfo.getReceiveTime(), //代收室接收到包裹的时间
                    parcelInfo.getPickUpTime(), //用户取件的时间 若还没取件 则该时间为null
                    CurrentState.stateOf(parcelInfo.getCurrentState()).getStateInfo(), //当前状态转化为用户可读的状态
                    parcelInfo.getIsConsignee(), //是否代取了
                    ConsigneeState.stateOf(parcelInfo.getIsConsignee()).getStateInfo(), //代取状态转化为用户可读的状态(若无则为null)
                    parcelInfo.getConsigneeId(), //代取人ID
                    (parcelInfo.getConsigneeId() == 0? //代取人名字(若未代取 则设置为null 避免error)
                            null:
                            userDAO.queryUserInfo(parcelInfo.getConsigneeId()).getFullName())
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
