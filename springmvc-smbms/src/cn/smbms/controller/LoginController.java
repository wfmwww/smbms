package cn.smbms.controller;

import cn.smbms.entity.User;
import cn.smbms.service.user.UserService;
import cn.smbms.util.Constants;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class LoginController {
    private Logger logger = Logger.getLogger(LoginController.class);
    @Resource
    private UserService userService;

    //跳转登录页面
    @RequestMapping("/login.html")
    public String login() {
        logger.info("login.html===============================");
        return "login";
    }

    //进行登录判断
    @RequestMapping(value = "/doLogin.html", method = RequestMethod.POST)
    public String doLogin(@RequestParam(value = "userCode", required = true) String userCode,
                          @RequestParam(value = "userPassword", required = true) String userPassword,
                          HttpSession session,
                          HttpServletRequest request) {
        User user = userService.getLogin(userCode, userPassword);
        if (user == null) {
            request.setAttribute("error", "用户名或者密码错误");
            return "login";
        }
        session.setAttribute(Constants.USER_SESSION, user);
        return "redirect:/sys/main.html";

    }

    //进入首页
    @RequestMapping("/sys/main.html")
    public String main() {
        return "frame";
    }

    //注销的方法
    @RequestMapping("/logOut.html")
    public String logout(HttpSession session) {
        //清楚session
        session.removeAttribute(Constants.USER_SESSION);
        return "login";
    }

    //修改密码
    @RequestMapping("/sys/upPassword.html")
    public String upmima(){
        return "pwdmodify";
    }
    @ResponseBody
    @RequestMapping("/sys/ajaxPw.html")
    public Object getPw(String oldpassword,HttpSession session){
        String old=((User)session.getAttribute(Constants.USER_SESSION)).getUserPassword();
        if (!StringUtils.isEmpty(oldpassword)) {
            if (oldpassword.equals(old)) {
                return "true";
            }
            return "false";
        }else
            return "error";

    }

    @RequestMapping("/sys/savePw.html")
    public String savepw(String newpassword,HttpSession session){
        int i = userService.upPw(newpassword, ((User) (session.getAttribute(Constants.USER_SESSION))).getId());
        if (i > 0) {
            return "redirect:/login.html";
        }else
            return "pwdmodify";

    }
}
