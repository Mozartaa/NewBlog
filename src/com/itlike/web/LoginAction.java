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
    //ע��ҵ���
    private LoginService loginService;

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public User getModel() {
        return user;
    }
    /* ��¼ */
    public String login(){
        /*System.out.println("login");
        System.out.println(user);*/
        User resUser=loginService.login(user);
        if(resUser==null){
            //������Ϣ����
            this.addActionError("�û������������");
            //���ҳ��ת
            return LOGIN;
        }else{
            //�����û���Ϣ
            ActionContext.getContext().getSession().put("curUser",resUser);
            //��¼�ɹ���ת
            return SUCCESS;
        }
    }
    /* �˳� */
    public String loginout(){
//        System.out.println("loginout");
        //��յ�ǰ�û�session
        ActionContext.getContext().getSession().remove("curUser");
        return "loginout";
    }

    //������û�
    public String add(){
//        System.out.println(user);
        loginService.save(user);
        return "listAction";
    }

    //��ȡ�����û�
    public String userList(){
        System.out.println("������userlist");
        List<User> list=loginService.getAllUsers();
//        System.out.println(list);
        ActionContext.getContext().getValueStack().set("Userlist",list);
        return "list";
    }


    //ɾ���û�
    public String delete(){
        System.out.println(user);
        loginService.delete(user);
        return "listAction";
    }


}
