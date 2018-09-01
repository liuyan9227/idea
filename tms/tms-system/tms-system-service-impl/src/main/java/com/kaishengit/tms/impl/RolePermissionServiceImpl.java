package com.kaishengit.tms.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.entity.PermissionExample;
import com.kaishengit.tms.entity.Roles;
import com.kaishengit.tms.mapper.PermissionMapper;
import com.kaishengit.tms.mapper.RolesMapper;
import com.kaishengit.tms.service.RolePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * @author liuyan
 * @date 2018/8/31
 */
@Service(version = "1.0", timeout = 5000)
public class RolePermissionServiceImpl implements RolePermissionService {

    public static final Logger logger = LoggerFactory.getLogger(RolePermissionServiceImpl.class);

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolesMapper rolesMapper;


    /**
     * 查询所有权限
     */
    @Override
    public List<Permission> findAllPermission() {
        PermissionExample permissionExample = new PermissionExample();
        List<Permission> permissionList = permissionMapper.selectByExample(permissionExample);
        return permissionList;
    }

    /**
     * 查询用户角色,根据用户ID
     * account_roles,roles多表联查
     */
    @Override
    public List<Roles> findRolesByAccountId(Integer id) {
        // 在xml中添加节点用于多表联查
        List<Roles> rolesList = rolesMapper.findRolesByAccountId(id);
        return rolesList;
    }

    /**
     * 查询用户拥有角色的权限,根据角色id查找
     * roles_permission,permission多表联查
     */
    @Override
    public List<Permission> findPermissionByRolesId(Integer id) {
        List<Permission> permissionList = permissionMapper.findPermissionByRolesId(id);
        return permissionList;
    }


}
