package com.pd.goods.mapper.persistence;

import com.pd.goods.mapper.entity.ItemCatPo;
import com.pd.goods.mapper.entity.ItemCatExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ItemCatMapper {
    long countByExample(ItemCatExample example);

    int deleteByExample(ItemCatExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ItemCatPo record);

    int insertSelective(ItemCatPo record);

    List<ItemCatPo> selectByExample(ItemCatExample example);

    ItemCatPo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ItemCatPo record, @Param("example") ItemCatExample example);

    int updateByExample(@Param("record") ItemCatPo record, @Param("example") ItemCatExample example);

    int updateByPrimaryKeySelective(ItemCatPo record);

    int updateByPrimaryKey(ItemCatPo record);
}