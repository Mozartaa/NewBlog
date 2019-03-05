package com.itlike.web;

import com.itlike.domain.Article;
import com.itlike.domain.Category;
import com.itlike.domain.PageBean;
import com.itlike.service.ArticleService;
import com.mysql.jdbc.PingTarget;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import lombok.Setter;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.aspectj.util.FileUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ArticleAction extends ActionSupport implements ModelDriven<Article> {

    Article article=new Article();
    @Override
    public Article getModel() {
        return article;
    }

    @Setter
    private ArticleService articleService;

    public String list(){
//        System.out.println("list");
        List<Article> list = articleService.getAllArticle();
//        System.out.println(list);
        ServletActionContext.getContext().getValueStack().set("allArticle",list);
        return "list";
    }

    /* 获取分页数据 */
    @Setter
    private Integer currentPage=1;
    /* 搜索关键字*/
    @Setter
    private String keyWord;
    public String pageList() {
        System.out.println("pageList" + currentPage);
        System.out.println(keyWord);
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
        /* 设置查询条件 */
        if (keyWord != null) {
            detachedCriteria.add(Restrictions.like("article_title", "%" + keyWord + "%"));
        }
        PageBean pageBean = articleService.getPageData(detachedCriteria, currentPage, 5);
        ActionContext.getContext().getValueStack().push(pageBean);

        return "list";
    }

    /* 删除文章 */
    public String delete(){

        /* 调用业务层 删除文章 */
        Article article2 = new Article();
        article2.setArticle_id(article.getArticle_id());
        articleService.delete(article2);
        return "listres";
    }

    /* 查询所有目录*/
    @Setter
    private Integer parentid;
    public String getCategory()throws IOException {
        System.out.println(parentid);
        /*  调用业务层，查询分类 */
        List<Category>list=articleService.getCategory(parentid);
        System.out.println(list);
        //把数据给页面 通过json形式
        JSONArray jsonArray = JSONArray.fromObject(list, new JsonConfig());
//
//       System.out.println(jsonArray);
        //响应给页面
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
        return null;
    }

    /* 添加文章*/
    /**
     * 文件上传提供的三个属性:
     */
    @Setter
    private String uploadFileName; // 文件名称
    @Setter
    private File upload; // 上传文件
    @Setter
    private String uploadContentType; // 文件类型
    public String add() throws IOException {

       /* System.out.println("addd");
        System.out.println(uploadFileName);
        System.out.println(upload);
        System.out.println(uploadContentType);*/
       if(upload!=null){
           //上传图片

            //随机生成文件名称
           //获取文件扩展名  ss.jpg
           int index = uploadFileName.indexOf(".");
           String etx = uploadFileName.substring(index);
           System.out.println(etx);
           //随机生成文件名  拼接扩展名
           String uuid = UUID.randomUUID().toString();
           String uuidFileName = uuid.replace("-", "") + etx;
//           System.out.println(uuidFileName);
           //确定上传路径
           String path = ServletActionContext.getServletContext().getRealPath("/upload");
           File file = new File(path);
           if(!file.exists()){
               file.mkdirs();
           }
           //拼接新文件路径
           File desFile = new File(path + "/" + uuidFileName);
           //上传文件
           FileUtils.copyFile(upload,desFile);
           //设置图片
           article.setArticle_pic(uuidFileName);
       }
       //设置当前时间
        article.setArticle_time(new Date().getTime());
        System.out.println(article);
        //调用业务层  保存文章
        articleService.save(article);
        return "listres";
    }
    public String edit(){
        System.out.println("----");
        Integer article_id = article.getArticle_id();
        Article resArticle=articleService.getOneArticle(article_id);
//        把数据存放到值栈当中
        ActionContext.getContext().getValueStack().push(resArticle);
//        System.out.println(resArticle);

        return "edit";

    }

    //修改
    public String update() throws IOException {
        //System.out.println("update-----");
        if(upload!=null){
            //获取路径
            String path = ServletActionContext.getServletContext().getRealPath("/upload");
            //获取文件名称
            String picname=article.getArticle_pic();
            //删除原有的图片
            if(picname!=null||!"".equals(picname)){
                File file=new File(path+picname);
                file.delete();
            }
            //随机生成文件名称
            //获取文件扩展名  ss.jpg
            int index = uploadFileName.indexOf(".");
            String etx = uploadFileName.substring(index);
            System.out.println(etx);
            //随机生成文件名  拼接扩展名
            String uuid = UUID.randomUUID().toString();
            String uuidFileName = uuid.replace("-", "") + etx;
//           System.out.println(uuidFileName);
            //确定上传路径

            File file = new File(path);
            if(!file.exists()){
                file.mkdirs();
            }
            //拼接新文件路径
            File desFile = new File(path + "/" + uuidFileName);
            //上传文件
            FileUtils.copyFile(upload,desFile);
            //设置图片
            article.setArticle_pic(uuidFileName);
        }
        //设置时间
        article.setArticle_time(System.currentTimeMillis());
        //调用业务层更新文章
        articleService.update(article);
        return "listres";
    }

}
