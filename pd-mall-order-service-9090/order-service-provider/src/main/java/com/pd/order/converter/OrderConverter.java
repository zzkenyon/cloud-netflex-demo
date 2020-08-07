package com.pd.order.converter;

import com.pd.goods.dto.ItemDto;
import com.pd.goods.dto.ItemStockDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderConverter {
    @Mappings({
            @Mapping(source = "id",target = "itemId")
    })
    ItemStockDto itemDto2StockDto(ItemDto itemDto);
    List<ItemStockDto> itemDtos2StockDtos(List<ItemDto> itemDtos);

}
