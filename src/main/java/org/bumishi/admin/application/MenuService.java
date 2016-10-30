package org.bumishi.admin.application;

import org.bumishi.admin.domain.modle.Menu;
import org.bumishi.admin.domain.modle.TreeModel;
import org.bumishi.admin.domain.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by xieqiang on 2016/9/17.
 */
@Service
public class MenuService {

    @Autowired
    protected MenuRepository menuRepository;

    public void create(Menu menu){
       validate(menu);
        if(menuRepository.contains(menu.getId())){
            return;
        }
        menuRepository.add(menu);
    }

    public void modify(Menu menu){
        validate(menu);
        menuRepository.update(menu);
    }


    public Menu get(String code){
        return menuRepository.get(code);
    }

    public void delete(String code){
        menuRepository.remove(code);
    }

    public List<Menu> list(){
        List<Menu> list = menuRepository.list();
        TreeModel.sortByTree(list);
        return list;
    }

    public void switchStatus(String menu,boolean disable){
        menuRepository.switchStatus(menu,disable);
    }

    private void validate(Menu menu){
        Assert.hasText(menu.getId());
        Assert.hasText(menu.getLabel());
    }

}
