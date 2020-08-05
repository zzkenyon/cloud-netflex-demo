package com.pd.goods.service;

import com.netflix.discovery.converters.Auto;
import com.pd.goods.mapper.entity.Item;
import com.pd.goods.mapper.entity.ItemExample;
import com.pd.goods.mapper.persistence.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/8/5 15:05
 */
@Service
public class ItemService implements IItemService{
    @Resource
    private ItemMapper itemMapper;

    @Override
    public List<Item> queryBy(List<Long> ids) {
        return itemMapper.selectStockForUpdate(ids);
    }
}
