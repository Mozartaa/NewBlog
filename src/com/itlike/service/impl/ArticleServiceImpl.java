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
        /* ���õ�ǰҳ*/
        pageBean.setCurrentPage(currentPage);
        /* ����ÿһҳ�ж��������� */
        pageBean.setPageSize(pageSize);
        /* ȥ���ݿ��ѯ�ж���������  �ܼ�¼��*/
        Integer totalCount=articleDao.getTotalCount(detachedCriteria);
        pageBean.setTotalCount(totalCount);
        /* ������ҳ�� */
        pageBean.setTotalPage(pageBean.getTotalPage());
        /* ���õ�ǰҳ������  �����ݿ��в�ѯ */
        List<Article>list= articleDao.getPageData(detachedCriteria,pageBean.getIndex(),pageBean.getPageSize());
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public void delete(Article article) {
        /* ����DAO�㣬ɾ������*/
        articleDao.delete(article);
    }

    @Override
    public List<Category> getCategory(Integer parentid) {
        /* ����Dao���ѯ���� */
        List<Category> list=articleDao.getCategory(parentid);
        return list;
    }

    @Override
    public void save(Article article) {
        //����DAO ��������
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
