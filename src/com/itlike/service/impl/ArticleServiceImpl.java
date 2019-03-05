package com.itlike.service.impl;

import com.itlike.dao.ArticleDao;
import com.itlike.domain.Article;
import com.itlike.domain.Category;
import com.itlike.domain.PageBean;
import com.itlike.service.ArticleService;
import lombok.Setter;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public class ArticleServiceImpl implements ArticleService {

    @Setter
    private ArticleDao articleDao;
    @Override
    public List<Article> getAllArticle() {
        List<Article> allArticle = articleDao.getAllArticle();
        return allArticle;
    }

    @Override
    public PageBean getPageData(DetachedCriteria detachedCriteria, Integer currentPage, int pageSize) {
        PageBean<Article> pageBean = new PageBean<>();
        /* 设置当前页*/
        pageBean.setCurrentPage(currentPage);
        /* 设置每一页有多少条数据 */
        pageBean.setPageSize(pageSize);
        /* 去数据库查询有多少条数据  总记录数*/
        Integer totalCount=articleDao.getTotalCount(detachedCriteria);
        pageBean.setTotalCount(totalCount);
        /* 设置总页数 */
        pageBean.setTotalPage(pageBean.getTotalPage());
        /* 设置当前页的数据  从数据库中查询 */
        List<Article>list= articleDao.getPageData(detachedCriteria,pageBean.getIndex(),pageBean.getPageSize());
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public void delete(Article article) {
        /* 调用DAO层，删除文章*/
        articleDao.delete(article);
    }

    @Override
    public List<Category> getCategory(Integer parentid) {
        /* 调用Dao层查询分类 */
        List<Category> list=articleDao.getCategory(parentid);
        return list;
    }

    @Override
    public void save(Article article) {
        //调用DAO 保存文章
        articleDao.save(article);
    }

    @Override
    public Article getOneArticle(Integer article_id) {

        Article article=articleDao.getOneArticle(article_id);
        return article;
    }

    @Override
    public void update(Article article) {
        articleDao.update(article);
    }
}
