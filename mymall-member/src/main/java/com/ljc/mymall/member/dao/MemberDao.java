package com.ljc.mymall.member.dao;

import com.ljc.mymall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author ljc
 * @email 15596712191@163.com
 * @date 2020-05-02 15:38:49
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
