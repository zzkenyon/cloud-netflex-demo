package com.pd.goods.converter;

import com.pd.goods.domian.ItemStockDo;
import com.pd.goods.dto.ItemDto;
import com.pd.goods.dto.ItemStockDto;
import com.pd.goods.mapper.entity.ItemPo;
import com.pd.goods.vo.ItemVo;
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
    ItemDto itemPo2Dto(ItemPo itemPoDo);
    List<ItemDto> itemPos2Dtos(List<ItemPo> itemPoDos);

    @Mappings({})
    ItemVo itemPo2Vo(ItemPo itemPo);
    List<ItemVo> itemPos2Vos(List<ItemPo>itemPos);

    @Mappings({})
    ItemStockDo stockDto2Do(ItemStockDto dto);
    List<ItemStockDo> stockDtos2Dos(List<ItemStockDto> dtos);
}
