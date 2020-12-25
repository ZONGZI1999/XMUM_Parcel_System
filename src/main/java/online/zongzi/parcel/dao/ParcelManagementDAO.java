package online.zongzi.parcel.dao;

import online.zongzi.parcel.entity.Parcel_Info;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @Author: zongzi
 * @Date: 2020/12/25
 * @Description:
 **/
@Mapper
@Component("ParcelManagementDAO")
public interface ParcelManagementDAO {
    void insertIntoInfo(Parcel_Info parcelInfo);
    void insertIntoDetails(@Param("parcelId") Integer parcelId, @Param("operatorId") Integer operatorId);
    void pickUpBySelf(@Param("parcelId") Integer parcelId, @Param("operatorId") Integer operatorId);
    void pickUpByConsignee(@Param("parcelId") Integer parcelId, @Param("operatorId") Integer operatorId);
    void abnormalParcel(@Param("parcelId") Integer parcelId, @Param("operatorId") Integer operatorId);
    void deleteParcel(@Param("parcelId") Integer parcelId);
}
