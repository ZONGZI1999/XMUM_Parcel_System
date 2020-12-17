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
    private Integer userId;
    private String fullName;
    private String password;
}
