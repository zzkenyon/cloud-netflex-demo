package com.pd.order.model;

import lombok.Data;

import java.util.Date;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/7/22 11:16
 */
@Data
public class Order {
    private String oId;
    private Date timeStamp;
}
