package com.ljc.mymall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljc.common.utils.PageUtils;
import com.ljc.mymall.product.entity.BrandEntity;

import java.util.Map;

/**
 * 品牌
 *
 * @author ljc
 * @email 15596712191@163.com
 * @date 2020-05-01 15:16:59
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

