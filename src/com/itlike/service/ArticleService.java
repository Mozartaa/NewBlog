package com.itlike.service;

import com.itlike.domain.Article;
import com.itlike.domain.Category;
import com.itlike.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface ArticleService {
    /*  查询所有文章信息 */
    public List<Article> getAllArticle();

    PageBean getPageData(DetachedCriteria detachedCriteria, Integer currentPage, int i);

    /* 删除文章 */
    void delete(Article article);

    /* 查询分类 */
    List<Category> getCategory(Integer parentid);

    /* 保存文章 */
    void save(Article article);

    Article getOneArticle(Integer article_id);

    void update(Article article);
}
