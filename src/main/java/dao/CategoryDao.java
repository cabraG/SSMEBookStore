package dao;

import model.Category;

import java.util.List;

public interface CategoryDao {

     List<Category> findallCategory();
     List<Category> findCategoriesByParentId(String pid);


}
