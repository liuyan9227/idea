package com.kaishengit.tms.service;

import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.entity.Roles;

import java.util.List;
import java.util.Map;

/**
 * @author liuyan
 * @date 2018/8/31
 */
public interface RolePermissionService {

    /**
     * 传输登录人员信息
     * @param map 登录人员
     */
    void transAccount(Map<String,Object> map);

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


    /**
     * (角色主页)显示所有角色信息
     */
    List<Roles> findAllRolesWithPermission();

    /**
     * (角色新增信息)
     * @param roles roles对象
     */
    void saveRoles(Roles roles, Integer[] permissionId);

    /**
     * 删除角色信息根据角色ID
     * @param id rolesId
     */
    void delRolesById(Integer id);

    /**
     * (修改角色)查询角色和相应权限根据角色ID
     * @param id rolesId
     * @return rolesList
     */
    Roles findRolesWithPermissionByRoleId(Integer id);

    /**
     * (角色修改)提交表单
     * @param roles 修改的角色对象
     * @param permissionId 修改角色对应的权限信息
     */
    void updateRoles(Roles roles, Integer[] permissionId);

    // ---------------------------------------------------------

    /**
     * (新增权限回显)
     * @param menuType 权限类型
     * @return permissionList
     */
    List<Permission> findPermissionByPermissionType(String menuType);

    /**
     * 新增权限
     * @param permission 权限对象
     */
    void savePermission(Permission permission);

    /**
     * 删除权限
     * @param id permissionId
     */
    void delPermissionById(Integer id);


    /**
     * 修改权限->查询权限根据permissionId
     * @param id permissionId
     */
    Permission findPermissionById(Integer id);

    /**
     * 更新权限,提交表单
     * @param permission 权限信息
     */
    void editPermission(Permission permission);

    /**
     * 查询所有角色信息
     * @return rolesList
     */
    List<Roles> findAllRoles();
}
