package com.itlike.dao;

import com.itlike.domain.Article;
import com.itlike.domain.Category;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface ArticleDao {
    /* ��ѯ����������Ϣ */
    public List<Article> getAllArticle();
    /* ��ȡ�ܼ�¼�� */
    Integer getTotalCount(DetachedCriteria detachedCriteria);

    /* ��ѯ��ҳ���� */
    List<Article> getPageData(DetachedCriteria detachedCriteria, Integer index, Integer pageSize);

    void delete(Article article);

    /* ��ѯ���� */
    List<Category> getCategory(Integer parentid);

    /* ��������*/
    void save(Article article);

    Article getOneArticle(Integer article_id);

    void update(Article article);
}
