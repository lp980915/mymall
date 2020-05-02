package com.ljc.mymall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljc.common.utils.PageUtils;
import com.ljc.mymall.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author ljc
 * @email 15596712191@163.com
 * @date 2020-05-02 15:38:49
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

