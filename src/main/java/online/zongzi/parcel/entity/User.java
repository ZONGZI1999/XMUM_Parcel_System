package online.zongzi.parcel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zongzi
 * @Date: 2020/12/9
 * @Description: 用户信息
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer userId; //用户的id
    private String fullName; //用户的全名
    private String password; //用户的密码
    private String studentID;
}
