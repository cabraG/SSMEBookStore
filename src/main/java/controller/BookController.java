package controller;

import Utils.PageBean;
import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class BookController {

@Autowired
private BookService bookService;

    @Autowired
    HttpServletRequest request;

//分页映射url用方法
    private String getUrl(HttpServletRequest req) {
        String url = req.getRequestURI() + "?" + req.getQueryString();
        /*
         * 如果url中存在pc参数，截取掉，如果不存在那就不用截取。
         */
        int index = url.lastIndexOf("&pc=");
        if(index != -1) {
            url = url.substring(0, index);
        }
        return url;
    }
//分类获得图书
    @RequestMapping(value = "/findbookbycid")
    public String findbookbycid(@RequestParam(name = "cid")String cid,@RequestParam(name = "pc",required=false)String pc,Map<String, Object> map){


        PageBean<Book> pb=bookService.findbookbycid(cid,pc);


        pb.setUrl(getUrl(request));
        map.put("pb",pb);

        return "jsps/book/list";

    }
    //id获得图书
    @RequestMapping(value = "/bookLoader")
    public String bookLoader(@RequestParam(name="bid") String bid,Map<String, Object> map){

                map.put("book",bookService.bookLoader(bid));
        return "jsps/book/desc";

    }
//三条件动态查询
    @RequestMapping(value = "findbythree")
    public String findbythree(@RequestParam(name = "pc",required=false)String pc,Book book,Map<String, Object> map){
        PageBean<Book> pb=bookService.findbythree(book,pc);
        pb.setUrl(getUrl(request));
        map.put("pb",pb);
        return "jsps/book/list";
    }
}
