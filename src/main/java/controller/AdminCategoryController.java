package controller;

import Utils.CommonUtils;

import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.BookService;
import service.CategoryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    HttpServletRequest request;


    @Autowired
    BookService bookService;

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




@RequestMapping(value = "/addCategory")
    public String addRootCategory(Category category){


        category.setCid(CommonUtils.uuid());
        categoryService.insert(category);



        return "forward:/admin/category";

}
    @RequestMapping(value = "/addChildPre")
    public String addChildPre(String pid,Map<String, Object> map){
map.put("parents",categoryService.findallparents());
map.put("pid",pid);

        return "adminjsps/admin/category/add2";

    }
    @RequestMapping(value = "/editPre")
    public String editPre(String cid,Map<String, Object> map){

        Category category=categoryService.findbycid(cid);
        map.put("parent",category);
if(category.getPid()==null)
        return "adminjsps/admin/category/edit";
else {
    map.put("parentslist",categoryService.findallparents());
    return "adminjsps/admin/category/edit2";
}
    }


    @RequestMapping(value = "/editCategory")
    public String editCategory(Category category){

        categoryService.update(category);
        return "forward:/admin/category";
    }




    @RequestMapping(value = "AjaxChangePcategory", method = RequestMethod.POST)
    @ResponseBody
    public String updateQuantity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pid = request.getParameter("pid");

        String json = toJson(categoryService.findbypid(pid));
        response.getWriter().print(json);
        return null;
    }

    // {"cid":"fdsafdsa", "cname":"fdsafdas"}
    private String toJson(Category category) {
        StringBuilder sb = new StringBuilder("{");
        sb.append("\"cid\"").append(":").append("\"").append(category.getCid()).append("\"");
        sb.append(",");
        sb.append("\"cname\"").append(":").append("\"").append(category.getCname()).append("\"");
        sb.append("}");
        return sb.toString();
    }

    // [{"cid":"fdsafdsa", "cname":"fdsafdas"}, {"cid":"fdsafdsa", "cname":"fdsafdas"}]
    private String toJson(List<Category> categoryList) {
        StringBuilder sb = new StringBuilder("[");
        for(int i = 0; i < categoryList.size(); i++) {
            sb.append(toJson(categoryList.get(i)));
            if(i < categoryList.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }


    @RequestMapping(value = "/deleteCategory")
    public String deleteCategory(Category category,Map<String, Object> map) {

        Category localcategory = categoryService.findbycid(category.getCid());
        if (bookService.findbookbycid(category.getCid(),"1").getBeanList()!=null) {
            map.put("code", "success");
            map.put("msg", "请删除该分类下的图书再对分类进行删除");
            return "adminjsps/msg";


        } else {
            categoryService.deleteCategory(localcategory);
            if (categoryService.findbycid(category.getCid()) == null) {
                map.put("code", "error");
                map.put("msg", "刪除分类成功！");
                return "adminjsps/msg";
            }
            map.put("code", "error");
            map.put("msg", "刪除分类失败，请检查其关系！");
            return "adminjsps/msg";
        }
    }



}
