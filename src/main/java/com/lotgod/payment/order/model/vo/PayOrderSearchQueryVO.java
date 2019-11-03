package com.lotgod.payment.order.model.vo;

import lombok.Data;

/**
 * @author lwg linweiguang@wuzhenpay.com
 * @date 2019/2/18 13:41
 * @copyright Copyright (c) 2018 wuzhenpay Inc. All Rights Reserved.
 * @description
 */
@Data
public class PayOrderSearchQueryVO {

    public static final Integer QUERYTYPE_PAY_ORDER = 0;
    public static final Integer QUERYTYPE_ALL_ORDER = 1;

    /***查询方式(0:仅主订单 1:全部)*/
    private Integer queryType;

    /***渠道id*/
    private Integer channelId;

    /***大商户id*/
    private Integer bigmerchantId;

    /***商户id*/
    private Integer merchantId;

    /***交易流水号*/
    private String payNo;

    /***第三方支付号，比如支付宝、微信等*/
    private String thirdTradeNo;

    /***商户订单号*/
    private String outTradeNo;

    /***通道订单号*/
    private String passagewayTradeNo;
    /**
     * 通道id
     */
    private Integer passagewayId;

    /***订单类型 0:主订单 1:退款订单*/
    private Integer ordersType;

    /***订单状态*/
    private Integer status;

    /***支付方式*/
    private String payTypeId;

    /***订单类型(wechat alipay)*/
    private String payCategory;

    /***开始时间*/
    private Integer startTime;

    /***结束时间*/
    private Integer endTime;

    private Integer page;

    private Integer size;
    /**
     * 是否有退款
     */
    private Byte hasRefunded;
}
