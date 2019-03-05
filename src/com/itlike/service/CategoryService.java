package com.itlike.service;

import com.itlike.domain.Category;

import java.util.List;

public interface CategoryService {

    public void save(Category category);

    List<Category> getAllCategory();

    Category getOneCategory(Integer cid);

    void update(Category category);

    void delete(Category category);
}
