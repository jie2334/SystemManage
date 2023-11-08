package com.jiejie.menu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiejie.menu.entity.SysMenu;
import com.jiejie.menu.mapper.MenuMapper;
import com.jiejie.menu.service.MenuService;
import com.jiejie.menu.utils.MenuHelper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单表(SysMenu)表服务实现类
 *
 * @author makejava
 * @since 2023-10-29 16:25:29
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, SysMenu> implements MenuService {
    @Resource
    private MenuMapper menuMapper;


    @Override
    public List<SysMenu> findNodes() {

        List<SysMenu> sysMenuList = menuMapper.selectList(null);

        List<SysMenu> resultList = MenuHelper.buildTree(sysMenuList);
        return resultList;
    }

    @Override
    public void removeMenuById(Long id) {
        //判断当前菜单是否有下一层菜单
        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
        //解释SysMenu::getParentId起到了限定查询范围的作用，将id值作为比较条件，再该范围内比较查询
        wrapper.eq(SysMenu::getParentId,id);
        Long count = menuMapper.selectCount(wrapper);
        if(count > 0) {
            throw new RuntimeException("菜单不能删除");
        }
        menuMapper.deleteById(id);
    }
}
