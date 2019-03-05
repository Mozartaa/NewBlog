package com.itlike.dao;

import com.itlike.domain.Article;
import com.itlike.domain.Category;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface ArticleDao {
    /* 查询所有文章信息 */
    public List<Article> getAllArticle();
    /* 获取总记录数 */
    Integer getTotalCount(DetachedCriteria detachedCriteria);

    /* 查询分页数据 */
    List<Article> getPageData(DetachedCriteria detachedCriteria, Integer index, Integer pageSize);

    void delete(Article article);

    /* 查询分类 */
    List<Category> getCategory(Integer parentid);

    /* 保存文章*/
    void save(Article article);

    Article getOneArticle(Integer article_id);

    void update(Article article);
}
