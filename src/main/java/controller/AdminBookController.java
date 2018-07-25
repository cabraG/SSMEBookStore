package controller;

import Utils.CommonUtils;
import Utils.PageBean;
import model.Book;
import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import service.BookService;
import service.CategoryService;
import service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;


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



    @Autowired
    private CategoryService categoryService;

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
    @RequestMapping(value = "/adminbookLoader")
    public String bookLoader(@RequestParam(name="bid") String bid,Map<String, Object> map){
        Book book=bookService.bookLoader(bid);
        map.put("book",book);
        map.put("parents",categoryService.findallparents());
        map.put("children",categoryService.findbypid(book.getCategory().getPid()));
        return "adminjsps/admin/book/desc";

    }

    @RequestMapping(value = "adminfindbythree")
    public String findbythree(@RequestParam(name = "pc",required=false)String pc,Book book,Map<String, Object> map){
        PageBean<Book> pb=bookService.findbythree(book,pc);
        pb.setUrl(getUrl(request));
        map.put("pb",pb);
        return "adminjsps/admin/book/list";
    }



    @RequestMapping(value = "editBook")
    public String editBook(Book book, Map<String, Object> map, Category category){
        book.setCategory(category);
        bookService.update(book);
        map.put("msg", "修改图书成功！");
        return "adminjsps/msg";
    }



    @RequestMapping(value = "deleteBook")
    public String deleteBook(Book book,Map<String, Object> map){

        map.put("msg", "刪除图书成功！");
        return "adminjsps/msg";
    }


    @RequestMapping(value = "addPre")
    public String addPre(Map<String, Object> map){

        map.put("parents",categoryService.findallparents());
        return "adminjsps/admin/book/add";
    }

    @RequestMapping(value = "insertBook")
    public String insertBook(
            @RequestParam(name="files")MultipartFile[] files,
            Book book, Category category, Map<String, Object> map) throws IOException {



        if(files!=null && files.length>0){

            String image_wfilename= UUID.randomUUID()+files[0].getOriginalFilename();
            String image_bfilename= UUID.randomUUID()+files[1].getOriginalFilename();

            if(image_wfilename.endsWith("jpg")||image_wfilename.endsWith("png")||
                    image_bfilename.endsWith("jpg")||image_bfilename.endsWith("png")){
               String leftpath= request.getSession().getServletContext().getRealPath("book_img");
               File nimage_w =new File(leftpath,image_wfilename);
               File nimage_b =new File(leftpath,image_bfilename);

                files[0].transferTo(nimage_w);
                files[1].transferTo(nimage_b);


                book.setCategory(category);
                book.setImage_b("book_img/"+image_bfilename);
                book.setImage_w("book_img/"+image_bfilename);
                book.setBid(CommonUtils.uuid());


                bookService.insertBook(book);





                map.put("msg", "添加成功！");
                return "adminjsps/msg";

            }

            map.put("msg", "11添加失败，请检测文件是否正常，格式限定为jpg和png！");
            return "adminjsps/msg";


        }



       else{
            map.put("msg", "22添加失败，请检测文件是否正常，格式限定为jpg和png！");
           return "adminjsps/msg";}

    }

}
