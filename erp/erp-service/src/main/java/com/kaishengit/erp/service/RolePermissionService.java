package com.kaishengit.erp.service;

import com.kaishengit.erp.entity.Permission;
import com.kaishengit.erp.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * @author liuyan
 * @date 2018/7/27
 */
public interface RolePermissionService {

    /**
     * 查询所有角色信息
     * @return
     */
    List<Role> findRoleAll();

    /**
     * 查询所有权限的信息显示在new页面中作为显示
     * @return
     */
    List<Permission> findPermissionAll();

    /**
     * 保存角色和权限信息
     * @param role 角色对象
     * @param permissionId 权限对象
     */
    void save(Role role, Integer[] permissionId);

    /**
     * 删除权限类型,查看是否在使用
     * @return
     */
    void delPermission(Integer id);

    /**
     * 删除角色信息
     */
    void delRole(Integer id);

    /**
     * 根据roleId查询role
     * @return
     */
    Role findRoleById(Integer id);

    /**
     * 根据已选中的权限返回一个permissiin的Map集合
     * @param permissionList 已选中的permission集合
     * @return Map集合中,未选中的权限值为false,已选中的权限值为true
     */
    Map<Permission,Boolean> findPermissionWithBoolean(List<Permission> permissionList);

    /**
     * 根据获得的role信息修改当前角色信息
     * @param roleEdit
     * @return
     */
    void editRole(Role roleEdit);

    /**
     * 根据roleId删除原有的权限列表
     * @param roleEdit
     */
    void delPermissionByRoleId(Role roleEdit);

    /**
     * 跟新角色的权限信息
     * @param permissionId
     */
    void updetePermissionMap(Role roleEdit , Integer[] permissionId);

    /**
     * 根据employeeId查找选中的角色列表
     * @return
     */
    List<Role> findRoleByEmployeeId(Integer id);

    /**
     * 根据roleId(角色)查找PermissionList(权限)
     * @param id 角色的ID值
     * @return 权限列表
     */
    List<Permission> findPermissionByRoleId(Integer id);

    /**
     * 新增权限
     * @param permission
     */
    void savePermission(Permission permission);

    /**
     * 根据PermissionId查找Permission对象
     * @param id
     * @return
     */
    Permission findPermissionById(Integer id);

    /**
     * 修改permission
     * @param permission
     */
    void updetePermission(Permission permission);

    /**
     * 权限复选框回显,不能回显自己以及子类的权限
     * @param id
     * @return
     */
    List<Permission> findPermissionPid(Integer id);
}
