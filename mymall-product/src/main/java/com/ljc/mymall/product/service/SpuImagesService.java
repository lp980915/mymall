package com.ljc.mymall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljc.common.utils.PageUtils;
import com.ljc.mymall.product.entity.SpuImagesEntity;

import java.util.Map;

/**
 * spu图片
 *
 * @author ljc
 * @email 15596712191@163.com
 * @date 2020-05-01 15:16:59
 */
public interface SpuImagesService extends IService<SpuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

