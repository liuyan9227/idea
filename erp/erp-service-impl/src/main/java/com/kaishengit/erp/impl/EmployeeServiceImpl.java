package com.kaishengit.erp.impl;

import com.kaishengit.erp.entity.Employee;
import com.kaishengit.erp.entity.EmployeeExample;
import com.kaishengit.erp.entity.EmployeeLoginLog;
import com.kaishengit.erp.entity.EmployeeRole;
import com.kaishengit.erp.exception.ServiceException;
import com.kaishengit.erp.mapper.EmployeeLoginLogMapper;
import com.kaishengit.erp.mapper.EmployeeMapper;
import com.kaishengit.erp.mapper.EmployeeRoleMapper;
import com.kaishengit.erp.service.EmployeeService;
import com.kaishengit.erp.utils.Constant;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author liuyan
 * @date 2018/7/26
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeLoginLogMapper employeeLoginLogMapper;

    @Autowired
    private EmployeeRoleMapper employeeRoleMapper;


    /**
     * 登录页面根据电话和密码查找是否存在
     * @param userTel  用户电话
     * @param password 用户密码(采用md5Hex加密)
     * @return 对象用作前端显示
     */
    @Override
    public Employee findTelAndPassword(String userTel, String password, String ip) {
        // 根据电话查找返回对象
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andEmployeeTelEqualTo(userTel);

        List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);

        // 判断对象是否存在
        if(employeeList != null && employeeList.size() > 0){
            // List集合的根据下标获取元素
            Employee employee = employeeList.get(0);
            // 判断密码是否相同(加密/commons-codec)
            String passwordMd5 = DigestUtils.md5Hex(password);

            if(passwordMd5.equals(employee.getPassword())){
                // 判断状态是否正常 0:禁用 1:正常
                if(Constant.STATE_NORMAL.equals(employee.getState())){
                    // 记录日志
                    EmployeeLoginLog employeeLoginLog = new EmployeeLoginLog();
                    employeeLoginLog.setLoginIp(ip);
                    employeeLoginLog.setLoginTime(new Date());
                    employeeLoginLog.setEmployeeId(employee.getId());
                    // 将登录日志存入数据库
                    employeeLoginLogMapper.insertSelective(employeeLoginLog);
                    // 系统记录日志
                    logger.info("{}-{}在{}登陆了系统", employee.getEmployeeName(), employee.getEmployeeTel(), employee.getCreateTime());
                    // 返回对象
                    return employee;
                } else {
                    throw new ServiceException("账户异常,请联系管理员");
                }
            } else {
                throw new ServiceException("用户名或密码的错误");
            }
        } else {
            throw new ServiceException("用户名或密码的错误");
        }
    }

    /**
     * 查找所有员工信息
     * @return 员工列表
     */
    @Override
    public List<Employee> findAll() {
        EmployeeExample employeeExample = new EmployeeExample();
        List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);

        return employeeList;
    }

    /**
     * 保存账户新增页面信息
     * @param employee 新增员工信息
     * @param roleIds   新增员工的角色信息
     */
    @Override
    public void save(Employee employee, Integer[] roleIds) {
        // 保存新增员工信息, 密码需要md5加密
        String password = employee.getPassword();
        String passwordMd5 = DigestUtils.md5Hex(password);
        employee.setPassword(passwordMd5);
        employeeMapper.insertSelective(employee);
        // 保存员工的角色列表,添加关联关系表
        EmployeeRole employeeRole = new EmployeeRole();
        for(Integer id : roleIds){
            employeeRole.setRoleId(id);
            employeeRole.setEmployeeId(employee.getId());
            employeeRoleMapper.insertSelective(employeeRole);
        }
    }

    /**
     * 根据员工ID查询员工的信息和员工的角色列表
     *
     * @param id
     * @return
     */
    @Override
    public List<Employee> findEmployeeAndRoles(Integer id) {
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andIdEqualTo(id);
        List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);
        return employeeList;
    }

    /**
     * 回显员工信息
     *
     * @param id
     * @return
     */
    @Override
    public Employee findEmployee(Integer id) {
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }
}
