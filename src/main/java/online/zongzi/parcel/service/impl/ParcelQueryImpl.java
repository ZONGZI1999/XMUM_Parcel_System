package online.zongzi.parcel.service.impl;

import online.zongzi.parcel.dao.ParcelInfoDAO;
import online.zongzi.parcel.dao.UserDAO;
import online.zongzi.parcel.dto.Parcel_Info_To_Client;
import online.zongzi.parcel.entity.Parcel_Info;
import online.zongzi.parcel.enums.ConsigneeState;
import online.zongzi.parcel.enums.CurrentState;
import online.zongzi.parcel.service.ParcelQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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


    @Override
    public List<Parcel_Info_To_Client> queryAllParcel(Integer userId, Integer offset, Integer currentState) {
        List<Parcel_Info> result = parcelInfoDAO.queryParcelByUserId(userId, offset, currentState);
        List<Parcel_Info_To_Client> toClients = new ArrayList<>();
        for (Parcel_Info a: result) {
            Parcel_Info_To_Client parcelInfoToClient  = new Parcel_Info_To_Client(
                    a.getParcelId(),
                    a.getUserId(),
                    userDAO.queryUserInfo(a.getUserId()).getFullName(),
                    a.getTackingNumber(),
                    a.getCurrentState(),
                    a.getReceiveTime(),
                    a.getPickUpTime(),
                    CurrentState.stateOf(a.getCurrentState()).getStateInfo(),
                    a.getIsConsignee(),
                    ConsigneeState.stateOf(a.getIsConsignee()).getStateInfo(),
                    a.getConsigneeId(),
                    (a.getConsigneeId() == 0?
                            null:
                            userDAO.queryUserInfo(a.getConsigneeId()).getFullName())
            );
            toClients.add(parcelInfoToClient);
        }
        return toClients;
    }
}
