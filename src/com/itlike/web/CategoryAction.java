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

    //注入业务层
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
        //调用业务层 查询所有分类
        List<Category> list=categoryService.getAllCategory();
//        System.out.println(list);
        //把数据存到值栈之中
        ActionContext.getContext().getValueStack().set("categorylist",list);
        return "list";
    }

    public String updateUI() throws IOException {
//        System.out.println("updateUI");
//        System.out.println(category.getCid());
        //调用业务层
        Category category2=categoryService.getOneCategory(category.getCid());
//        System.out.println(category2);
        //把数据给页面 通过json形式
        JSONArray jsonArray = JSONArray.fromObject(category2, new JsonConfig());
//        System.out.println(jsonArray);
        //响应给页面
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
        return null;
    }

    //修改分类
    public String update(){
//        System.out.println(category);
        //调用Service层修改category
        categoryService.update(category);
        return "listAction";
    }

    //删除分类
    public String delete(){

        System.out.println("delete"+category);
        //调用service层删除分类
        categoryService.delete(category);
        return "listAction";
    }
}
