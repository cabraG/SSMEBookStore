package controller;

import Utils.PageBean;
import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.BookService;
import service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
@RequestMapping("/admin/book")
public class AdminBookController {


    @Autowired
    BookService bookService;


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


    @Autowired
    private HttpServletRequest request;

    @RequestMapping()
    public String toadminindex(){
        return "adminjsps/admin/index";
    }



    @RequestMapping(value = "/adminfindbookbycid")
    public String findbookbycid(@RequestParam(name = "cid")String cid, @RequestParam(name = "pc",required=false)String pc, Map<String, Object> map){


        PageBean<Book> pb=bookService.findbookbycid(cid,pc);


        pb.setUrl(getUrl(request));
        map.put("pb",pb);

        return "adminjsps/admin/book/list";

    }

@RequestMapping(value = "/msg")
    public String toadminmsg(){
        return "adminjsps/msg";
}

}
