package com.kaishengit.erp.mapper;

import com.kaishengit.erp.entity.Role;
import com.kaishengit.erp.entity.RoleExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> findRoleAndPermissionList();

    Role findRoleAndPermissions(Integer id);

    List<Role> findRoleListByEmployeeId();
}