package com.pd.goods.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/8/5 15:59
 */
@Data
public class ItemDto {
    private Long id;

    private String title;

    private BigDecimal price;

    private Integer num;

    private String image;

    private Long cid;
}
