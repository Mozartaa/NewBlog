package com.itlike.dao.impl;

import com.itlike.dao.ArticleDao;
import com.itlike.domain.Article;
import com.itlike.domain.Category;
import net.bytebuddy.asm.Advice;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = false)
public class ArticleDaoImpl extends HibernateDaoSupport implements ArticleDao {
    @Override
    public List<Article> getAllArticle() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
        List<Article> list = (List<Article>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return list;
    }

    @Override
    public Integer getTotalCount(DetachedCriteria detachedCriteria) {
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> list = ( List<Long>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if(list.size()>0){
            return list.get(0).intValue();
        }
        return 0;
    }

    @Override
    public List<Article> getPageData(DetachedCriteria detachedCriteria, Integer index, Integer pageSize) {

        detachedCriteria.setProjection(null);
        List<Article> list = (List<Article>)this.getHibernateTemplate().findByCriteria(detachedCriteria, index, pageSize);
        return list;
    }

    @Override
    public void delete(Article article) {
        System.out.println("dao");
        this.getHibernateTemplate().delete(article);
    }

    @Override
    public List<Category> getCategory(Integer parentid) {

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Category.class);
        detachedCriteria.add(Restrictions.eq("parentid",parentid));
        List<Category> list =(List<Category>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return list;
    }

    @Override
    public void save(Article article) {
        this.getHibernateTemplate().save(article);
    }

    @Override
    public Article getOneArticle(Integer article_id) {

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
        detachedCriteria.add(Restrictions.eq("article_id",article_id));

        List<Article> list =(List<Article>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if(list.size()>0){
            return list.get(0);
        }
//        System.out.println(article_id);
        return null;
    }

    @Override
    public void update(Article article) {
        this.getHibernateTemplate().update(article);
    }
}
