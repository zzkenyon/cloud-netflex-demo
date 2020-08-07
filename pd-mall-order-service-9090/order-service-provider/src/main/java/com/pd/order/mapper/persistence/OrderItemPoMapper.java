package com.pd.order.mapper.persistence;

import com.pd.order.mapper.entity.OrderItemPo;
import com.pd.order.mapper.entity.OrderItemPoExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderItemPoMapper {
    long countByExample(OrderItemPoExample example);

    int deleteByExample(OrderItemPoExample example);

    int deleteByPrimaryKey(String id);

    int insert(OrderItemPo record);

    int insertSelective(OrderItemPo record);

    List<OrderItemPo> selectByExample(OrderItemPoExample example);

    OrderItemPo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OrderItemPo record, @Param("example") OrderItemPoExample example);

    int updateByExample(@Param("record") OrderItemPo record, @Param("example") OrderItemPoExample example);

    int updateByPrimaryKeySelective(OrderItemPo record);

    int updateByPrimaryKey(OrderItemPo record);
}