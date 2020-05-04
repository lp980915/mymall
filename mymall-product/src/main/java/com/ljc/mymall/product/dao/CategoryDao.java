package com.ljc.mymall.product.dao;

import com.ljc.mymall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 商品三级分类
 * 
 * @author ljc
 * @email 15596712191@163.com
 * @date 2020-05-01 15:16:59
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
