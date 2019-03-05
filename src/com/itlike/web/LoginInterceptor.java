package com.itlike.web;

import com.itlike.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;
import org.springframework.cglib.proxy.MethodInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {

        //System.out.println("��������Ч");
        //�ж��û��Ƿ��¼
        User user=(User)ServletActionContext.getRequest().getSession().getAttribute("curUser");
        if(user==null){
            //û�е�¼
            //��ת�ص�¼ҳ��
            ActionSupport action = (ActionSupport)actionInvocation.getAction();
            action.addActionError("�㻹û�е�¼��û��Ȩ�޷���");
            return "login";
        }else{
            //����
            return actionInvocation.invoke();
        }

    }
}
