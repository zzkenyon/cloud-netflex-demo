package com.pd.goods.controller;

import com.pd.goods.dto.ItemDto;
import com.pd.goods.mapper.entity.Item;
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
    public RequestResult<List<Item>> getItemsBy(@PathVariable("ids")List<Long> ids){
        List<Item> items = itemService.queryBy(ids);
        return new RequestResult.Builder<List<Item>>().data(items).Ok();
    }

    @PutMapping(value = "/item")
    public RequestResult decreaseStock(@RequestBody ItemDto itemDto){

        return new RequestResult.Builder<>().Ok();
    }
}
