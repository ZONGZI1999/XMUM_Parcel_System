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
import java.text.SimpleDateFormat;
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

    @Autowired
    private ParcelInfoDAO parcelInfoDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ParcelDetailsDAO parcelDetailsDAO;

    @Override
    public List<Parcel_Info_To_Client> queryAllParcel(Integer userId, Integer offset, Integer currentState) {
        List<Parcel_Info> result = parcelInfoDAO.queryParcelByUserId(userId, offset, currentState);
        List<Parcel_Info_To_Client> toClients = new ArrayList<>();
        for (Parcel_Info parcelInfo: result) {
            Parcel_Info_To_Client parcelInfoToClient  = new Parcel_Info_To_Client(
                    parcelInfo.getParcelId(),
                    parcelInfo.getUserId(),
                    userDAO.queryUserInfo(parcelInfo.getUserId()).getFullName(),
                    parcelInfo.getTackingNumber(),
                    parcelInfo.getCurrentState(),
                    parcelInfo.getReceiveTime(),
                    parcelInfo.getPickUpTime(),
                    CurrentState.stateOf(parcelInfo.getCurrentState()).getStateInfo(),
                    parcelInfo.getIsConsignee(),
                    ConsigneeState.stateOf(parcelInfo.getIsConsignee()).getStateInfo(),
                    parcelInfo.getConsigneeId(),
                    (parcelInfo.getConsigneeId() == 0?
                            null:
                            userDAO.queryUserInfo(parcelInfo.getConsigneeId()).getFullName())
            );
            toClients.add(parcelInfoToClient);
        }
        return toClients;
    }

    @Override
    public List<Parcel_Details_To_Client> queryParcelDetails(Integer parcelId) {
        List<Parcel_Details> result = parcelDetailsDAO.queryParcelById(parcelId);
        List<Parcel_Details_To_Client> toClients = new ArrayList<>();
        DateFormat df3 = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.CHINA);

        for (Parcel_Details parcelDetails: result){
            Parcel_Details_To_Client parcelDetailsToClient = new Parcel_Details_To_Client(
                    df3.format(parcelDetails.getDetailTime()),
                    DetailState.stateOf(parcelDetails.getState()).getStateInfo());
            toClients.add(parcelDetailsToClient);
        }
        toClients.sort(Comparator.comparing(Parcel_Details_To_Client::getDetailTime));
        return toClients;
    }
}
