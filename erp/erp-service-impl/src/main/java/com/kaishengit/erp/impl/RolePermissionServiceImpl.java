package com.kaishengit.erp.impl;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.kaishengit.erp.entity.*;
import com.kaishengit.erp.exception.ServiceException;
import com.kaishengit.erp.mapper.EmployeeRoleMapper;
import com.kaishengit.erp.mapper.PermissionMapper;
import com.kaishengit.erp.mapper.RoleMapper;
import com.kaishengit.erp.mapper.RolePermissionMapper;
import com.kaishengit.erp.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuyan
 * @date 2018/7/27
 */
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private EmployeeRoleMapper employeeRoleMapper;


    /**
     * 查询所有角色信息将角色权限List写入role对象中
     * @return
     */
    @Override
    public List<Role> findRoleAll() {
        List<Role> roleList = roleMapper.findRoleAndPermissionList();
        return roleList;
    }

    /**
     * 查询所有权限的信息显示在new页面中作为显示
     *
     * @return
     */
    @Override
    public List<Permission> findPermissionAll() {
        // 创建一个新的List集合用于存放递归后的权限信息
        List<Permission> endList = new ArrayList<>();

        // TODO 没写完
        PermissionExample permissionExample = new PermissionExample();
        // 存在的是乱序的permission的集合
        List<Permission> permissionList = permissionMapper.selectByExample(permissionExample);
        // 本类中创建一个方法用于递归排列permission值(传入空集合,乱序集合,顶级Id)
        treeList(permissionList, endList, 0);
        return endList;
    }

    /**
     * 将查询数据库的角色列表转换为树形集合结果
     * @param sourceList 数据库查询出的集合
     * @param endList 转换结束的结果集合
     * @param parentId 父ID
     */
    private void treeList(List<Permission> sourceList, List<Permission> endList, int parentId) {
        List<Permission> tempList = Lists.newArrayList(Collections2.filter(sourceList, new Predicate<Permission>() {
            @Override
            public boolean apply(Permission permission) {
                return permission.getPid().equals(parentId);
            }
        }));

        for(Permission permission : tempList) {
            endList.add(permission);
            treeList(sourceList,endList,permission.getId());
        }
    }


    /**
     * 保存角色和权限信息
     * @param role       角色对象
     * @param permissionId 权限对象
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void save(Role role, Integer[] permissionId) {
        // 保存角色
        roleMapper.insert(role);
        // 保存多个权限到角色的关联关系表中
        for(Integer permission : permissionId){
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(role.getId());
            rolePermission.setPermissionId(permission);

            rolePermissionMapper.insert(rolePermission);
        }
    }

    /**
     * 删除权限类型,查看是否在使用
     * @param id
     * @return
     */
    @Override
    public void delPermission(Integer id) {
        // 查询权限id是否存在
        Permission permission = permissionMapper.selectByPrimaryKey(id);
        if(permission != null){
            // 查询此权限id是否下面有子权限使用
            PermissionExample permissionExample = new PermissionExample();
            permissionExample.createCriteria().andPidEqualTo(id);
            List<Permission> permissionList = permissionMapper.selectByExample(permissionExample);
            // 判断此权限id是否有相同的权限pid,如果相同表示有子权限,则不能删除
            // 一定要看清楚判断方法,数组默认值为0,不可能又等于null又等于0,只能取反判断!!!
            if(permissionList != null && permissionList.size() > 0){
                throw new ServiceException("此权限下有子权限使用,不能删除");
            } else {
                // 查询此权限id是否被其他角色使用
                RolePermissionExample rolePermissionExample = new RolePermissionExample();
                rolePermissionExample.createCriteria().andPermissionIdEqualTo(id);
                List<RolePermission> rolePermissionList = rolePermissionMapper.selectByExample(rolePermissionExample);
                // 判断此类型id是否被其他使用
                if(rolePermissionList != null && rolePermissionList.size() > 0){
                    throw new ServiceException("此权限有其他角色使用,不能删除");
                } else {
                    permissionMapper.deleteByPrimaryKey(id);
                }
            }
        } else {
            throw new ServiceException("此类型不存在");
        }
    }

    /**
     * 删除角色信息
     * @param roleId 需要删除的角色的id
     */
    @Override
    public void delRole(Integer roleId) {
        // 查询此角色id是否存在
        Role role = roleMapper.selectByPrimaryKey(roleId);
        // 如果为null则代表不存在, 抛出异常

        System.out.println("角色是否存在:"+role);

        if(role != null){
            // 是否有其他使用此角色(角色和账号的关联关系表)
            EmployeeRoleExample employeeRoleExample = new EmployeeRoleExample();
            employeeRoleExample.createCriteria().andRoleIdEqualTo(roleId);
            List<EmployeeRole> employeeRolesList = employeeRoleMapper.selectByExample(employeeRoleExample);

            System.out.println("是否存在关联关系表中:"+employeeRolesList);

            // 如果查询结果是null或者为空,则表示没有账号引用此角色:可以删除
            if(employeeRolesList != null && !employeeRolesList.isEmpty()){
                throw new ServiceException("此角色信息正在被使用,不能删除");
            } else {
                // 删除角色信息
                roleMapper.deleteByPrimaryKey(roleId);
                // 删除角色与权限的关联关系表.
                RolePermissionExample rolePermissionExample = new RolePermissionExample();
                rolePermissionExample.createCriteria().andRoleIdEqualTo(roleId);
                rolePermissionMapper.deleteByExample(rolePermissionExample);
            }
        } else {
            throw new ServiceException("此角色类型不存在");
        }
    }

    /**
     * 根据roleId查询role
     * @return
     */
    @Override
    public Role findRoleById(Integer id) {
        // 获得role对象
        Role role = roleMapper.findRoleAndPermissions(id);
        return role;
    }

    /**
     * 根据已选中的权限返回一个permissiin的Map集合
     * @param permissionList 已选中的permission集合
     * @return Map集合中, 未选中的权限值为false, 已选中的权限值为true
     */
    @Override
    public Map<Permission, Boolean> findPermissionWithBoolean(List<Permission> permissionList) {
        // 先查询所有的permission
        PermissionExample permissionExample = new PermissionExample();
        List<Permission> permissionListEdit = permissionMapper.selectByExample(permissionExample);
        // 创建一个Map集合存仓每个权限信息的boolean值 : 有序的Map集合,LinkedHashMap
        Map<Permission, Boolean> permissionMap = new LinkedHashMap<>();

        for(Permission all : permissionListEdit){
            // 制定一个标志位
            boolean flag = false;
            for(Permission click : permissionList){
                // 判断如果已选中的与全部的id相同时,改变此权限的状态为true
                if(all.getId().equals(click.getId())){
                    flag = true;
                    break;
                }
            }
            // 将所有permission放入Map集合中
            permissionMap.put(all, flag);
        }
        return permissionMap;
    }

    /**
     * 根据获得的role信息修改当前角色信息
     * @param roleEdit
     * @return
     */
    @Override
    public void editRole(Role roleEdit) {
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andIdEqualTo(roleEdit.getId());
        roleExample.createCriteria().andRoleNameEqualTo(roleEdit.getRoleName());
        roleExample.createCriteria().andRoleCodeEqualTo(roleEdit.getRoleCode());
        Integer count = roleMapper.updateByExampleSelective(roleEdit, roleExample);
    }

    /**
     * 根据roleId删除原有的权限列表
     * @param roleEdit
     */
    @Override
    public void delPermissionByRoleId(Role roleEdit) {
        RolePermissionExample rolePermissionExample = new RolePermissionExample();
        rolePermissionExample.createCriteria().andRoleIdEqualTo(roleEdit.getId());
        rolePermissionMapper.deleteByExample(rolePermissionExample);
    }

    /**
     * 跟新角色的权限信息
     * @param permissionId
     */
    @Override
    public void updetePermissionMap(Role roleEdit , Integer[] permissionId) {
        for(Integer id : permissionId){
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleEdit.getId());
            rolePermission.setPermissionId(id);
            rolePermissionMapper.insert(rolePermission);
        }
    }

    /**
     * 根据employeeId查找选中的角色列表
     * @return
     */
    @Override
    public List<Role> findRoleByEmployeeId(Integer id) {
        List<Role> roleList = roleMapper.findRoleListByEmployeeId();
        return roleList;
    }

    /**
     * 根据roleId(角色)查找PermissionList(权限)
     * @param id 角色的ID值
     * @return 权限列表
     */
    @Override
    public List<Permission> findPermissionByRoleId(Integer id) {
        List<Permission> permissionList = rolePermissionMapper.findPermissionByRoleId(id);
        return permissionList;
    }

    /**
     * 新增权限
     *
     * @param permission
     */
    @Override
    public void savePermission(Permission permission) {
        permissionMapper.insert(permission);
    }

    /**
     * 根据PermissionId查找Permission对象
     * @param id
     * @return
     */
    @Override
    public Permission findPermissionById(Integer id) {
        Permission permission = permissionMapper.selectByPrimaryKey(id);
        return permission;
    }

    /**
     * 修改permission
     *
     * @param permission
     */
    @Override
    public void updetePermission(Permission permission) {
        permissionMapper.updateByPrimaryKeySelective(permission);
    }

    /**
     * 根据类型查找Permission集合
     * @param permissionTypeMenu
     * @return
     */
    @Override
    public List<Permission> findPermissionTypeById(String permissionTypeMenu) {
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.createCriteria().andPermissionTypeEqualTo(permissionTypeMenu);
        List<Permission> permissionList = permissionMapper.selectByExample(permissionExample);
        return permissionList;
    }


}
