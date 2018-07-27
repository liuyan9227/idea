package com.ly.dao;

import com.ly.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liuyan
 * @date 2018/7/17
 */

@Repository
public class BookDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Book findBookById(Integer id){
        String sql = "select * from t_book where id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class),id);
    }

    public List<Book> findAll(){
        String sql ="select * from t_book";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
    }

    public int save(Book book){
        String sql ="insert into t_book (t_book.name,author,total,nownum,isbn) values (?,?,?,?,?)";
        return jdbcTemplate.update(sql, book.getName(), book.getAuthor(), book.getTotal(), book.getNownum(), book.getIsbn());
    }

    public int updateById(Book book){
        String sql = "update t_book set name= ?,author= ?, total= ?, nownum= ?,isbn= ? where id= ?";
        return jdbcTemplate.update(sql, book.getName(), book.getAuthor(), book.getTotal(), book.getNownum(), book.getIsbn(), book.getId());
    }

    /**
     * 删除书籍根据id,
     * @param book 根据参数传进的bookId删除此id的书籍信息
     * @return 受影响行数
     */
    public int delById(Book book){
        String sql = "delete from t_book where id = ?";
        return jdbcTemplate.update(sql, book.getId());
    }

    public int saveBookList(Book book){
        String sql = "insert into t_book (t_book.name,author,total,nownum,isbn) values (?,?,?,?,?)";
        return jdbcTemplate.update(sql, book.getName(), book.getAuthor(), book.getTotal(), book.getNownum(), book.getIsbn());
    }


}
