package com.example.springboot.junit;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lt
 * @date 2019/10/17
 */

@RunWith(SpringRunner.class)
public class RecursionTest {

    @Test
    public void test() {
        List<Menu> menus = setDate();
        // 一级节点
        List<Menu> menuTopList = menus.stream().filter(x -> x.parentId == 0).collect(Collectors.toList());

        for (Menu m : menuTopList) {
            setChild(m, menus);
        }

        System.out.print(menuTopList.stream().map(Menu::toString).collect(Collectors.joining()));

    }

    void setChild(Menu parent, List<Menu> allMenu) {

        List<Menu> allChild = allMenu.stream().filter(x -> x.parentId == parent.id).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(allChild)) {
            parent.setName(parent.getName() + "reference");
            parent.setChildList(allChild);
        }

        //
        allChild.forEach(x -> {
                    setChild(x, allMenu);
                }
        );


    }

    List<Menu> setDate() {

        List<Menu> menus = new ArrayList<>();

        // level 1
        Menu menu = new Menu("1", 1, 0, null);
        menus.add(menu);
        menu = new Menu("2", 2, 0, null);
        menus.add(menu);
        menu = new Menu("3", 3, 0, null);
        menus.add(menu);

        // level 2
        menu = new Menu("11", 11, 1, null);
        menus.add(menu);
        menu = new Menu("22", 22, 2, null);
        menus.add(menu);
        menu = new Menu("33", 33, 3, null);
        menus.add(menu);

        // level 3
        menu = new Menu("111", 111, 11, null);
        menus.add(menu);
        menu = new Menu("222", 222, 22, null);
        menus.add(menu);
        menu = new Menu("333", 333, 33, null);
        menus.add(menu);

        return menus;

    }


    @Data
    @AllArgsConstructor
    class Menu {

        String name;
        Integer id;
        Integer parentId;
        List<Menu> childList;
    }

    int sum(int val) {
        if (val == 1) return 1;
        return val + sum(val - 1);
    }

    @Test
    public void testRecursion() {
        System.out.printf(sum(100) + "");
    }


}
