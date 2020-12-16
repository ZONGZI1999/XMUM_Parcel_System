package online.zongzi.parcel.dao;

import online.zongzi.parcel.entity.Parcel_Info;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: zongzi
 * @Date: 2020/12/9
 * @Description:
 **/
@Mapper
@Component("ParcelInfoDAO")
public interface ParcelInfoDAO {
    List<Parcel_Info> queryParcelByUserId(@Param("userId") Integer userId, @Param("offset") Integer offset, @Param("currentState") Integer currentState);
    Integer countParcelByUserId(@Param("userId") Integer userId, @Param("currentState") Integer currentState);
    Parcel_Info queryParcelInfo(@Param("parcelId") Integer parcelId);
    void updateConsignee(@Param("parcelId") Integer parcelId,@Param("consigneeId") Integer consigneeId);
    Integer queryParcelId(@Param("trackingNumber") String trackingNumber);
}
