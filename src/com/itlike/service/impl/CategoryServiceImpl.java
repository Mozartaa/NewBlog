package com.itlike.service.impl;

import com.itlike.dao.CategoryDao;
import com.itlike.domain.Category;
import com.itlike.service.CategoryService;
import lombok.Setter;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Setter
    private CategoryDao categoryDao;


    @Override
    public void save(Category category) {
//        System.out.println("category service");
        categoryDao.save(category);
    }

    @Override
    public List<Category> getAllCategory() {

        List<Category>list=categoryDao.getAllCategory();
        return list;
    }

    @Override
    public Category getOneCategory(Integer cid) {
        //调用DAO层
        Category category=categoryDao.getOneCategory(cid);
        return category;
    }

    @Override
    public void update(Category category) {
//        System.out.println(category);
        categoryDao.update(category);
    }

    @Override
    public void delete(Category category) {
        //调用dao层删除分类
        categoryDao.delete(category);
    }
}
