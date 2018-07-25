package dao;

import Utils.PageBean;
import model.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookDao {
    int findbookcount();
    List<Book> findbookbycid(@Param("cid")String cid,@Param("pc")int pc,@Param("ps")int ps);

    Book bookLoader(String bid);

    List<Book> findbythree(@Param("book")Book book, @Param("pc")int pc, @Param("ps")int ps);

    int findbythreecount(@Param("book")Book book);

    void update(@Param("book")Book book);


    void insertBook(@Param("book")Book book);
}
