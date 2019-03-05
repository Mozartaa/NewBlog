package com.itlike.web;

import com.itlike.domain.User;
import com.itlike.service.LoginService;
import com.itlike.service.impl.LoginServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class LoginAction extends ActionSupport implements ModelDriven<User>{

    private User user=new User();
    //注入业务层
    private LoginService loginService;

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public User getModel() {
        return user;
    }
    /* 登录 */
    public String login(){
        /*System.out.println("login");
        System.out.println(user);*/
        User resUser=loginService.login(user);
        if(resUser==null){
            //错误信息回显
            this.addActionError("用户名或密码错误");
            //结果页跳转
            return LOGIN;
        }else{
            //保存用户信息
            ActionContext.getContext().getSession().put("curUser",resUser);
            //登录成功跳转
            return SUCCESS;
        }
    }
    /* 退出 */
    public String loginout(){
//        System.out.println("loginout");
        //清空当前用户session
        ActionContext.getContext().getSession().remove("curUser");
        return "loginout";
    }

    //添加新用户
    public String add(){
//        System.out.println(user);
        loginService.save(user);
        return "listAction";
    }

    //获取所有用户
    public String userList(){
        System.out.println("来到了userlist");
        List<User> list=loginService.getAllUsers();
//        System.out.println(list);
        ActionContext.getContext().getValueStack().set("Userlist",list);
        return "list";
    }


    //删除用户
    public String delete(){
        System.out.println(user);
        loginService.delete(user);
        return "listAction";
    }


}
