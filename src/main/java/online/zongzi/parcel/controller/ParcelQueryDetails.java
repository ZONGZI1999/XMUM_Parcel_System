package online.zongzi.parcel.controller;

import online.zongzi.parcel.dto.ParcelQueryResult;
import online.zongzi.parcel.dto.Parcel_Details_To_Client;
import online.zongzi.parcel.service.ParcelQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: zongzi
 * @Date: 2020/12/9
 * @Description: ParcelQuery 业务逻辑
 **/
@Controller
public class ParcelQueryDetails {

    //包裹增删改查的服务
    @Autowired
    ParcelQuery parcelQuery;

    //查询包裹的页面绑定
    @RequestMapping("/parcelQuery")
    public String ParcelQuery(){
        return "parcelQuery";
    }

    //获得包裹详细数据的接口
    @RequestMapping("/query")
    @ResponseBody //返回json数据 (数据格式: ParcelQueryResult)
    public ParcelQueryResult query(@RequestParam Integer parcelId){
        //得到用户展示的数据
        List<Parcel_Details_To_Client> parcelDetailsToClient = parcelQuery.queryParcelDetails(parcelId);
        //判断数据是否为空
        boolean isEmpty = parcelDetailsToClient.isEmpty();
        //返回给用户的结果
        return new ParcelQueryResult(true, isEmpty,parcelDetailsToClient);
    }
}
