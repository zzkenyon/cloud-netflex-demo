package com.pd.user.mapper.persistence;

import com.pd.user.mapper.entity.MemberPo;
import com.pd.user.mapper.entity.MemberPoExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface MemberPoMapper {
    long countByExample(MemberPoExample example);

    int deleteByExample(MemberPoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MemberPo record);

    int insertSelective(MemberPo record);

    List<MemberPo> selectByExample(MemberPoExample example);

    MemberPo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MemberPo record, @Param("example") MemberPoExample example);

    int updateByExample(@Param("record") MemberPo record, @Param("example") MemberPoExample example);

    int updateByPrimaryKeySelective(MemberPo record);

    int updateByPrimaryKey(MemberPo record);
}