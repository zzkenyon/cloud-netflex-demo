package com.pd.goods.service;

import com.pd.goods.mapper.entity.Item;

import java.util.List;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/8/5 15:03
 */
public interface IItemService {
    public List<Item> queryBy(List<Long> ids);
}
