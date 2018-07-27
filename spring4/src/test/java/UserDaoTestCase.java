import com.kaishengit.dao.UserDao;
import com.kaishengit.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

/**
 * @author liuyan
 * @date 2018/7/13
 */
public class UserDaoTestCase {

    @Test
    public void testSay(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = (UserDao) context.getBean("userDao");
        userDao.say();
    }

    @Test
    public void testUserService(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
//        userService.save();
//        userService.getUserDao().say();

        int age = userService.getAge();
        System.out.println(age);

        String name = userService.getName();
        System.out.println(name);

        List<String> lists = userService.getList();
        for (String list : lists){
            System.out.println("List:" + list);
        }

        Map<String,String> maps = userService.getMap();
        Set<String> mapSet = maps.keySet();
        for (String set : mapSet){
            System.out.println("Map:" + set);
        }

        Set<Double> sets = userService.getSet();
        for (Double set : sets){
            System.out.println("Set:" + set);
        }

        Properties properties = userService.getProperties();
        Enumeration<Object> em = properties.keys();
        while (em.hasMoreElements()){
            String str = (String) properties.get(em.nextElement());
            System.out.println(str);
        }

        Properties prop = userService.getProperties();
        Enumeration<Object> key = prop.keys();
        while (key.hasMoreElements()){
            Object nextKey = key.nextElement();
            Object value = prop.get(nextKey);
            System.out.println("key:" + nextKey);
            System.out.println("value:" + value);
        }
    }

}
