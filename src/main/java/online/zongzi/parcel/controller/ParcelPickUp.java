package online.zongzi.parcel.controller;

import online.zongzi.parcel.dao.ParcelDetailsDAO;
import online.zongzi.parcel.dao.ParcelInfoDAO;
import online.zongzi.parcel.dao.UserDAO;
import online.zongzi.parcel.entity.Parcel_Details;
import online.zongzi.parcel.entity.Parcel_Info;
import online.zongzi.parcel.entity.User;
import online.zongzi.parcel.service.ParcelQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @Author: zongzi
 * @Date: 2020/12/15
 * @Description:
 **/
@Controller
public class ParcelPickUp {

    //日志打印
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //获取用户信息的数据库实例
    @Autowired
    private UserDAO userDAO;

    //获取包裹信息的数据库实例
    @Autowired
    private ParcelInfoDAO parcelInfoDAO;

    @Autowired
    private ParcelDetailsDAO parcelDetailsDAO;

    //包裹增删改查的服务
    @Autowired
    private ParcelQuery parcelQuery;

    @RequestMapping("/cancelOrRejectApply")
    public String cancelApply(HttpServletRequest httpServletRequest,
                              @RequestParam("parcelId") Integer parcelId){
        Integer userId = (Integer) httpServletRequest.getSession().getAttribute("userId");
        try{
            Parcel_Details parcelDetails = parcelDetailsDAO.queryParcelStateTime(parcelId, null);
            Parcel_Info parcelInfo = parcelInfoDAO.queryParcelInfo(parcelId);
            if (userId.equals(parcelInfo.getUserId()) &&
                    (parcelDetails.getState().equals(3) || parcelDetails.getState().equals(4))){
                parcelDetailsDAO.insertNewRecord(parcelId, -2, userId);
                parcelInfoDAO.updateConsignee(parcelId, 0);
            }
            if(userId.equals(parcelInfo.getConsigneeId()) && parcelDetails.getState().equals(4)) {
                parcelDetailsDAO.insertNewRecord(parcelId, -2, userId);
                parcelInfoDAO.updateConsignee(parcelId, 0);
            }
            if(userId.equals(parcelInfo.getConsigneeId()) && parcelDetails.getState().equals(3)) {
                parcelDetailsDAO.insertNewRecord(parcelId, -1, userId);
                parcelInfoDAO.updateConsignee(parcelId, 0);
            }
        }catch (Exception e) {
            logger.warn("非法请求. 不存在该parcel ID");
        }
        return "redirect:/parcelPickUp";
    }

    @RequestMapping("/acceptApply")
    public String acceptApply(HttpServletRequest httpServletRequest,
                              @RequestParam("parcelId") Integer parcelId){
        Integer userId = (Integer) httpServletRequest.getSession().getAttribute("userId");
        try{
            Parcel_Details parcelDetails = parcelDetailsDAO.queryParcelStateTime(parcelId, null);
            Parcel_Info parcelInfo = parcelInfoDAO.queryParcelInfo(parcelId);
            if(userId.equals(parcelInfo.getConsigneeId())
                    && (parcelDetails.getState().equals(3))){
                parcelDetailsDAO.insertNewRecord(parcelId, 4, userId);
            }
        }catch (Exception e) {
            logger.warn("非法请求. 不存在该parcel ID: " + parcelId);
        }
        return "redirect:/parcelPickUp";
    }

    @RequestMapping(value = "/applyConsignee",method = RequestMethod.POST)
    public String applyConsignee(HttpServletRequest httpServletRequest,
                                 @RequestParam("parcelId") Integer parcelId,
                                 @RequestParam("consigneeId") Integer consigneeId) {
        Integer userId = (Integer) httpServletRequest.getSession().getAttribute("userId");
        try{
            Parcel_Details parcelDetails = parcelDetailsDAO.queryParcelStateTime(parcelId, null);
            Parcel_Info parcelInfo = parcelInfoDAO.queryParcelInfo(parcelId);
            if(userId.equals(parcelInfo.getUserId())
                    && (parcelDetails.getState() <= 0)){
                parcelDetailsDAO.insertNewRecord(parcelId, 3, userId);
                parcelInfoDAO.updateConsignee(parcelId, consigneeId);
            }
        }catch (Exception e) {
            logger.warn("非法请求. 不存在该parcel ID: " + parcelId);
        }
        return "redirect:/parcelPickUp";
    }

    @RequestMapping("/userInfo")
    @ResponseBody
    public List<User> queryUserByName(@RequestParam("fullName") String fullName){
        return userDAO.queryUserInfoByName(fullName);
    }


    @RequestMapping("/parcelPickUp")
    public String allReadyToPickUp(HttpServletRequest httpServletRequest, //session
                                   @RequestParam(defaultValue = "1", required = false) Integer page, //分页
                                   @RequestParam(defaultValue = "0", required = false) Integer get, //筛选数据
                                   Model model){
        Integer userId = (Integer) httpServletRequest.getSession().getAttribute("userId");
        /*
         * 数据筛选:
         *   0 代取的包裹(默认)
         *   2 异常包裹
         */

        //数据校验
        get = get == 0 || get == 2 ? get : 0;
        //每页5条

        int resultCount = parcelInfoDAO.countParcelByUserId(userId, get); //一共有多少结果
        int maxPage = (int) Math.ceil(resultCount/5.00d); //根据结果算页数

        //数据校验
        page = page<=maxPage && page>=1 ? page : 1;
        //返回数据
        model.addAttribute("userName", userDAO.queryUserInfo(userId)); //用户名
        model.addAttribute("resultNumber", resultCount); //总共的页数
        model.addAttribute("allParcel",parcelQuery.queryAllParcel(userId, (page-1)*5, get)); //包裹数据(分页后)
        model.addAttribute("maxPage", maxPage); //最大页数
        model.addAttribute("currentPage", page); //当前页数
        model.addAttribute("get", get); //筛选数据
        return "parcelPickUp";
    }
}
