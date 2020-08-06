package com.pd.goods.service;

import com.pd.exception.BizException;
import com.pd.goods.domian.ItemStockDo;
import com.pd.goods.mapper.entity.ItemExample;
import com.pd.goods.mapper.entity.ItemPo;
import com.pd.goods.mapper.persistence.ItemMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public List<ItemPo> queryBy(List<Long> ids) {
        ItemExample itemExample = new ItemExample();
        itemExample.createCriteria().andIdIn(ids);
        return itemMapper.selectByExample(itemExample);
    }

    @Transactional
    @Override
    public Boolean reduction(List<ItemStockDo> itemStockDos) {
        List<Long> ids = itemStockDos.stream().map(ItemStockDo::getItemId).collect(Collectors.toList());
        List<ItemPo> items = itemMapper.selectStockForUpdate(ids);
        if(items.isEmpty() || items == null){
            throw new BizException("商品不存在");
        }
        if(ids.size() != items.size()){
            throw new BizException("有商品不存在");
        }
        itemStockDos.forEach(itemStockDo-> {
            items.forEach(item -> {
                if (Objects.equals(item.getId(), itemStockDo.getItemId())) {
                    if (item.getNum() < itemStockDo.getNum()) {
                        throw new BizException(itemStockDo.getItemId() + ":库存不足");
                    }
                    ItemPo record = new ItemPo();
                    record.setId(itemStockDo.getItemId());
                    record.setNum(item.getNum() - itemStockDo.getNum());
                    int row = itemMapper.reduction(record);
                    if (row <= 0) {
                        throw new BizException(itemStockDo.getItemId() + ":库存不足");
                    }
                }
            });
        });
        return true;
    }
}
