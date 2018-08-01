package com.kaishengit.erp.shiro;


import com.kaishengit.erp.entity.Permission;
import com.kaishengit.erp.service.RolePermissionService;
import org.apache.shiro.config.Ini;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 实现自FactoryBean动态产生IniSection对象
 * @author liuyan
 * @date 2018/7/31
*/

public class CustomerFilterChainDefinition implements FactoryBean<Ini.Section> {

    private String filterChainDefinitions;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    public Ini.Section getObject() throws Exception {
        Ini ini = new Ini();
        // 加载xml中的filterChainDefinitions节点的所有(固定的)值,加载进ini对象中
        ini.load(filterChainDefinitions);
        // section为Map集合,ini也位Map集合
        Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        // 获取所有的权限用作与动态添加/manage/employee/new = perms[employee:add]
        List<Permission> permissionList = rolePermissionService.findPermissionAll();
        for(Permission permission : permissionList){
            // 访问路径 = perms[用户:权限]
            section.put(permission.getUrl(), "perms[" + permission.getPermissionCode() + "]");
        }
        section.put("/**","user");
        return section;
    }

    @Override
    public Class<?> getObjectType() {
        return Ini.Section.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    /**
     * 在spring-shiro.xml的CustomerFilterChainDefinition节点中有filterChainDefinitions属性,添加此属性,并提供set方法
     * @param filterChainDefinitions
     */
    public void setFilterChainDefinitions(String filterChainDefinitions){
        this.filterChainDefinitions = filterChainDefinitions;
    }
}
