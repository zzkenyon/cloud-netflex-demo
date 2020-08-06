package com.pd.goods.controller;

import com.pd.goods.clients.IGoodsFeignClient;
import com.pd.goods.converter.ItemConverter;
import com.pd.goods.domian.ItemStockDo;
import com.pd.goods.dto.ItemStockDto;
import com.pd.goods.mapper.entity.ItemPo;
import com.pd.goods.service.ItemService;
import com.pd.goods.vo.ItemVo;
import com.pd.result.RequestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/8/5 13:48
 */
@RestController
@Slf4j
public class GoodsController implements IGoodsFeignClient {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemConverter itemConverter;

    @Override
    public RequestResult<List<ItemVo>> getItemsBy(List<Long> ids){
        log.info("begin GoodsController.getItemsBy:"+ids);
        List<ItemPo> itemPos = itemService.queryBy(ids);
        List<ItemVo> itemVos = itemConverter.itemPos2Vos(itemPos);
        return new RequestResult.Builder<List<ItemVo>>().data(itemVos).Ok();
    }

    @Override
    public RequestResult decreaseStock(List<ItemStockDto> itemStockDtos){
        log.info("begin GoodsFeignClient.decreaseStock:"+itemStockDtos);
        List<ItemStockDo> stockDos = itemConverter.stockDtos2Dos(itemStockDtos);
        boolean res = itemService.reduction(stockDos);
        return new RequestResult.Builder<>().Ok();
    }
}
