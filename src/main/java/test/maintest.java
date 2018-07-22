package test;

import model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.BookService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/spring-mybatis.xml"})
public class maintest {

    @Autowired
    BookService bookService;
    @Test
    public void planerror(){
        String cid="5F79D0D246AD4216AC04E9C5FAB3199E";
        for(Book book:bookService.findbookbycid(cid).getBeanList()){
            System.out.println(book.getBname());
        }

    }
}
