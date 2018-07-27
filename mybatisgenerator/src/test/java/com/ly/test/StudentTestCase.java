package com.ly.test;

import com.github.pagehelper.PageHelper;
import com.ly.entity.Student;
import com.ly.entity.StudentExample;
import com.ly.mapper.StudentMapper;
import com.ly.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author liuyan
 * @date 2018/7/13
 */
public class StudentTestCase {

    private Logger logger = LoggerFactory.getLogger(StudentTestCase.class);
    private SqlSession sqlSession;
    private StudentMapper studentMapper;

    @Before
    public void before(){
        sqlSession = MyBatisUtils.getSqlSession(true);
        studentMapper = sqlSession.getMapper(StudentMapper.class);
    }

    @After
    public void after(){
        sqlSession.close();
    }

    @Test
    public void testFindAll(){
        // 分页插件
        PageHelper.startPage(1,3);

        StudentExample studentExample = new StudentExample();
        List<Student> studentList = studentMapper.selectByExample(studentExample);
        for (Student stu : studentList){
            System.out.println("student:" + stu);
        }
    }

    @Test
    public void testFindById(){
        Student student = studentMapper.selectByPrimaryKey(2);
        System.out.println(student);
    }

    @Test
    public void testFindByName(){
        StudentExample student = new StudentExample();
        student.or().andNameEqualTo("max");
        List<Student> studentList = studentMapper.selectByExample(student);
        for (Student stu : studentList){
            System.out.println(stu);
        }
    }

    @Test
    public void testAdd(){
        Student student = new Student();
        student.setName("tom");
        student.setAge(22);
        // student.setAddress("马村");
        student.setSchoolId(3);

        int count = studentMapper.insert(student);
        System.out.println("受影响行数:" + count);
    }

    @Test
    public void testAddExample(){
        // 没用和普通添加没看出什么区别
        Student student = new Student();
        student.setName("tom");
        student.setAge(22);
        student.setAddress("山阳");
        // student.setSchoolId(3);
        int count = studentMapper.insertSelective(student);
        System.out.println("受影响行数:" + count);
    }

    @Test
    public void testDel(){
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andIdEqualTo(7);
        int count = studentMapper.deleteByExample(studentExample);
        System.out.println("受影响行数:" + count);
    }

    @Test
    public void update(){
         StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andNameEqualTo("火烈鸟");



        Student student = new Student();
        student.setId(8);
        student.setAge(66);
        // student.setName("火烈鸟");
        // student.setAddress("哈哈");



        // 根据id修改整列
        studentMapper.updateByPrimaryKey(student);
        // 根据id选择修改其中项
        studentMapper.updateByPrimaryKeySelective(student);
        // 根据其他条件修改整列
        studentMapper.updateByExample(student, studentExample);
        // 根据其他条件选择修改修改其中项
        studentMapper.updateByExampleSelective(student, studentExample);
    }
}
