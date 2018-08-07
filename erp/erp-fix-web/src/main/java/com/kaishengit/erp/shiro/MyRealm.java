package com.kaishengit.erp.shiro;

import com.kaishengit.erp.entity.Employee;
import com.kaishengit.erp.entity.EmployeeLoginLog;
import com.kaishengit.erp.entity.Permission;
import com.kaishengit.erp.entity.Role;
import com.kaishengit.erp.service.EmployeeService;
import com.kaishengit.erp.service.RolePermissionService;
import com.kaishengit.erp.utils.Constant;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @author liuyan
 * @date 2018/7/30
 */
public class MyRealm extends AuthorizingRealm {

    Logger logger = LoggerFactory.getLogger(MyRealm.class);

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RolePermissionService rolePermissionService;

    /**
     * 判断角色和权限的授权
     * 启动方式:在前端页面存在<shiro:>标签时,Controller的注解时启动
     * 大概步骤:获得(数据库查询)登录用户的所有角色信息和权限信息,将信息封装在set集合中设置进simpleAuthorizationInfo
     * 对象中传值,封装的信息与前端shiro标签的name属性比对,相同代表具有此角色和权限
     * @param principalCollection (主要收藏)可获得登录对象
     * @return AuthorizationInfo(授权信息)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 1.主要 : 查询>出所有<角色信息>和<权限信息>
        // 获得当前登陆的对象
        Employee employee = (Employee)principalCollection.getPrimaryPrincipal();
        // 获取当前对象的角色信息
        List<Role> roleList = rolePermissionService.findRoleByEmployeeId(employee.getId());
        // 先创建一个List集合作为存放多个permissionList的容器,现在容器具有此用户的所有权限
        List<Permission> permissionList = new ArrayList<>();
        // 获取当前对象的权限信息
        for(Role roles : roleList){
            List<Permission> permissions = rolePermissionService.findPermissionByRoleId(roles.getId());
            // 注意 : 多个List集合存入另一个List容器时,用addAll()方法;
            permissionList.addAll(permissions);
        }
        // 1.主要 : 存放传值所有的<角色信息>和<权限信息>
        // 将查询出的角色信息封装在Set<String>集合中
        Set<String> roles = new HashSet<>();
        for(Role role : roleList){
            // 在set集合中设置的属性需要跟jsp页面的<shiro:hasRole name="...">name值相同
            roles.add(role.getRoleCode());
        }
        // 将查询的权限信息封装在Set<String>集合中
        Set<String> permissions = new HashSet<>();
        for(Permission permission : permissionList){
            // <shiro:hasPermission name="..."> ↓
            permissions.add(permission.getPermissionCode());
        }
        // 创建simpleAuthorizationInfo对象用于返回值
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 在simpleAuthorizationInfo对象中有三个属性,拥有,get和set方法,将值set到对象中
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    /**
     * 判断登录
     * @param authenticationToken usernamePasswordToken实现的接口,存放传值登录信息:帐号,密码,ip
     * @return SimpleAuthenticationInfo(简单的认证信息)存放传值认证信息
     * @throws AuthenticationException 认证异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 从controller的usernamePasswordToken对象传入,用usernamePasswordToken接收需要强制类型转换为usernamePasswordToken对象
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        // 获取token中的帐号(此项目为电话号码登录)
        String userTel = usernamePasswordToken.getUsername();
        // 数据库中查找是否有该账号存在,根据规范只能用service对象进行查询
        Employee employee = employeeService.findEmployeeByTel(userTel);
        // 判断employee是否存在
        if(employee != null){
            // 判断状态是否正常
            if(employee.getState().equals(Constant.STATE_NORMAL)){
                // 登陆成功,获取帐号IP作为记录日志
                String loginIp = usernamePasswordToken.getHost();
                // 记录日志
                EmployeeLoginLog employeeLoginLog = new EmployeeLoginLog();
                employeeLoginLog.setLoginIp(loginIp);
                employeeLoginLog.setLoginTime(new Date());
                employeeLoginLog.setEmployeeId(employee.getId());
                // 保存到数据库
                employeeService.saveLoginLog(employeeLoginLog);
                // log4j在控制台打印日志
                logger.info("{}-{} 在 {} 登录了系统", employee.getEmployeeName(),employee.getEmployeeTel(),new Date());
                // TODO 这里不明白

                return new SimpleAuthenticationInfo(employee, employee.getPassword(),getName());
            } else {
                throw new LockedAccountException("账户已被冻结");
            }
        } else {
            throw new UnknownAccountException("帐号或密码错误");
        }
    }



}
