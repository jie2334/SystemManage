package com.jiejie.menu.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.jiejie.menu.entity.SysMenu;

import java.util.List;

/**
 * 菜单表(SysMenu)表服务接口
 *
 * @author makejava
 * @since 2023-10-29 16:25:28
 */
public interface MenuService extends IService<SysMenu> {

    //菜单列表接口,用于获取菜单信息   38
    List<SysMenu> findNodes();

    void removeMenuById(Long id);
}
