package controller;

import Utils.PageBean;
import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.CategoryService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    HttpServletRequest request;

    @RequestMapping()
    public String findall(Map<String, Object> map){

        List<Category> list=categoryService.findCategoriesByParentId();
        map.put("parents",categoryService.findCategoriesByParentId());


        return "adminjsps/admin/category/list";
    }

    @RequestMapping(value = "findallCategory")
    public String findallCategory(Map<String, Object> map){
        map.put("parents",categoryService.findCategoriesByParentId());
        return "adminjsps/admin/book/left";
    }

@RequestMapping(value = "/msg")
    public String toadminmsg(){
        return "adminjsps/msg";
}

}
