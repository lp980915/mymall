package com.ljc.mymall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljc.common.utils.PageUtils;
import com.ljc.mymall.product.entity.SkuSaleAttrValueEntity;

import java.util.Map;

/**
 * sku销售属性&值
 *
 * @author ljc
 * @email 15596712191@163.com
 * @date 2020-05-01 15:16:59
 */
public interface SkuSaleAttrValueService extends IService<SkuSaleAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

