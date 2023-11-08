package com.jiejie.menu.controller;


import com.jiejie.menu.entity.SysMenu;
import com.jiejie.menu.result.Result;
import com.jiejie.menu.service.MenuService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单表(SysMenu)表控制层
 *
 * @author makejava
 * @since 2023-10-29 16:25:24
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    /**
     * 服务对象
     */
    @Resource
    private MenuService menuService;


    //菜单列表接口,用于获取菜单信息
    @GetMapping("/findNodes")
    public Result findNodes() {

        List<SysMenu> list = menuService.findNodes();
        return Result.success(list);
    }

    //新增菜单   38
    @PostMapping("save")
    public Result save(@RequestBody SysMenu sysMenu) {
        menuService.save(sysMenu);
        return Result.success();
    }

    //修改菜单
    @PutMapping("update")
    public Result updateById(@RequestBody SysMenu sysMenu) {
        menuService.updateById(sysMenu);
        return Result.success();
    }


    //完善删除菜单
    @DeleteMapping("remove/{id}")
    public Result removeCompletely(@PathVariable Long id) {
        menuService.removeMenuById(id);
        return Result.success();
    }

}

