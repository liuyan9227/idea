package com.kaishengit.erp.service;

import com.kaishengit.erp.entity.Employee;
import com.kaishengit.erp.entity.EmployeeLoginLog;

import java.util.List;
import java.util.Map;

/**
 * @author liuyan
 * @date 2018/7/26
 */
public interface EmployeeService {
    /**
     * 登录页面根据电话和密码查找是否存在
     * @param userTel 用户电话
     * @param password 用户密码(采用md5Hex加密)
     * @return 对象用作前端显示
     */
    Employee findTelAndPassword(String userTel, String password, String ip);

    /**
     * 查找所有员工信息
     * @return 员工列表
     */
    List<Employee> findAll();

    /**
     * 保存账户新增页面信息
     * @param employee 新增员工信息
     * @param roleIds 新增员工的角色信息
     */
    void save(Employee employee, Integer[] roleIds);

    /**
     * 根据员工ID查询员工的信息和员工的角色列表
     * @param id
     * @return
     */
    List<Employee> findEmployeeAndRoles(Integer id);

    /**
     * 回显员工信息
     * @param id
     * @return
     */
    Employee findEmployee(Integer id);

    /**
     * 根据Tel查找对象
     * @return
     */
    Employee findEmployeeByTel(String userTel);

    /**
     * 保存记录日志
     * @param employeeLoginLog
     */
    void saveLoginLog(EmployeeLoginLog employeeLoginLog);

    /**
     * 更新员工信息
     * @param employee
     * @param roleIds
     */
    void edit(Employee employee, Integer[] roleIds);

    /**
     * 删除员工信息,和所有的员工的关联关系表
     * @param id
     */
    void del(Integer id);

    /**
     * 查询符合查询条件的员工信息
     * @param p 分页起始页
     * @param param 需要查询满足的条件
     * @return 返回符合模糊查询的员工信息
     */
    List<Employee> findEmployeeByLike(Integer p, Map<String,Object> param);
}
