package com.ljc.mymall.coupon.dao;

import com.ljc.mymall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author ljc
 * @email 15596712191@163.com
 * @date 2020-05-02 14:43:01
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
