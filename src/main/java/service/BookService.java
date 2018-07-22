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

    public PageBean<Book> findbookbycid(String cid,String pc) {
        int thispc =0;
        if(pc==null){
            thispc =1;
        }
        else{
            thispc=Integer.valueOf(pc).intValue();
        }
        PageBean<Book> bookPageBean =new PageBean<Book>();
        bookPageBean.setTr(bookDao.findbookcount());
        bookPageBean.setPs(8);
        bookPageBean.setPc(thispc);
        bookPageBean.setBeanList(bookDao.findbookbycid(cid,(thispc-1)*8,8));
        return bookPageBean;
    }


    public Book bookLoader(String bid) {
       return bookDao.bookLoader(bid);

    }

    public PageBean<Book> findbythree(Book book,String pc) {
        int thispc =0;
        if(pc==null){
            thispc =1;
        }
        else{
            thispc=Integer.valueOf(pc).intValue();
        }

        PageBean<Book> bookPageBean =new PageBean<Book>();
        bookPageBean.setTr(bookDao.findbythreecount(book));
        bookPageBean.setPc(thispc);
        bookPageBean.setPs(8);
        bookPageBean.setBeanList(bookDao.findbythree(book,(thispc-1)*8,8));
        return bookPageBean;

    }
}
