package com.kaishengit.test;

import com.kaishengit.BeaseTest;
import com.kaishengit.entity.Book;
import com.kaishengit.entity.BookExample;
import com.kaishengit.mapper.BookMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author liuyan
 * @date 2018/7/19
 */

public class BookTest extends BeaseTest {
    @Autowired
    private BookMapper bookMapper;

    @Test
    public void testFindAll(){
        BookExample bookExample = new BookExample();
        List<Book> bookList = bookMapper.selectByExample(bookExample);
        for (Book book : bookList){
            System.out.println(book);
        }
    }

}
