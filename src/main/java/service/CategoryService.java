package service;


import dao.CategoryDao;
import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("categoryService")
public class CategoryService {

     @Autowired

     private CategoryDao categoryDao;

     public List<Category> findall(){
          return   categoryDao.findallCategory();

     }

     public List<Category> findCategoriesByParentId(){
            String pid=null;

          return categoryDao.findCategoriesByParentId(pid);
     }
}
