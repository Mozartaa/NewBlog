package com.itlike.web;

import com.itlike.domain.Category;
import com.itlike.service.CategoryService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import lombok.Setter;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.util.List;

public class CategoryAction extends ActionSupport implements ModelDriven<Category > {

    private  Category category=new Category();

    //ע��ҵ���
    @Setter
    CategoryService categoryService;
    @Override
    public Category getModel() {
        return category;
    }

    public String add(){
//        System.out.println("add");
//        System.out.println(category);
        categoryService.save(category);
        return "listAction";
    }

    public String list(){
        System.out.println("list");
        //����ҵ��� ��ѯ���з���
        List<Category> list=categoryService.getAllCategory();
//        System.out.println(list);
        //�����ݴ浽ֵջ֮��
        ActionContext.getContext().getValueStack().set("categorylist",list);
        return "list";
    }

    public String updateUI() throws IOException {
//        System.out.println("updateUI");
//        System.out.println(category.getCid());
        //����ҵ���
        Category category2=categoryService.getOneCategory(category.getCid());
//        System.out.println(category2);
        //�����ݸ�ҳ�� ͨ��json��ʽ
        JSONArray jsonArray = JSONArray.fromObject(category2, new JsonConfig());
//        System.out.println(jsonArray);
        //��Ӧ��ҳ��
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
        return null;
    }

    //�޸ķ���
    public String update(){
//        System.out.println(category);
        //����Service���޸�category
        categoryService.update(category);
        return "listAction";
    }

    //ɾ������
    public String delete(){

        System.out.println("delete"+category);
        //����service��ɾ������
        categoryService.delete(category);
        return "listAction";
    }
}
