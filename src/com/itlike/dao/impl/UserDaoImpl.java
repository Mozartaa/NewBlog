package com.itlike.dao.impl;

import com.itlike.dao.UserDao;
import com.itlike.domain.User;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
    @Override
    public User getUser(String username, String password) {
        System.out.println(username+" "+password);
        //��ѯ���ݿ�
        //�����ĸ����ѯ
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        //��������
        detachedCriteria.add(Restrictions.eq("username",username));
        detachedCriteria.add(Restrictions.eq("password",password));
        //ʹ��QBC��ѯ
        List<User> list = (List<User>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
//        System.out.println(list);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public void save(User user) {

        this.getHibernateTemplate().save(user);
    }

    @Override
    public List<User> getAllUsers() {

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        List<User> list =(List<User>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        //System.out.println("dao"+list);
        return list;
    }

    @Override
    public void delete(User user) {

        this.getHibernateTemplate().delete(user);
    }
}
