package com.kaishengit.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/**
 * @author liuyan
 * @date 2018/8/25
 */
public class ShiroRealm extends AuthorizingRealm {

    /**
     * 权限认证 : Auth oriz ation
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 1.权限认证的对象
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 2.1.封装角色信息对象
        Set<String> roles = new HashSet<>();
        roles.add("admin");
        // 2.权限对象中传入角色信息对象(需要Set集合)
        simpleAuthorizationInfo.setRoles(roles);
        // 3.1.封装权限信息对象
        Set<String> permissions = new HashSet<>();
        permissions.add("admin:add");
        // 3.权限对象中传入权限信息对象(Set集合)
        simpleAuthorizationInfo.setStringPermissions(permissions);

        return simpleAuthorizationInfo;
    }

    /**
     * 验证信息 : Auth entic ation
     * Controller层会将封装登录信息的token传入此方法中进行验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 接口应用指向实现类对象,将实现类对象还原为原有对象,需要强制类型转换
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        // 将token对象拆开,分出找好密码进行数据库查询
        String username = usernamePasswordToken.getUsername();
        // 为了性能,先从数据库对账号进行查询
        if(username.equals("max")){
            // 将帐号密码和realm信息传参,方法会自动匹配密码进行验证
            return new SimpleAuthenticationInfo(username, "123123", getName());
        }
        throw new UnknownAccountException("账号或密码错误");
    }
}
