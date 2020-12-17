package online.zongzi.parcel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


/**
 * @Author: zongzi
 * @Date: 2020/12/9
 * @Description: 主页跳转
 **/
@Controller
public class index {
    //主页跳转
    @RequestMapping("/")
    public String indexPage() {
        return "homepage";
    }

}
