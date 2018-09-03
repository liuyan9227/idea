package com.kaishengit.tms.shiro;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.service.RolePermissionService;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuyan
 * @date 2018/8/31
 */
public class CustomerFilterChainDefinition {

    private Logger logger = LoggerFactory.getLogger(CustomerFilterChainDefinition.class);

    @Reference(version = "1.0")
    private RolePermissionService rolePermissionService;

    private Map<String,String> filterChainDefinitions;
    private AbstractShiroFilter shiroFilter;


    /**
     * 在spring容器启动时,config会调用set方法将值赋值给此对象,包含config所有规则
     */
    public void setFilterChainDefinitions(Map<String, String> filterChainDefinitions) {
        this.filterChainDefinitions = filterChainDefinitions;
    }

    /**
     * 设置shiro规则到对象中
     */
    public void  setShiroFilter(AbstractShiroFilter shiroFilter) {
        this.shiroFilter = shiroFilter;
    }

    /**
     * Spring容器启动时调用
     */
    @PostConstruct
    public synchronized void init() {
        logger.info("------初始化URL权限-----------");
        //清除原有的URL权限
        getFilterChainManager().getFilterChains().clear();
        //加载现有的URL权限
        load();
        logger.info("------初始化URL权限结束-----------");
    }

    /**
     * 重新加载URL权限
     */
    public synchronized void updateUrlPermission() {
        logger.info("------刷新URL权限-----------");
        //清除原有的URL权限
        getFilterChainManager().getFilterChains().clear();
        //加载现有的URL权限
        load();
        logger.info("------刷新URL权限结束-----------");
    }

    /**
     * 加载URL和权限的对应关系
     */
    public synchronized void load() {
        // 1.创建有序Map
        LinkedHashMap<String,String> urlMap = new LinkedHashMap<>();
        // 2.加载静态资源,合并map集合
        urlMap.putAll(filterChainDefinitions);

        //从数据库中查找所有的权限对象
        List<Permission> permissionList = rolePermissionService.findAllPermission();

        for(Permission permission : permissionList) {
            // 3.将所有权限设置成键值对,并合并到有序map中
            urlMap.put(permission.getUrl(),"perms["+permission.getPermissionCode()+"]");
        }
        // 4.将末尾权限放入有序map中
        urlMap.put("/**","user");

        //URL和权限的关系设置到shiroFilter中
        DefaultFilterChainManager defaultFilterChainManager = getFilterChainManager();
        for(Map.Entry<String,String> entry : urlMap.entrySet()) {
            defaultFilterChainManager.createChain(entry.getKey(),entry.getValue());
        }
    }

    /**
     * shiro管理器对象
     */
    private DefaultFilterChainManager getFilterChainManager() {
        PathMatchingFilterChainResolver pathMatchingFilterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
        DefaultFilterChainManager defaultFilterChainManager = (DefaultFilterChainManager) pathMatchingFilterChainResolver.getFilterChainManager();
        return defaultFilterChainManager;
    }



}
