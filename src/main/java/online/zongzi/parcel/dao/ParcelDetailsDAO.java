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
}
