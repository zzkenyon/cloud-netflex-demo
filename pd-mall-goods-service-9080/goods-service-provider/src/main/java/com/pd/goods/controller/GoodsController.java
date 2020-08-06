package com.pd.goods.controller;

import com.pd.goods.domian.ItemStockDo;
import com.pd.goods.dto.ItemDto;
import com.pd.goods.mapper.entity.ItemPo;
import com.pd.goods.service.ItemService;
import com.pd.result.RequestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/8/5 13:48
 */
@RestController
public class GoodsController{
    @Autowired
    private ItemService itemService;

    @GetMapping(value = "/items/{ids}")
    public RequestResult<List<ItemPo>> getItemsBy(@PathVariable("ids")List<Long> ids){
        List<ItemPo> itemPos = itemService.queryBy(ids);
        return new RequestResult.Builder<List<ItemPo>>().data(itemPos).Ok();
    }

    @PutMapping(value = "/item")
    public RequestResult decreaseStock(@RequestBody List<ItemStockDo> itemStockDos){

        return new RequestResult.Builder<>().Ok();
    }
}
