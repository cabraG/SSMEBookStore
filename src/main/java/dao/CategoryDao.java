package dao;

import model.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryDao {

     List<Category> findallCategory();
     List<Category> findCategoriesByParentId(String pid);


    void insert(@Param("category")Category category);

    List<Category> findallparents();

    Category findbycid(@Param("cid") String cid);

    void update(@Param("category")Category category);

    List<Category> findbypid(@Param("pid") String pid);
}
