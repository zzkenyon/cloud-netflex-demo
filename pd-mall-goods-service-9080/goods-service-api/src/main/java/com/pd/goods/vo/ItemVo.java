package com.pd.goods.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/8/6 10:07
 */
@Data
public class ItemVo {
    private Long id;

    private String title;

    private BigDecimal price;

    private Integer num;

    private String image;

    private Long cid;
}
