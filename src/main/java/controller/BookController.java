package controller;

import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.BookService;

import java.util.Map;

@Controller
public class BookController {

@Autowired
private BookService bookService;

    @RequestMapping(value = "/findbookbycid")
    public String findbookbycid(@RequestParam(value = "cid")String cid,Map<String, Object> map){

        map.put("pb",bookService.findbookbycid(cid));

        return "jsps/book/list";

    }

}
