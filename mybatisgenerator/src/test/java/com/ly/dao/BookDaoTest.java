package com.ly.dao;

import com.ly.BaseTest;
import com.ly.entity.Book;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


/**
 * @author liuyan
 * @date 2018/7/17
 */

public class BookDaoTest extends BaseTest {

    @Autowired
    private BookDao bookDao;

    @Test
    public void testFindBookById(){
        Book book = bookDao.findBookById(15);
        System.out.println(book);
    }

    @Test
    public void testFindAll(){
        List<Book> bookList = bookDao.findAll();
        for (Book book : bookList){
            System.out.println(book);
        }
    }

    @Test
    public void testSave(){
        Book book = new Book();
        book.setName("新版三国");
        book.setAuthor("不认识");
        book.setTotal(30);
        book.setNownum(30);
        book.setIsbn("123");
        System.out.println("isbn : " + book.getIsbn());
        int num = bookDao.save(book);
        System.out.println("添加后受影响行数:" + num);
    }

    @Test
    public void testUpdateBookById(){
        Book book = new Book();
        book.setId(36);
        book.setName("水浒传");
        book.setAuthor("哈利波特");
        book.setTotal(20);
        book.setNownum(20);
        book.setIsbn("963");
        int count = bookDao.updateById(book);
        System.out.println("修改后受影响行数为:" + count);
    }

    @Test
    public void testDelById(){
        Book book = new Book();
        book.setId(34);
        int count = bookDao.delById(book);
        System.out.println("删除后受影响行数为:" + count);
    }

    @Test
    public void addBookList(){
        Book book1 = new Book();
        book1.setName("武林外传");
        book1.setAuthor("宁财神");
        book1.setTotal(50);
        book1.setNownum(50);
        book1.setIsbn("5566");

        Book book2 = new Book();
        book2.setName("龙门镖局");
        book2.setAuthor("宁财神");
        book2.setTotal(60);
        book2.setNownum(60);
        book2.setIsbn("6655");

        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);


        for (Book book : books){
            int count = bookDao.saveBookList(book);
            System.out.println("批量插入后的受影响行数为:" + count);
        }

    }
}
