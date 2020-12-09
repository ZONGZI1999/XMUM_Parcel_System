package online.zongzi.parcel.controller;

import online.zongzi.parcel.dao.ParcelInfoDAO;
import online.zongzi.parcel.dao.UserDAO;
import online.zongzi.parcel.service.ParcelQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: zongzi
 * @Date: 2020/12/9
 * @Description:
 **/
@Controller
public class MyParcel {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ParcelInfoDAO parcelInfoDAO;

    @Autowired
    private ParcelQuery parcelQuery;

    @RequestMapping(value = "/myParcel", method = RequestMethod.GET)
    public String demoMethod(@RequestParam(defaultValue = "1", required = false) Integer userId,
                             @RequestParam(defaultValue = "1", required = false) Integer page,
                             @RequestParam(defaultValue = "0", required = false) Integer get,
                             Model model) {
        //每页10条

        int resultCount = parcelInfoDAO.countParcelByUserId(userId, get);
        int maxPage = (int) Math.ceil(resultCount/5.00d);
        get = get >= 0 && get <= 2? get : 0 ;
        page = page<=maxPage && page>=1 ? page : 1;
        model.addAttribute("userName", userDAO.queryUserInfo(userId));
        model.addAttribute("resultNumber", resultCount);
        model.addAttribute("allParcel",parcelQuery.queryAllParcel(userId, (page-1)*5, get));
        model.addAttribute("maxPage", maxPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("get", get);
        return "myParcel";
    }
}
