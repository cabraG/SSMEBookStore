package dao;

import Utils.PageBean;
import model.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookDao {
    int findbookcount();
    List<Book> findbookbycid(@Param("cid")String cid,@Param("pc")int pc,@Param("ps")int ps);
}
