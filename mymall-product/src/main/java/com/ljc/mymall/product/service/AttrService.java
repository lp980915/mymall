package com.ljc.mymall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljc.common.utils.PageUtils;
import com.ljc.mymall.product.entity.AttrEntity;

import java.util.Map;

/**
 * 商品属性
 *
 * @author ljc
 * @email 15596712191@163.com
 * @date 2020-05-01 15:17:00
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

