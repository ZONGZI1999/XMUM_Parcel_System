package online.zongzi.parcel.dao;

import online.zongzi.parcel.entity.Parcel_Info;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: zongzi
 * @Date: 2020/12/9
 * @Description: 包裹信息的DAO
 **/
@Mapper
@Component("ParcelInfoDAO")
public interface ParcelInfoDAO {
    //获取该用户所有(按照不同state)的包裹
    List<Parcel_Info> queryParcelByUserId(@Param("userId") Integer userId, @Param("offset") Integer offset, @Param("currentState") Integer currentState);
    //获取该用户所有(按照不同state)包裹的数量
    Integer countParcelByUserId(@Param("userId") Integer userId, @Param("currentState") Integer currentState);
    //获取单条parcel的信息
    Parcel_Info queryParcelInfo(@Param("parcelId") Integer parcelId);
    //更新代取人的编号
    void updateConsignee(@Param("parcelId") Integer parcelId,@Param("consigneeId") Integer consigneeId);
    //获得parcel ID
    Integer queryParcelId(@Param("trackingNumber") String trackingNumber);
}
