package controller;

import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.CategoryService;


import java.util.List;
import java.util.Map;


@Controller

public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @RequestMapping(value = "/findallCategory")
    public String findallCategory(Map<String, Object> map){
        List<Category> list=categoryService.findCategoriesByParentId();
      map.put("parents",categoryService.findCategoriesByParentId());
        return "jsps/left";


    }




}
