package com.itlike.web;

import com.itlike.domain.Article;
import com.itlike.domain.Category;
import com.itlike.domain.PageBean;
import com.itlike.service.ArticleService;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Setter;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class WebAction extends ActionSupport {

    @Setter
    private ArticleService articleService;
    /* ��ȡ��ҳ���� */
    @Setter
    private Integer currentPage=1;
    @Setter
    private Integer parentid;
    @Setter
    private Integer cid;
    public void getPageList() throws IOException {
        //System.out.println("web-------action");
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
        if(parentid!=null){
            List<Category> category = articleService.getCategory(parentid);
            Object[] cidArrays = new Object[category.size()];
            for(int i=0;i<category.size();i++){
                Category category1=category.get(i);
                cidArrays[i]=category1.getCid();
            }
            //��������
//            System.out.println(Arrays.toString(cidArrays));
              detachedCriteria.add(Restrictions.in("category.cid",cidArrays));

        }
        if(cid!=null){
            detachedCriteria.add(Restrictions.eq("category.cid",cid));
        }

        PageBean pageBean=articleService.getPageData(detachedCriteria,currentPage,5);

//        System.out.println(pageBean);
        //��json��ʽ���ظ�web��
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"handler","hibernateLazyInitializer"});
        JSONObject jsonObject = JSONObject.fromObject(pageBean, jsonConfig);
//        System.out.println(jsonArray);
        //��Ӧ��ҳ��
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().println(jsonObject.toString());
    }

    //��ѯ����
    @Setter
    private Integer id;
    public void getDetail() throws IOException {
        Article oneArticle = articleService.getOneArticle(id);
        //��json��ʽ���ظ�web��
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"handler","hibernateLazyInitializer"});
        JSONObject jsonObject = JSONObject.fromObject(oneArticle, jsonConfig);
       // System.out.println(jsonObject);
        //��Ӧ��ҳ��
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().println(jsonObject.toString());
    }



}
