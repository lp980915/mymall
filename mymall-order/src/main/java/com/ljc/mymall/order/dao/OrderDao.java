package com.ljc.mymall.order.dao;

import com.ljc.mymall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author ljc
 * @email 15596712191@163.com
 * @date 2020-05-02 16:12:06
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
