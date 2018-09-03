package com.kaishengit.tms.shiro;

import com.alibaba.dubbo.config.annotation.Reference;

import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.AccountLoginLog;
import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.entity.Roles;
import com.kaishengit.tms.service.AccountService;
import com.kaishengit.tms.service.RolePermissionService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author liuyan
 * @date 2018/8/31
 */
public class MyRealm extends AuthorizingRealm {

    @Reference(version = "1.0")
    private RolePermissionService rolePermissionService;

    @Reference(version = "1.0")
    private AccountService accountService;

    private Logger logger = LoggerFactory.getLogger(MyRealm.class);

    /**
     * 权限验证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Account account = (Account) principalCollection.getPrimaryPrincipal();
        // 获取当前登录对象拥有的角色信息
        List<Roles> rolesList = rolePermissionService.findRolesByAccountId(account.getId());
        // 获取当前登录对象角色拥有的权限信息
        List<Permission> permissionAll = new ArrayList<>();
        for(Roles role : rolesList){
            List<Permission> permissionList = rolePermissionService.findPermissionByRolesId(role.getId());
            permissionAll.addAll(permissionList);
        }
        // 封装当前用户拥有的角色信息
        Set<String> rolesNameSet = new HashSet<>();
        for(Roles roles : rolesList) {
            rolesNameSet.add(roles.getRolesCode());
        }
        // 封装当前用户拥有的权限信息
        Set<String> permissionNameSet = new HashSet<>();
        for(Permission permission : permissionAll) {
            permissionNameSet.add(permission.getPermissionCode());
        }
        // 创建SimpleAuthorizationInfo对象,并封装当前用户拥有的角色信息和用户信息
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //当前用户拥有的角色（code）
        simpleAuthorizationInfo.setRoles(rolesNameSet);
        //当前用户拥有的权限(code)
        simpleAuthorizationInfo.setStringPermissions(permissionNameSet);

        return simpleAuthorizationInfo;
    }

    /**
     * 验证登录信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String accountMobile = usernamePasswordToken.getUsername();
        if(accountMobile != null){
            // 查询用户信息,根据姓名返回用户对象
            Account account = accountService.findAccountByModel(accountMobile);
            if(account != null){
                // 查看状态是否正常............................
                if(Account.STATE_NORMAL.equals(account.getAccountState())){
                    logger.info("{} 在 {} 登录tms-system系统: IP为: {}",account.getAccountName(),new Date(),usernamePasswordToken.getHost());

                    // 记录日志保存到数据库.....................................
                    AccountLoginLog accountLoginLog = new AccountLoginLog();
                    accountLoginLog.setAccountId(account.getId());
                    accountLoginLog.setLoginTime(new Date());
                    accountLoginLog.setLoginIp(usernamePasswordToken.getHost());

                    accountService.saveAccountLoginlog(accountLoginLog);

                    return new SimpleAuthenticationInfo(accountMobile, usernamePasswordToken.getPassword(), getName());
                } else {
                    throw new LockedAccountException("账号被禁用或锁定:" + account.getAccountState());
                }
            } else {
                throw new UnknownAccountException("此用户不存在");
            }
        } else {
            throw new UnknownAccountException("信息输入格式错误");
        }
    }
}
