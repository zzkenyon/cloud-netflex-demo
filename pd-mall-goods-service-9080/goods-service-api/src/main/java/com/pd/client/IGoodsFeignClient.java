package com.pd.client;

import com.pd.goods.dto.ItemStockDto;
import com.pd.goods.vo.ItemVo;
import com.pd.result.RequestResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "goods-service")
public interface IGoodsFeignClient {
    @GetMapping(value = "/items/{ids}")
    RequestResult<List<ItemVo>> getItemsBy(@PathVariable("ids")List<Long> ids);

    @PutMapping(value = "/items/stock")
    RequestResult decreaseStock(@RequestBody List<ItemStockDto> itemStockDtos);
}
