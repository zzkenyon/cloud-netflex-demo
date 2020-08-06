package com.pd.goods.converter;

import com.pd.goods.dto.ItemDto;
import com.pd.goods.mapper.entity.ItemPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/8/6 09:46
 */
@Mapper(componentModel="spring")
public interface ItemConverter {
    @Mappings({})
    ItemDto itemDo2Dto(ItemPo itemPoDo);
    List<ItemDto> itemDos2Dtos(List<ItemPo> itemPoDos);
}
