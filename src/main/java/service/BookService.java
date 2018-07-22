package service;

import Utils.PageBean;
import dao.BookDao;
import model.Book;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("bookService")
public class BookService {
@Autowired
private BookDao bookDao;

    public PageBean<Book> findbookbycid(String cid) {
        PageBean<Book> bookPageBean =new PageBean<Book>();
        bookPageBean.setTr(bookDao.findbookcount());
        bookPageBean.setPc(1);
        bookPageBean.setPs(12);
        bookPageBean.setBeanList(bookDao.findbookbycid(cid,1,12));
        return bookPageBean;
    }


}
