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

    /* ��ȡ��ҳ���� */
    @Setter
    private Integer currentPage=1;
    /* �����ؼ���*/
    @Setter
    private String keyWord;
    public String pageList() {
        System.out.println("pageList" + currentPage);
        System.out.println(keyWord);
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
        /* ���ò�ѯ���� */
        if (keyWord != null) {
            detachedCriteria.add(Restrictions.like("article_title", "%" + keyWord + "%"));
        }
        PageBean pageBean = articleService.getPageData(detachedCriteria, currentPage, 5);
        ActionContext.getContext().getValueStack().push(pageBean);

        return "list";
    }

    /* ɾ������ */
    public String delete(){

        /* ����ҵ��� ɾ������ */
        Article article2 = new Article();
        article2.setArticle_id(article.getArticle_id());
        articleService.delete(article2);
        return "listres";
    }

    /* ��ѯ����Ŀ¼*/
    @Setter
    private Integer parentid;
    public String getCategory()throws IOException {
        System.out.println(parentid);
        /*  ����ҵ��㣬��ѯ���� */
        List<Category>list=articleService.getCategory(parentid);
        System.out.println(list);
        //�����ݸ�ҳ�� ͨ��json��ʽ
        JSONArray jsonArray = JSONArray.fromObject(list, new JsonConfig());
//
//       System.out.println(jsonArray);
        //��Ӧ��ҳ��
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
        return null;
    }

    /* �������*/
    /**
     * �ļ��ϴ��ṩ����������:
     */
    @Setter
    private String uploadFileName; // �ļ�����
    @Setter
    private File upload; // �ϴ��ļ�
    @Setter
    private String uploadContentType; // �ļ�����
    public String add() throws IOException {

       /* System.out.println("addd");
        System.out.println(uploadFileName);
        System.out.println(upload);
        System.out.println(uploadContentType);*/
       if(upload!=null){
           //�ϴ�ͼƬ

            //��������ļ�����
           //��ȡ�ļ���չ��  ss.jpg
           int index = uploadFileName.indexOf(".");
           String etx = uploadFileName.substring(index);
           System.out.println(etx);
           //��������ļ���  ƴ����չ��
           String uuid = UUID.randomUUID().toString();
           String uuidFileName = uuid.replace("-", "") + etx;
//           System.out.println(uuidFileName);
           //ȷ���ϴ�·��
           String path = ServletActionContext.getServletContext().getRealPath("/upload");
           File file = new File(path);
           if(!file.exists()){
               file.mkdirs();
           }
           //ƴ�����ļ�·��
           File desFile = new File(path + "/" + uuidFileName);
           //�ϴ��ļ�
           FileUtils.copyFile(upload,desFile);
           //����ͼƬ
           article.setArticle_pic(uuidFileName);
       }
       //���õ�ǰʱ��
        article.setArticle_time(new Date().getTime());
        System.out.println(article);
        //����ҵ���  ��������
        articleService.save(article);
        return "listres";
    }
    public String edit(){
        System.out.println("----");
        Integer article_id = article.getArticle_id();
        Article resArticle=articleService.getOneArticle(article_id);
//        �����ݴ�ŵ�ֵջ����
        ActionContext.getContext().getValueStack().push(resArticle);
//        System.out.println(resArticle);

        return "edit";

    }

    //�޸�
    public String update() throws IOException {
        //System.out.println("update-----");
        if(upload!=null){
            //��ȡ·��
            String path = ServletActionContext.getServletContext().getRealPath("/upload");
            //��ȡ�ļ�����
            String picname=article.getArticle_pic();
            //ɾ��ԭ�е�ͼƬ
            if(picname!=null||!"".equals(picname)){
                File file=new File(path+picname);
                file.delete();
            }
            //��������ļ�����
            //��ȡ�ļ���չ��  ss.jpg
            int index = uploadFileName.indexOf(".");
            String etx = uploadFileName.substring(index);
            System.out.println(etx);
            //��������ļ���  ƴ����չ��
            String uuid = UUID.randomUUID().toString();
            String uuidFileName = uuid.replace("-", "") + etx;
//           System.out.println(uuidFileName);
            //ȷ���ϴ�·��

            File file = new File(path);
            if(!file.exists()){
                file.mkdirs();
            }
            //ƴ�����ļ�·��
            File desFile = new File(path + "/" + uuidFileName);
            //�ϴ��ļ�
            FileUtils.copyFile(upload,desFile);
            //����ͼƬ
            article.setArticle_pic(uuidFileName);
        }
        //����ʱ��
        article.setArticle_time(System.currentTimeMillis());
        //����ҵ����������
        articleService.update(article);
        return "listres";
    }

}
