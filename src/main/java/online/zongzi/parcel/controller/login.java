package online.zongzi.parcel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @RequestMapping("/setUser")
    public String setSession(@RequestParam("userId") Integer userId,
                             HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute("userId", userId);
        return "redirect:/";
    }
    @RequestMapping("/login")
    public String loginPage() {
        return "index";
    }
    @RequestMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.removeAttribute("userId");
        return "redirect:/";
    }
}
