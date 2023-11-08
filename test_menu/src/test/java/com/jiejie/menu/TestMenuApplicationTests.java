package com.jiejie.menu;

import com.jiejie.menu.entity.SysMenu;
import com.jiejie.menu.mapper.MenuMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class TestMenuApplicationTests {

    @Resource
    private MenuMapper menuMapper;


    @Test
    void contextLoads() {

        System.out.println("test开始！~~~~");
        //List<SysMenu> menuList = menuMapper.getMenuList();
        System.out.println("menuList = " + menuList);
        //List<Long> allMenuId = menuMapper.getAllMenuId();
        System.out.println("allMenuId = " + allMenuId);

    }

    @Test
    void testMapper() {
        /*
         * 先获取id集合，遍历id集合----获取每一个id集的parent_id
         *
         * */
        //List<SysMenu> menuList = menuMapper.getMenuList();

        System.out.println("menuList = " + menuList);
        List<SysMenu> menus = buildMenuTree(menuList);

        System.out.println("menus = " + menus);
    }

    private List<SysMenu> buildMenuTree(List<SysMenu> menus) {

        List<SysMenu> returnList = new ArrayList<>();


        List<Long> tempList = menus.stream().map(SysMenu::getMenuId).collect(Collectors.toList());

        for (Iterator<SysMenu> iterator = menus.iterator(); iterator.hasNext(); ) {
            SysMenu next = iterator.next();
            if (!tempList.contains(next.getParentId())) {
                //开始遍历
                recursionFn(menus, next);
                returnList.add(next);
            }
        }

        if (returnList.isEmpty()) {
            returnList = menus;
        }
        return returnList;
    }

    //构建层级关系
    private void recursionFn(List<SysMenu> menus, SysMenu next) {

        //得到next的子结点
        List<SysMenu> childList = getChildList(menus, next);

        next.setChildren(childList);

        for (SysMenu menu : childList) {
            //如果子结点还有再找子结点的子结点
            if (hasChild(menus, menu)) {

                recursionFn(menus, menu);

            }
        }
    }

    private List<SysMenu> getChildList(List<SysMenu> menus, SysMenu next) {

        //获取next的子结点

        List<SysMenu> tlist = new ArrayList<SysMenu>();


        for (SysMenu nexted : menus) {
            if (nexted.getParentId().longValue() == (next.getMenuId())) {
                tlist.add(nexted);
            }
        }

        return tlist;
    }


    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysMenu> list, SysMenu t) {
        return getChildList(list, t).size() > 0;
    }
}
