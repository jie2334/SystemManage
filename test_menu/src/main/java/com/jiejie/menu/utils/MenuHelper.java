package com.jiejie.menu.utils;

import com.jiejie.menu.entity.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @description: TODO
 * @author: 22783
 * @date: 2023/10/29
 **/
public class MenuHelper {
    //菜单列表接口,用于获取菜单信息
    //使用递归方法建菜单
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        //创建list集合，用于返回最终数据
        List<SysMenu> trees = new ArrayList<>();
        //把所有菜单数据进行遍历
        for(SysMenu sysMenu:sysMenuList) {
            //递归入口进入
            //parentId=0是入口（父亲id=0即程序递归入口）
            if(sysMenu.getParentId().longValue()==0) {
                //向树里面添加该节点的子结点一直遍历
                trees.add(getChildren(sysMenu,sysMenuList));
            }
        }
        return trees;
    }

    public static SysMenu getChildren(SysMenu sysMenu,
                                      List<SysMenu> sysMenuList) {

        //父亲节点初始化 children 属性为空的 ArrayList 对象是为了确保递归过程中能
        //一个空的集合。避免可能的空指针异常
        sysMenu.setChildren(new ArrayList<>());

        //遍历所有菜单数据，判断 id 和 parentId对应关系
        for(SysMenu it: sysMenuList) {
            //如果本对象的id和遍历到的对象的id一直，就证明遍历到的这个对象是本对象的孩子，可进入继续递归
            if(sysMenu.getId().longValue() == it.getParentId().longValue()) {

                // 而是一个空的集合。避免可能的空指针异常
                if (sysMenu.getChildren() == null) {
                    sysMenu.setChildren(new ArrayList<>());
                }
                //找到孩子就放到sysMenu的一个叫做children的属性中（这个children是个list集合）
                sysMenu.getChildren().add(getChildren(it,sysMenuList));
            }
        }
//        封装返回
        return sysMenu;
    }

}
