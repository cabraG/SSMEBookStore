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

    public void insert(Category category) {

         categoryDao.insert(category);
    }

    public List<Category> findallparents() {
         return categoryDao.findallparents();
    }

    public Category findbycid(String cid) {
        return categoryDao.findbycid(cid);
    }

    public void update(Category category) {
        categoryDao.update(category);
    }

    public List<Category> findbypid(String pid) {
         return categoryDao.findbypid(pid);
    }

    public void deleteCategory(Category category) {

         if(category.getPid()==null){
             List<Category> childrens= categoryDao.findbypid(category.getCid());
             categoryDao.deleteCategoryChild(childrens);
             categoryDao.deleteCategoryparent(category);
         }
        categoryDao.deleteCategoryparent(category);



    }
}
