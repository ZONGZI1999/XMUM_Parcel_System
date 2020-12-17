package online.zongzi.parcel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Author: zongzi
 * @Date: 2020/12/17
 * @Description:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResult {
    private Boolean success;
    private String msg;
}
