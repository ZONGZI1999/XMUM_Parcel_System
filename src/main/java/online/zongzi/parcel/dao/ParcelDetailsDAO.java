package online.zongzi.parcel.dao;

import online.zongzi.parcel.entity.Parcel_Details;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: zongzi
 * @Date: 2020/12/9
 * @Description: 包裹详细状态的DAO
 **/
@Mapper
@Component("parcelDetailsDAO")
public interface ParcelDetailsDAO {
    //按用户ID获取包裹所有的状态
    List<Parcel_Details> queryParcelById(@Param("parcelId") Integer parcelId);
    //获取最新状态
    Parcel_Details queryParcelStateTime(@Param("parcelId") Integer parcelId, @Param("state") Integer State);
    //新增一条状态
    void insertNewRecord(@Param("parcelId") Integer parcelId, @Param("state") Integer State, @Param("operatorId") Integer operatorId);
}
