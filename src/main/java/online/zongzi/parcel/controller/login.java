package online.zongzi.parcel.controller;

import online.zongzi.parcel.dao.UserDAO;
import online.zongzi.parcel.dto.Result;
import online.zongzi.parcel.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author: zongzi
 * @Date: 2020/12/16
 * @Description: 登录
 **/
@Controller
@RequestMapping("/public")
public class login {

    private final Logger logger = LoggerFactory.getLogger(this.getClass()); //定义一个日志实例

    @Autowired //自动注入
    private UserDAO userDAO;  //数据库连接实例


    @RequestMapping(value = "/check",method = RequestMethod.POST)
    @ResponseBody
    public Result loginService(@RequestBody User userFromClient,
                               HttpServletRequest httpServletRequest){
        Result loginResult = new Result(false, "Login failed! Please try again!", null); //定义一个返回登录结果的实例
        try{ //进行数据库操作 使用try
            User user = userDAO.queryUserInfoByStudentId(userFromClient.getStudentID()); //从数据库中获取用户输入的ID的用户信息
            System.out.println(userFromClient);
            if (user.getPassword().equals(userFromClient.getPassword())){  //判断密码是否匹配
                //如果密码匹配
                HttpSession httpSession = httpServletRequest.getSession(); //获得Session
                httpSession.setAttribute("userId", user.getUserId()); //将user ID放入Session中
                loginResult.setSuccess(true); //将结果状态设置为成功
                loginResult.setMsg("Login successful! Please wait..."); //设置成功的消息
                loginResult.setData(user);
                return loginResult; //返回结果
            } else {
                loginResult.setMsg("Student ID or password is wrong!");
            }
        } catch (Exception e){
            //发生异常： user ID无法获取到用户的信息
            loginResult.setMsg("Student ID or password is wrong!");
            logger.warn("No user has been found. (userId:" + userFromClient.getUserId() + ")"); //日志打印
        }

        return loginResult; //返回数据
    }

    @RequestMapping("/login")
    public String loginPage(HttpServletRequest httpServletRequest) {
        if(httpServletRequest.getSession().getAttribute("userId") != null &&
                (Integer) httpServletRequest.getSession().getAttribute("userId") <=0 )
            return "redirect:/admin/parcelReg";
        if(httpServletRequest.getSession().getAttribute("userId") != null) //用户已经登录
            return "redirect:/"; //自动重定向到根目录("/")
        return "index"; //用户没登录 视图解析器 View Resolver 将会解析 index.html
    }
    @RequestMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession(); //获取Session
        httpSession.removeAttribute("userId"); //将user ID从Session中移除
        return "redirect:/"; //重定向至根目录("/")
    }

    @RequestMapping("/unauthorizedAccess")
    public String unauthorizedAccess(){
        return "unauthorizedAccess";
    }
}
