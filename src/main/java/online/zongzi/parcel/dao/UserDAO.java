package online.zongzi.parcel.dao;

import online.zongzi.parcel.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: zongzi
 * @Date: 2020/12/9
 * @Description: 用户信息的DAO
 **/
@Mapper
@Component("userDAO")
public interface UserDAO {
    //得到用户的ID
    User queryUserInfo(@Param("userId") Integer userId);
    //根据名字 返回可能存在的用户名
    List<User> queryUserInfoByName(@Param("fullName") String fullName);
    User queryUserInfoByStudentId(@Param("studentId") String studentId);
}
