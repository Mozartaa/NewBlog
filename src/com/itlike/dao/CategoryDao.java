package com.itlike.dao;

import com.itlike.domain.Category;

import java.util.List;

public interface CategoryDao {
    public void save(Category category);
    List<Category> getAllCategory();
    //查询当前分类
    Category getOneCategory(Integer cid);

    void update(Category category);

    void delete(Category category);
}
