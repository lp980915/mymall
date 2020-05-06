package com.ljc.mymall.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljc.common.utils.PageUtils;
import com.ljc.common.utils.Query;

import com.ljc.mymall.product.dao.CategoryDao;
import com.ljc.mymall.product.entity.CategoryEntity;
import com.ljc.mymall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    //由于继承了ServiceImpl<CategoryDao, CategoryEntity>，相当于已经注入了CategoryDao。BaseMapper就相当于CategoryDao
   /* @Autowired
    private CategoryDao categoryDao;*/
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        //1.查出所有分类
        List<CategoryEntity> entities = baseMapper.selectList(null);
        //2.组装成父子树形结构
        //2.1找出所有的一级分类
        List<CategoryEntity> level1Menus = entities.stream().filter((categoryEntity) -> {
            //返回所有parent_cid为0的为一级分类
            return categoryEntity.getParentCid() == 0;
            //获取子菜单
        }).map((menu)->{
            menu.setChildren(getChildrens(menu,entities));
            return menu;
            //为菜单排序
        }).sorted((menu1,menu2)->{
            return (menu1.getSort()==null?0:menu1.getSort()) - (menu2.getSort()==null?0:menu2.getSort());
        }).collect(Collectors.toList());

        return level1Menus;
    }

    @Override
    public void removeMenuByIds(List<Long> asList) {
        //TODO 检查当前要删除的菜单是否被别的地方引用

        //逻辑删除
        baseMapper.deleteBatchIds(asList);
    }

    /**
     * 递归查找所有菜单的子菜单
     * @param root 传入当前菜单
     * @param all 传入所有菜单
     * @return  返回对应的子菜单
     */
    private List<CategoryEntity> getChildrens(CategoryEntity root,List<CategoryEntity> all){
        List<CategoryEntity> children = all.stream().filter(categoryEntity -> {
            //如果当前菜单的父ID等于传入菜单的ID，那么这个菜单就是一个传入菜单的子菜单
            return categoryEntity.getParentCid().equals(root.getCatId());
            //子菜单也会有对应的子菜单，依次递归，继续获取。
        }).map(categoryEntity -> {
            categoryEntity.setChildren(getChildrens(categoryEntity,all));
            return categoryEntity;
        }).sorted((menu1,menu2)->{
            //菜单的排序
            return (menu1.getSort()==null?0:menu1.getSort()) - (menu2.getSort()==null?0:menu2.getSort());
        }).collect(Collectors.toList());

        return children;
    }

}