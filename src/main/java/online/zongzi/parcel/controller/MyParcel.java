package online.zongzi.parcel.controller;

import online.zongzi.parcel.dao.ParcelInfoDAO;
import online.zongzi.parcel.dao.UserDAO;
import online.zongzi.parcel.service.ParcelQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: zongzi
 * @Date: 2020/12/9
 * @Description: MyParcel业务逻辑
 **/
@Controller
//我的包裹功能控制区
public class MyParcel {
    //日志打印
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //获取用户信息的数据库实例
    @Autowired
    private UserDAO userDAO;

    //获取包裹信息的数据库实例
    @Autowired
    private ParcelInfoDAO parcelInfoDAO;

    //包裹增删改查的服务
    @Autowired
    private ParcelQuery parcelQuery;

    //获取所有我的包裹
    @RequestMapping(value = "/myParcel", method = RequestMethod.GET)
    public String queryAllMyParcel(HttpServletRequest httpServletRequest,
                                   @RequestParam(defaultValue = "1", required = false) Integer page, //分页
                                   @RequestParam(defaultValue = "3", required = false) Integer get, //筛选数据
                                   Model model) {

        Integer userId = (Integer) httpServletRequest.getSession().getAttribute("userId");
        //数据校验
        get = get >= 1 && get <= 3 ? get : 3;
        //每页5条

        try {
            int resultCount = parcelInfoDAO.countParcelByUserId(userId, get); //一共有多少结果
            int maxPage = (int) Math.ceil(resultCount/5.00d); //根据结果算页数

            System.out.println(parcelQuery.queryAllParcel(userId, (page-1)*5, get));

            //数据校验
            page = page<=maxPage && page>=1 ? page : 1;
            //返回数据
            model.addAttribute("userName", userDAO.queryUserInfo(userId)); //用户名
            model.addAttribute("resultNumber", resultCount); //总共的结果数量
            model.addAttribute("allParcel",parcelQuery.queryAllParcel(userId, (page-1)*5, get)); //包裹数据(分页后)
            model.addAttribute("maxPage", maxPage); //最大页数
            model.addAttribute("currentPage", page); //当前页数
            model.addAttribute("get", get); //筛选数据
        }catch (Exception e){
            logger.warn("Database error");
            model.addAttribute("message", "Database Error!"); //返回数据库错误
        }
        return "myParcel";
    }
}
