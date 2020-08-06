package com.pd.goods.mapper.persistence;

import com.pd.goods.domian.ItemStockDo;
import com.pd.goods.mapper.entity.ItemPo;
import com.pd.goods.mapper.entity.ItemExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

@Mapper
public interface ItemMapper {
    long countByExample(ItemExample example);

    int deleteByExample(ItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ItemPo record);

    int insertSelective(ItemPo record);

    List<ItemPo> selectByExample(ItemExample example);

    ItemPo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ItemPo record, @Param("example") ItemExample example);

    int updateByExample(@Param("record") ItemPo record, @Param("example") ItemExample example);

    int updateByPrimaryKeySelective(ItemPo record);

    int updateByPrimaryKey(ItemPo record);

    List<ItemPo> selectStockForUpdate(@Param("ids")List<Long> ids);

    Integer reduction(@Param("record")ItemPo record);
}