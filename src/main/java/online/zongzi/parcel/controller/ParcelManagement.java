package online.zongzi.parcel.controller;

import online.zongzi.parcel.dao.ParcelDetailsDAO;
import online.zongzi.parcel.dao.ParcelInfoDAO;
import online.zongzi.parcel.dao.ParcelManagementDAO;
import online.zongzi.parcel.dao.UserDAO;
import online.zongzi.parcel.dto.Result;
import online.zongzi.parcel.entity.Parcel_Details;
import online.zongzi.parcel.entity.Parcel_Info;
import online.zongzi.parcel.entity.User;
import online.zongzi.parcel.service.ParcelQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: zongzi
 * @Date: 2020/12/25
 * @Description:
 **/
@Controller
@RequestMapping("/admin")
public class ParcelManagement {

    //包裹增删改查的服务
    //Parcel Info CRUD(Create, Retrieve, Update, Delete)
    @Autowired
    private ParcelQuery parcelQuery;

    //获取包裹信息的数据库实例
    //instance of accessing database (table: parcelInfo)
    @Autowired
    private ParcelInfoDAO parcelInfoDAO;

    //获取包裹状态的数据库实例
    //instance of accessing database (table: parcel details)
    @Autowired
    private ParcelDetailsDAO parcelDetailsDAO;

    //包裹管理的数据库实例
    //instance of accessing database
    @Autowired
    private ParcelManagementDAO parcelManagementDAO;

    @Autowired
    private UserDAO userDAO;

    @RequestMapping("/parcelReg")
    public String parcelReg(){
        return "parcelReg";
    }

    @RequestMapping(value = "/addParcel",method = RequestMethod.POST)
    @ResponseBody
    public Result addParcel(HttpServletRequest httpServletRequest,
                          @RequestBody Parcel_Info parcelInfo) {
        Result result = new Result(false, "");
        Integer currentUserId = (Integer) httpServletRequest.getSession().getAttribute("userId"); //从Session中获得用户ID
        boolean ifExist = (parcelInfoDAO.queryParcelId(parcelInfo.getTrackingNumber()) != null);
        if (!ifExist) {
            parcelManagementDAO.insertIntoInfo(parcelInfo);
            parcelManagementDAO.insertIntoDetails(parcelInfo.getParcelId(), currentUserId);
            result.setSuccess(true);
            result.setMsg("Success!");
        } else {
            result.setMsg("Tracking Number duplicate!");
        }
        return result;
    }


    @RequestMapping("/parcelManagement")
    public String parcelManagement(HttpServletRequest httpServletRequest, //session
                                   @RequestParam(defaultValue = "1", required = false) Integer page, //分页
                                   @RequestParam(defaultValue = "0", required = false) Integer get, //筛选数据
                                   @RequestParam(required = false) Integer userId,
                                   Model model){
        User user = null;
        try{
            user = userDAO.queryUserInfo(userId);
        }catch (Exception e) {
            e.printStackTrace();
        }

        if(user != null) {
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
            get = get == 0 || get == 2 || get ==3 ? get : 3;


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
            model.addAttribute("userInfo", user); //用户名 username
            model.addAttribute("resultNumber", resultCount); //总共的数据数量 total number of result
            model.addAttribute("allParcel",parcelQuery.queryAllParcel(userId, (page-1)*5, get)); //包裹数据(分页后) result(after paging)
            model.addAttribute("maxPage", maxPage); //最大页数 max page
            model.addAttribute("currentPage", page); //当前页数 current page
            model.addAttribute("get", get); //筛选数据
        }

        return "parcelManagement";
    }

    @RequestMapping("/pickUp")
    @ResponseBody
    public void parcelPickUp(HttpServletRequest httpServletRequest,
                             @RequestParam Integer parcelId,
                             @RequestParam Integer userId,
                             @RequestParam(defaultValue = "0", required = false) Integer state){
        System.out.println(httpServletRequest.getPathInfo());
        Integer currentUserId = (Integer) httpServletRequest.getSession().getAttribute("userId"); //从Session中获得用户ID
        Parcel_Details parcelDetails = null;
        Parcel_Info parcelInfo = null;
        try {
            parcelDetails = parcelDetailsDAO.queryParcelStateTime(parcelId, null); //获取最新状态
            parcelInfo = parcelInfoDAO.queryParcelInfo(parcelId); //
            switch (state){
                case 0:
                    if(!(parcelDetails.getState().equals(1) || parcelDetails.getState().equals(5))){
                        if (parcelInfo.getConsigneeId().equals(userId)){ //代取的
                            parcelManagementDAO.pickUpByConsignee(parcelId, currentUserId);
                        } else if (parcelInfo.getUserId().equals(userId)) {
                            parcelManagementDAO.pickUpBySelf(parcelId, currentUserId);
                        }
                    }
                    break;
                case 1:
                    if (parcelInfo.getUserId().equals(userId)) { //自己的
                        parcelManagementDAO.abnormalParcel(parcelId, currentUserId);
                    }
                    break;
                case 2:
                    if (parcelInfo.getUserId().equals(userId)) { //自己的
                        parcelManagementDAO.deleteParcel(parcelId);
                    }
                    break;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
