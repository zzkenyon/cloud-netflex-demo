package com.pd.goods.mapper.persistence;

import com.pd.goods.mapper.entity.ItemCat;
import com.pd.goods.mapper.entity.ItemCatExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ItemCatMapper {
    long countByExample(ItemCatExample example);

    int deleteByExample(ItemCatExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ItemCat record);

    int insertSelective(ItemCat record);

    List<ItemCat> selectByExample(ItemCatExample example);

    ItemCat selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ItemCat record, @Param("example") ItemCatExample example);

    int updateByExample(@Param("record") ItemCat record, @Param("example") ItemCatExample example);

    int updateByPrimaryKeySelective(ItemCat record);

    int updateByPrimaryKey(ItemCat record);
}