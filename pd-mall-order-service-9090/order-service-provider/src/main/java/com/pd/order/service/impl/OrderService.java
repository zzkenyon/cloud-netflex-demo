package com.pd.order.service.impl;

import com.pd.exception.BizException;
import com.pd.client.IGoodsFeignClient;
import com.pd.goods.dto.ItemDto;
import com.pd.goods.dto.ItemStockDto;
import com.pd.goods.vo.ItemVo;
import com.pd.order.converter.OrderConverter;
import com.pd.order.dto.OrderDto;
import com.pd.order.mapper.entity.OrderItemPo;
import com.pd.order.mapper.entity.OrderPo;
import com.pd.order.mapper.persistence.OrderItemPoMapper;
import com.pd.order.mapper.persistence.OrderPoMapper;
import com.pd.order.service.IOrderService;
import com.pd.result.RequestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/8/6 15:48
 */
@Service
@Slf4j
public class OrderService  implements IOrderService {
    @Resource
    private IGoodsFeignClient goodsService;
    @Resource
    private OrderConverter converter;
    @Resource
    private OrderPoMapper orderPoMapper;
    @Resource
    private OrderItemPoMapper orderItemPoMapper;

    @Override
    public String createOrder(OrderDto orderDto) {
        /**
         * 1. 减库存
         * 2. 查询商品信息 为了获取价格计算费用
         * 3. 创建订单（订单号生成，计算费用，插入OrderItem表，插入Order表）
         */
        log.debug("OrderService.createOrder: {}",orderDto.toString());
        List<ItemStockDto> stockDto = converter.itemDtos2StockDtos(orderDto.getItems());
        RequestResult result = goodsService.decreaseStock(stockDto);
        if(result.getCode() != 200){
            throw new BizException(result.getMsg());
        }
        List<Long> ids = stockDto.stream().map(ItemStockDto::getItemId).collect(Collectors.toList());
        RequestResult<List<ItemVo>> itemDetails = goodsService.getItemsBy(ids);
        String orderId = UUID.randomUUID().toString().replaceAll("-","");
        BigDecimal totalPrice=new BigDecimal(0);
        Date date = new Date();
        for(ItemDto itemDto : orderDto.getItems()){
            for(ItemVo itemVo: itemDetails.getData()){
                if(Objects.equals(itemDto.getId(),itemVo.getId())){
                    OrderItemPo orderItemPo = new OrderItemPo();
                    BigDecimal totalFee=itemVo.getPrice().multiply(BigDecimal.valueOf(itemDto.getNum()));
                    totalPrice = totalPrice.add(totalFee);
                    orderItemPo.setTotalFee(totalFee);
                    orderItemPo.setCreateTime(date);
                    orderItemPo.setUpdateTime(date);
                    orderItemPo.setId(UUID.randomUUID().toString().replaceAll("-",""));
                    orderItemPo.setItemId(itemVo.getId());
                    orderItemPo.setNum(itemDto.getNum());
                    orderItemPo.setOrderId(orderId);
                    orderItemPo.setPrice(itemVo.getPrice());
                    orderItemPo.setStatus(1);
                    orderItemPo.setTitle(itemVo.getTitle());
                    orderItemPoMapper.insert(orderItemPo);
                }
            }
        }
        OrderPo tbOrder=new OrderPo();
        tbOrder.setOrderId(orderId);
        tbOrder.setPayment(totalPrice);
        tbOrder.setPaymentTime(date);
        tbOrder.setStatus(0);
        tbOrder.setCreateTime(date);
        tbOrder.setUpdateTime(date);
        tbOrder.setUserId(1000000l);
        tbOrder.setOrderId(orderId);
        orderPoMapper.insert(tbOrder);
        return orderId;
    }

    @Override
    public List<String> queryIds() {
        return orderPoMapper.queryIds();
    }
}
