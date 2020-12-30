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
import java.util.ArrayList;
import java.util.List;


/**
 * @Author: zongzi
 * @Date: 2020/12/15
 * @Description: 包裹代取的业务逻辑
 **/
@Controller
public class ParcelPickUp {

    //日志打印
    //Log print
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //获取用户信息的数据库实例
    //instance of accessing database (table: user)
    @Autowired
    private UserDAO userDAO;

    //获取包裹信息的数据库实例
    //instance of accessing database (table: parcelInfo)
    @Autowired
    private ParcelInfoDAO parcelInfoDAO;

    //获取包裹状态的数据库实例
    //instance of accessing database (table: parcel details)
    @Autowired
    private ParcelDetailsDAO parcelDetailsDAO;

    //包裹增删改查的服务
    //Parcel Info CRUD(Create, Retrieve, Update, Delete)
    @Autowired
    private ParcelQuery parcelQuery;

    //取消或者拒绝代取
    //Cancel or Reject
    @RequestMapping("/cancelOrRejectApply")
    public String cancelApply(HttpServletRequest httpServletRequest,
                              @RequestParam("parcelId") Integer parcelId){
        Integer userId = (Integer) httpServletRequest.getSession().getAttribute("userId"); //从Session中获得用户ID
        try{
            Parcel_Details parcelDetails = parcelDetailsDAO.queryParcelStateTime(parcelId, null); //获取最新状态
            Parcel_Info parcelInfo = parcelInfoDAO.queryParcelInfo(parcelId); //
            if (userId.equals(parcelInfo.getUserId()) &&
                    (parcelDetails.getState().equals(3) || parcelDetails.getState().equals(4))){
                //如果是自己的包裹 并且处于申请代取 和 同意代取，这时候取消代取，就会记录为 申请人自己取消的代取
                parcelDetailsDAO.insertNewRecord(parcelId, -2, userId);
                parcelInfoDAO.updateConsignee(parcelId, 0);
            }
            if(userId.equals(parcelInfo.getConsigneeId()) && parcelDetails.getState().equals(4)) {
                //如果自己是代取人 并且 已经同意代取的状态，这时候取消代取，就会记录为 代取人取消的代取
                parcelDetailsDAO.insertNewRecord(parcelId, -2, userId);
                parcelInfoDAO.updateConsignee(parcelId, 0);
            }
            if(userId.equals(parcelInfo.getConsigneeId()) && parcelDetails.getState().equals(3)) {
                //如果自己是代取人 并且 处于申请状态，这时候取消代取， 就会记录为 代取人拒绝了申请
                parcelDetailsDAO.insertNewRecord(parcelId, -1, userId);
                parcelInfoDAO.updateConsignee(parcelId, 0);
            }
        }catch (Exception e) {
            //发生错误, 不存在相应的parcel ID
            logger.warn("非法请求. 不存在该parcel ID");
        }
        //重定向至 /parcelPickUp
        return "redirect:/parcelPickUp";
    }


    //同意代取
    //Accept
    @RequestMapping("/acceptApply")
    public String acceptApply(HttpServletRequest httpServletRequest,
                              @RequestParam("parcelId") Integer parcelId){
        Integer userId = (Integer) httpServletRequest.getSession().getAttribute("userId"); //从Session中获取user ID
        try{
            Parcel_Details parcelDetails = parcelDetailsDAO.queryParcelStateTime(parcelId, null); //获取当前最新的状态
            Parcel_Info parcelInfo = parcelInfoDAO.queryParcelInfo(parcelId); //得到包裹最新的信息
            if(userId.equals(parcelInfo.getConsigneeId())
                    && (parcelDetails.getState().equals(3))){
                //如果为代取人 并且 当前处于申请状态 那么如果同意代取 就会增加一项新的record
                parcelDetailsDAO.insertNewRecord(parcelId, 4, userId);
            }
        }catch (Exception e) {
            //发生错误, 不存在指定的parcel ID
            logger.warn("非法请求. 不存在该parcel ID: " + parcelId);
        }
        //重定向至 /parcelPickUp
        return "redirect:/parcelPickUp";
    }

    //申请代取 POST 请求
    //Applying
    @RequestMapping(value = "/applyConsignee",method = RequestMethod.POST)
    public String applyConsignee(HttpServletRequest httpServletRequest,
                                 @RequestParam("parcelId") Integer parcelId, //从用户获取 parcel ID
                                 @RequestParam("consigneeId") Integer consigneeId) { //从用户获取 代取人ID
        Integer userId = (Integer) httpServletRequest.getSession().getAttribute("userId"); //从Session中获取user ID
        try{
            Parcel_Details parcelDetails = parcelDetailsDAO.queryParcelStateTime(parcelId, null); //获取最新的状态
            Parcel_Info parcelInfo = parcelInfoDAO.queryParcelInfo(parcelId); //得到info
            if(userId.equals(parcelInfo.getUserId()) //如果是本人的包裹
                    && (parcelDetails.getState() <= 0)){ //并且处于未代取的状态(<=0均为未代取的状态)
                parcelDetailsDAO.insertNewRecord(parcelId, 3, userId); //新增一条record
                parcelInfoDAO.updateConsignee(parcelId, consigneeId); //同时增加代取人的id
            }
        }catch (Exception e) {
            logger.warn("非法请求. 不存在该parcel ID: " + parcelId);
        }
        return "redirect:/parcelPickUp";
    }

    @RequestMapping("/userInfo")
    @ResponseBody
    public List<User> queryUserByName(@RequestParam("fullName") String fullName){
        List<User> result = new ArrayList<>();
        try{
            for (User user: userDAO.queryUserInfoByName(fullName)){
                if (user.getUserId()>=0){
                    result.add(user);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


    @RequestMapping("/parcelPickUp")
    public String allReadyToPickUp(HttpServletRequest httpServletRequest, //session
                                   @RequestParam(defaultValue = "1", required = false) Integer page, //分页
                                   @RequestParam(defaultValue = "0", required = false) Integer get, //筛选数据
                                   Model model){
        Integer userId = (Integer) httpServletRequest.getSession().getAttribute("userId");
        /*
         * 数据筛选:
         * get value: meaning
         *   0 未取的包裹(默认)
         *   0 unpick up parcel (default)
         *   2 异常包裹
         *   2 abnormal parcel
         */

        //数据校验
        //data validation
        get = get == 0 || get == 2 ? get : 0;


        //一共有多少结果
        //count total number result
        int resultCount = parcelInfoDAO.countParcelByUserId(userId, get);
        //根据结果算页数
        //count total page we need (5 results per page)
        int maxPage = (int) Math.ceil(resultCount/5.00d);

        //数据校验
        //data validation
        page = page<=maxPage && page>=1 ? page : 1;
        //返回数据
        //return result to user client.
        model.addAttribute("userName", userDAO.queryUserInfo(userId)); //用户名 username
        model.addAttribute("resultNumber", resultCount); //总共的数据数量 total number of result
        model.addAttribute("allParcel",parcelQuery.queryAllParcel(userId, (page-1)*5, get)); //包裹数据(分页后) result(after paging)
        model.addAttribute("maxPage", maxPage); //最大页数 max page
        model.addAttribute("currentPage", page); //当前页数 current page
        model.addAttribute("get", get); //筛选数据
        return "parcelPickUp";
    }
}
