package com.kaishengit.tms.service;

import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.entity.Roles;

import java.util.List;

/**
 * @author liuyan
 * @date 2018/8/31
 */
public interface RolePermissionService {
    /**
     * 查询所有权限
     * shiro判断授权
     * @return 权限List
     */
    List<Permission> findAllPermission();

    /**
     * 查询用户角色,根据用户ID
     * shiro判断授权
     * @param id accountId
     * @return rolesList
     */
    List<Roles> findRolesByAccountId(Integer id);

    /**
     * 查询用户拥有角色的权限,根据角色id查找
     * shiro判断授权
     * @param id
     * @return
     */
    List<Permission> findPermissionByRolesId(Integer id);
}
