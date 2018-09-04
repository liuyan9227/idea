package com.kaishengit.tms.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.kaishengit.tms.entity.*;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.mapper.AccountRolesMapper;
import com.kaishengit.tms.mapper.PermissionMapper;
import com.kaishengit.tms.mapper.RolesMapper;
import com.kaishengit.tms.mapper.RolesPermissionMapper;
import com.kaishengit.tms.service.RolePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 角色和权限业务类
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
    @Autowired
    private AccountRolesMapper accountRolesMapper;
    @Autowired
    private RolesPermissionMapper rolesPermissionMapper;

    private Account account;


    /**
     * 传输登录人员信息
     *
     * @param map 登录人员
     */
    @Override
    public void transAccount(Map<String, Object> map) {
        account = (Account) map.get("account");
    }

    /**
     * 查询所有权限
     */
    @Override
    public List<Permission> findAllPermission() {
        PermissionExample permissionExample = new PermissionExample();
        List<Permission> permissionList = permissionMapper.selectByExample(permissionExample);
        List<Permission> resultList = new ArrayList<>();
        treeList(permissionList,resultList,0);
        return resultList;
    }


    /**
     * 将查询数据库的角色列表转换为树形集合结果
     * @param sourceList 数据库查询出的集合
     * @param endList 转换结束的结果集合
     * @param parentId 父ID
     */
    private void treeList(List<Permission> sourceList, List<Permission> endList, int parentId) {
        List<Permission> tempList = Lists.newArrayList(Collections2.filter(sourceList, permission -> permission.getParentId().equals(parentId)));

        for(Permission permission : tempList) {
            endList.add(permission);
            treeList(sourceList,endList,permission.getId());
        }
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

    /**
     * (角色管理:首页)显示所有角色信息和对应的权限信息
     */
    @Override
    public List<Roles> findAllRolesWithPermission() {
        List<Roles> rolesList = rolesMapper.findAllRolesWithPermissionList();
        return rolesList;
    }




    /**
     * (角色新增信息)
     * 1.使用事物 : @transactional 作用与整个方法,不需要try
     * 2.保存角色信息
     * 3.保存角色与权限的关联关系
     * 4.记录日志
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveRoles(Roles roles, Integer[] permissionId) {
        roles.setCreateTime(new Date());
        rolesMapper.insertSelective(roles);

        if(permissionId != null){
            for(Integer perId : permissionId){
                RolesPermissionKey rolesPermissionKey = new RolesPermissionKey();
                rolesPermissionKey.setRolesId(roles.getId());
                rolesPermissionKey.setPermissionId(perId);

                rolesPermissionMapper.insert(rolesPermissionKey);
            }
        }
        logger.info("{} 在 {} 添加了一条角色信息 {}", account.getAccountName(), new Date(), roles);
    }




    /**
     * 删除角色:
     * 1.删除角色不能有人员使用此角色(检查关联关系表)
     * 2.删除角色信息
     * 3.删除角色需要解除角色与权限的关联关系
     * 4.记录日志
     */
    @Override
    public void delRolesById(Integer id) {
        AccountRolesExample accountRolesExample = new AccountRolesExample();
        accountRolesExample.createCriteria().andRolesIdEqualTo(id);

        List<AccountRolesKey> keyList = accountRolesMapper.selectByExample(accountRolesExample);
        if(!keyList.isEmpty()){
            throw new ServiceException("该角色已被占用");
        }

        rolesMapper.deleteByPrimaryKey(id);

        RolesPermissionExample rolesPermissionExample = new RolesPermissionExample();
        rolesPermissionExample.createCriteria().andRolesIdEqualTo(id);
        rolesPermissionMapper.deleteByExample(rolesPermissionExample);

        logger.info("{} 在 {} 删除了一条角色信息", account.getAccountName(), new Date());
    }



    /**
     * (修改角色)查询角色和相应权限根据角色ID,用于显示
     */
    @Override
    public Roles findRolesWithPermissionByRoleId(Integer id) {
        Roles roles = rolesMapper.findRolesWithPermissionListByRoleId(id);
        return roles;
    }

    /**
     * (角色修改)提交表单
     * 1.事务
     * 2.日志
     * 3.删除原来角色与权限的关联关系,新增关联关系
     * 4.删除原来的角色信息,新增角色
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateRoles(Roles roles, Integer[] permissionId) {
        RolesPermissionExample rolesPermissionExample = new RolesPermissionExample();
        rolesPermissionExample.createCriteria().andRolesIdEqualTo(roles.getId());

        rolesPermissionMapper.deleteByExample(rolesPermissionExample);

        for(Integer perId : permissionId) {
            RolesPermissionKey rolesPermissionKey = new RolesPermissionKey();
            rolesPermissionKey.setRolesId(roles.getId());
            rolesPermissionKey.setPermissionId(perId);
            rolesPermissionMapper.insert(rolesPermissionKey);
        }

        rolesMapper.updateByPrimaryKeySelective(roles);

        logger.info("{} 在 {} 修改了角色信息 {}",account.getAccountName(), new Date(), roles);
    }


    // ----------------------------------------------------------------------------------


    /**
     * (新增权限回显)
     */
    @Override
    public List<Permission> findPermissionByPermissionType(String menuType) {
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.createCriteria().andPermissionTypeEqualTo(menuType);

        return permissionMapper.selectByExample(permissionExample);
    }

    /**
     * 新增权限
     * 1.日志
     */
    @Override
    public void savePermission(Permission permission) {
        permission.setCreateTime(new Date());
        permissionMapper.insertSelective(permission);
        logger.info("{} 在 {} 添加权限 {}", account.getAccountName(), new Date(), permission);
    }


    /**
     * 删除权限
     * 1.日志
     * 2.如果权限被使用则不能被删除
     */
    @Override
    public void delPermissionById(Integer id)throws ServiceException {
        //查询该权限是否有子节点
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.createCriteria().andParentIdEqualTo(id);

        List<Permission> permissionList = permissionMapper.selectByExample(permissionExample);
        if(permissionList != null && !permissionList.isEmpty()) {
            throw new ServiceException("该权限下有子节点，删除失败");
        }

        //查询权限是否被角色使用
        RolesPermissionExample rolesPermissionExample = new RolesPermissionExample();
        rolesPermissionExample.createCriteria().andPermissionIdEqualTo(id);

        List<RolesPermissionKey> rolesPermissionKeyList = rolesPermissionMapper.selectByExample(rolesPermissionExample);
        if(rolesPermissionKeyList != null && !rolesPermissionKeyList.isEmpty()) {
            throw new ServiceException("该权限被角色引用，删除失败");
        }
        //如果没有被使用，则可以直接删除
        Permission permission = permissionMapper.selectByPrimaryKey(id);
        permissionMapper.deleteByPrimaryKey(id);
        logger.info("{} 在 {} 删除权限 {}",account.getAccountName(), new Date(), permission);
    }

    /**
     * 修改权限->查询权限根据permissionId
     */
    @Override
    public Permission findPermissionById(Integer id) {
        Permission permission = permissionMapper.selectByPrimaryKey(id);
        return permission;
    }

    /**
     * 更新权限,提交表单
     */
    @Override
    public void editPermission(Permission permission) {
        permission.setUpdateTime(new Date());
        permissionMapper.updateByPrimaryKeySelective(permission);
        logger.info("{} 在 {} 更新权限 {}",account.getAccountName(), new Date(), permission);
    }


    /**
     * 查询所有角色信息
     */
    @Override
    public List<Roles> findAllRoles() {
        List<Roles> rolesList = rolesMapper.selectByExample(new RolesExample());
        return rolesList;
    }
}
