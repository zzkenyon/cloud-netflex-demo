package com.pd.goods.service;

import com.pd.goods.domian.ItemStockDo;
import com.pd.goods.mapper.entity.ItemPo;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/8/5 15:03
 */
public interface IItemService {
    List<ItemPo> queryBy(List<Long> ids);

    Boolean reduction(List<ItemStockDo> itemStockDos);
}
