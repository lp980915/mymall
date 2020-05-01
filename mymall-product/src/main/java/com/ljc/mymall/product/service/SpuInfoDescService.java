package com.ljc.mymall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljc.common.utils.PageUtils;
import com.ljc.mymall.product.entity.SpuInfoDescEntity;

import java.util.Map;

/**
 * spu信息介绍
 *
 * @author ljc
 * @email 15596712191@163.com
 * @date 2020-05-01 15:16:59
 */
public interface SpuInfoDescService extends IService<SpuInfoDescEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

