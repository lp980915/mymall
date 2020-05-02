package com.ljc.mymall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljc.common.utils.PageUtils;
import com.ljc.mymall.order.entity.OrderItemEntity;

import java.util.Map;

/**
 * 订单项信息
 *
 * @author ljc
 * @email 15596712191@163.com
 * @date 2020-05-02 16:12:06
 */
public interface OrderItemService extends IService<OrderItemEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

