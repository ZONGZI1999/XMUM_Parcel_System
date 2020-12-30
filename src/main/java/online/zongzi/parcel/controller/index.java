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
        return "homepage"; //试图解析器解析homepage.html
    }

    @RequestMapping("/public/learnMore")
    public String learnMore(){
        return "learnMore";
    }

    @RequestMapping("/comingSoon")
    public String comingSoon() {
        return "comingSoon";
    }

}
