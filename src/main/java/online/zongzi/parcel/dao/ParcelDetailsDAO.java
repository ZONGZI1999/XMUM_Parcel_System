package online.zongzi.parcel.dao;

import online.zongzi.parcel.entity.Parcel_Details;
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
@Component("parcelDetailsDAO")
public interface ParcelDetailsDAO {
    List<Parcel_Details> queryParcelById(@Param("parcelId") Integer parcelId);
    //获取最新状态
    Parcel_Details queryParcelStateTime(@Param("parcelId") Integer parcelId, @Param("state") Integer State);
    void insertNewRecord(@Param("parcelId") Integer parcelId, @Param("state") Integer State);
}
