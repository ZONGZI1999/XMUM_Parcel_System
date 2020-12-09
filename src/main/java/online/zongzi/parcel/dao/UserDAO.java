package online.zongzi.parcel.dao;

import online.zongzi.parcel.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @Author: zongzi
 * @Date: 2020/12/9
 * @Description:
 **/
@Mapper
@Component("userDAO")
public interface UserDAO {
    User queryUserInfo(@Param("userId") Integer userId);
}
