package online.zongzi.parcel.controller;

import online.zongzi.parcel.dao.UserDAO;
import online.zongzi.parcel.dto.LoginResult;
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
 * @Description:
 **/
@Controller
@RequestMapping("/public")
public class login {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDAO userDAO;


    @RequestMapping("/check")
    @ResponseBody
    public LoginResult loginService(@RequestBody User userFromClient,
                               HttpServletRequest httpServletRequest){
        System.out.println(userFromClient.getUserId() + '\n' + userFromClient.getPassword());
        LoginResult loginResult = new LoginResult(false, "Login failed! Please try again!");
        try{
            User user = userDAO.queryUserInfo(userFromClient.getUserId());
            if (user.getPassword().equals(userFromClient.getPassword())){
                HttpSession httpSession = httpServletRequest.getSession();
                httpSession.setAttribute("userId", userFromClient.getUserId());
                loginResult.setSuccess(true);
                loginResult.setMsg("Login successful! Please wait...");
                return loginResult;
            }
        } catch (Exception e){
            logger.warn("No user has been found. (userId:" + userFromClient.getUserId() + ")");
        }

        return loginResult;
    }

    @RequestMapping("/login")
    public String loginPage(HttpServletRequest httpServletRequest) {
        if(httpServletRequest.getSession().getAttribute("userId") != null)
            return "redirect:/myParcel";
        return "index";
    }
    @RequestMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.removeAttribute("userId");
        return "redirect:/";
    }
}
