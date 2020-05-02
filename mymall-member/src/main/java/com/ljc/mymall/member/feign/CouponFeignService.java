package com.ljc.mymall.member.feign;

import com.ljc.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 这个接口用来远程调用mymall-coupon下的服务
 */
@Component
@FeignClient("mymall-coupon")
public interface CouponFeignService {

    @RequestMapping("/coupon/coupon/member/list")
    public R memberCoupons();
}
