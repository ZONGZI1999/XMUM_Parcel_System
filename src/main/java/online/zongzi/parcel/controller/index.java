package online.zongzi.parcel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: zongzi
 * @Date: 2020/12/9
 * @Description:
 **/
@Controller
public class index {
    @RequestMapping("/")
    public String indexPage() {
        return "index";
    }
}
