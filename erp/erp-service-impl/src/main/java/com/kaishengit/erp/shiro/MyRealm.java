package com.kaishengit.erp.shiro;

import com.kaishengit.erp.entity.Employee;
import com.kaishengit.erp.entity.EmployeeLoginLog;
import com.kaishengit.erp.service.EmployeeService;
import com.kaishengit.erp.utils.Constant;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author liuyan
 * @date 2018/7/30
 */
public class MyRealm extends AuthorizingRealm {

    Logger logger = LoggerFactory.getLogger(MyRealm.class);

    @Autowired
    private EmployeeService employeeService;

    /**
     * 判断角色和权限的授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 判断登录
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //  从controller的usernamePasswordToken对象传入,用usernamePasswordToken接收需要强制类型转换为usernamePasswordToken对象
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        // 获取token中的帐号(此项目为电话号码登录)
        String userTel = usernamePasswordToken.getUsername();
        // 数据库中查找是否有该账号存在,根据规范只能用service对象进行查询
        Employee employee = employeeService.findEmployeeByTel(userTel);
        // 判断employee是否存在
        if(employee != null){
            // 判断状态是否正常
            if(employee.getState().equals(Constant.STATE_NORMAL)){
                // 登陆成功,获取帐号IP作为记录日志
                String loginIp = usernamePasswordToken.getHost();
                // 记录日志
                EmployeeLoginLog employeeLoginLog = new EmployeeLoginLog();
                employeeLoginLog.setLoginIp(loginIp);
                employeeLoginLog.setLoginTime(new Date());
                employeeLoginLog.setEmployeeId(employee.getId());
                // 保存到数据库
                employeeService.saveLoginLog(employeeLoginLog);
                // log4j在控制台打印日志
                logger.info("{}-{} 在 {} 登录了系统", employee.getEmployeeName(),employee.getEmployeeTel(),new Date());
                // TODO 这里不明白
                return new SimpleAuthenticationInfo(employee, employee.getPassword(),getName());
            } else {
                throw new LockedAccountException("账户已被冻结");
            }
        } else {
            throw new UnknownAccountException("帐号或密码错误");
        }
    }



}
