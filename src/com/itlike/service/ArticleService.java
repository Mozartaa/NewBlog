package com.itlike.service;

import com.itlike.domain.Article;
import com.itlike.domain.Category;
import com.itlike.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface ArticleService {
    /*  ��ѯ����������Ϣ */
    public List<Article> getAllArticle();

    PageBean getPageData(DetachedCriteria detachedCriteria, Integer currentPage, int i);

    /* ɾ������ */
    void delete(Article article);

    /* ��ѯ���� */
    List<Category> getCategory(Integer parentid);

    /* �������� */
    void save(Article article);

    Article getOneArticle(Integer article_id);

    void update(Article article);
}
