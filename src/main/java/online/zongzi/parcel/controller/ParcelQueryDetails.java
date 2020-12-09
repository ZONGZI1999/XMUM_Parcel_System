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
 * @Description:
 **/
@Controller
public class ParcelQueryDetails {

    @Autowired
    ParcelQuery parcelQuery;

    @RequestMapping("/parcelQuery")
    public String ParcelQuery(){
        return "parcelQuery";
    }

    @RequestMapping("/query")
    @ResponseBody
    public ParcelQueryResult query(@RequestParam Integer parcelId){
        List<Parcel_Details_To_Client> parcelDetailsToClient = parcelQuery.queryParcelDetails(parcelId);
        boolean isEmpty = parcelDetailsToClient.isEmpty();
        ParcelQueryResult parcelQueryResult = new ParcelQueryResult(true, isEmpty,parcelDetailsToClient);
        System.out.println(parcelDetailsToClient);
        return parcelQueryResult;
    }
}
