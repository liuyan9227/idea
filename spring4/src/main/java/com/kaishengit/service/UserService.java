package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import javafx.beans.property.Property;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author liuyan
 * @date 2018/7/14
 */
public class UserService {

    private UserDao userDao;
    private Integer age;
    private String name;
    private List<String> list;
    private Set<Double> set;
    private Map<String, String> map;
    private Properties properties;



    public void setAge(Integer age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setSet(Set<Double> set) {
        this.set = set;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }

    public void save(){
        System.out.println("我是调用之前的Servicre");
        userDao.say();
        System.out.println("我是service");
    }

    public UserDao getUserDao(){
        return userDao;
    }

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public List<String> getList() {
        return list;
    }

    public Set<Double> getSet() {
        return set;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public Properties getProperties() {
        return properties;
    }
}
