package com.kaishengit.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author liuyan
 * @date 2018/7/30
 */
public class QuickStart {
    // main方法是静态方法,静态方法不能调用非静态对象
    private static Logger logger = LoggerFactory.getLogger(QuickStart.class);

    public static void main(String[] args) {

        // 1.获得shiro-realm.ini配置文件的详情,创建IniSecurityManagerFactory
        Factory<SecurityManager> securityManagerFactory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        // 1.1获取Securitymanager对象
        SecurityManager securityManager = securityManagerFactory.getInstance();
        // 2.设置安全管理器
        SecurityUtils.setSecurityManager(securityManager);
        // 3通过SecurityUtils获得subject对象(主体账号)
        Subject subject = SecurityUtils.getSubject();
        // 4.判断主体帐号是否被认证(暂指登录)Authenticated : 已鉴定
        if(!subject.isAuthenticated()){
            // 未经过认证,则通过账户名和密码登录(与shiro-realm.ini的账号密码相同)
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("admin", "123123");
            try {
                // 5.通过login方法登录(传入设置帐号密码后的token对象)
                subject.login(usernamePasswordToken);
                // subject.getPrincipal()方法获得当前登录账号的用户名（默认）
                logger.info("{} login success", subject.getPrincipal());

                // 通过hasRole("角色名称")判断当前主体是否具有某个角色
                logger.info("hashRole {} : {}" ,"admin", subject.hasRole("admin"));
                logger.info("hashRole {} : {}" ,"admin", subject.hasRole("user"));
                // 通过checkRole()方法来判断当前主体是否拥有某权限，如果没有则抛出异常
//                subject.checkRole("user");

                // 通过hasRoles()方法判断subject主体对多个角色的拥有权
                boolean[] results = subject.hasRoles(Arrays.asList("admin","user","tom"));
                for (Boolean result : results) {
                    logger.info("result: {}", result);
                }

                // 判断当前登录对象是否拥有所有角色
                logger.info("reslut all: {}", subject.hasAllRoles(Arrays.asList("admin")));

                // 通过isPermitted()判断当前对象是否具有对应的权限
                logger.info("customer:add -->{}",subject.isPermitted("customer:delete"));
                logger.info("customer:* -->{}",subject.isPermitted("customer:delete","customer:query"));
                logger.info("customer:all -->{}",subject.isPermittedAll("customer:add","customer:query"));

                // 判断是否具有该权限，如果没有则抛出异常
//                subject.checkPermission("customer:delete");

                // 使用session存值取值，大多数情况下可取代HttpSession
                Session session = subject.getSession();

                // 存值
                session.setAttribute("username", subject.getPrincipal());
                // 取值
                logger.info("username :{}", session.getAttribute("username"));

                // 安全退出
                subject.logout();

            } catch (UnknownAccountException e) {
                logger.info("用户名或者密码错误");
            } catch (IncorrectCredentialsException e) {
                logger.info("密码错误");
            } catch (LockedAccountException e) {
                logger.info("账户被锁定");
            } catch (AuthenticationException ae) {
                logger.info("认证失败");
            }
        }
    }
}
