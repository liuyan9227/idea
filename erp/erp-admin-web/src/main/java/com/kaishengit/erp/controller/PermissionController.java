package com.kaishengit.erp.controller;

import com.google.common.collect.Lists;
import com.kaishengit.erp.controller.exceptionHandler.NotFoundException;
import com.kaishengit.erp.dto.ResponseBean;
import com.kaishengit.erp.entity.Permission;
import com.kaishengit.erp.exception.ServiceException;
import com.kaishengit.erp.service.RolePermissionService;
import com.kaishengit.erp.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Get方法作为--页面跳转作用,需要--回显数据,返回--jsp页面路径
 * Post方法作为表单--提交作用,需要大部分用于业务处理,并且返回的是重定向请求,防止表单重复提交
 * 如果排序出现乱序要采用递归排序处理,来自于guava技术支持
 * 在删除业务中,主要的是 判断 是否还有其他使用此项,判断条件要精确,不能出现又是null又是空的判断,切记
 * @author liuyan
 * @date 2018/8/1
*/

@Controller
@RequestMapping("/manage/permission")
public class PermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;

    @GetMapping
    public String home(Model model){
        // TODO 重点(递归排序)
        // 主页显示所有权限,roleList信息应该存在permission对象中进行传值
        List<Permission> permissionList = rolePermissionService.findPermissionAll();
        model.addAttribute("permissionList", permissionList);
        return "manage/permission/home";
    }

    @GetMapping("/new")
    public String save(Model model){
        // 在新增页面<checkbox>显示所有权限
        List<Permission> menuPermissionList = rolePermissionService.findPermissionAll();

        model.addAttribute("menuPermissionList", menuPermissionList);
        return "manage/permission/new";
    }

    @PostMapping("/new")
    public String savePost(Permission permission){
        // 将新增信息保存到数据库
        rolePermissionService.savePermission(permission);
        return "redirect:/manage/permission";
    }

    @ResponseBody
    @GetMapping("/{id:\\d+}/del")
    public ResponseBean del(@PathVariable Integer id){
        // TODO 路径中获取的值必须加上注解!!!在删除的判断中注意判断条件
        try {
            rolePermissionService.delPermission(id);
        } catch (ServiceException e){
            return ResponseBean.error(e.getMessage());
        }
            return ResponseBean.success();
    }

    @GetMapping("{id:\\d+}/edit")
    public String edit(@PathVariable Integer id, Model model){
        // 修改要回显权限信息给前端页面
        Permission permission = rolePermissionService.findPermissionById(id);
        if(permission == null){
            throw new NotFoundException();
        }
        // 根据所选按钮回显所有权限集合
        List<Permission> menuPermissionList = rolePermissionService.findPermissionTypeById(Constant.PERMISSION_TYPE_MENU);
        // 排除当前Permission对象及其子类
        remove(menuPermissionList, permission);
        model.addAttribute("permission", permission);
        model.addAttribute("menuPermissionList", menuPermissionList);
        return "manage/permission/edit";
    }

    /**
     * 递归删除所有子权限
     * 把(权限列表,目标对象)传入,for循环(权限集合)查找跟对象的子权限,如果查到,将子权限作为对象重新调用此方法,循环查找子权限的子权限,如果没有查到则跳出
     * for循环,将现在的对象用remove方法移除掉,如果上次的for循环还没有做完接着执行上次的for循环,直到最后一次移除本身的对象在结束
     * 递归删除:首先移除的是第一个属性的子类的子类,直到第一个子类移除完成,进入第二个子类,以此类推,最后在移除本身
     */
    private void remove(List<Permission> menuPermissionList, Permission permission){
        // 通过临时变量来存储所有的list元素防止漏删
        List<Permission> temp = Lists.newArrayList(menuPermissionList);
        for(int i = 0; i < temp.size(); i++) {
            // 判断有没有子权限要去除
            if(temp.get(i).getPid().equals(permission.getId())) {
                remove(menuPermissionList,temp.get(i));
            }
        }
        // 去除当前权限(List的remove方法:)
        menuPermissionList.remove(permission);
    }

    @PostMapping("{id:\\d+}/edit")
    public String editPost(Permission permission){
        rolePermissionService.updetePermission(permission);
        return "redirect:/manage/permission";
    }
}
