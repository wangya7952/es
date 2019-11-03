package com.lotgod.payment.order.model.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author lwg linweiguang@wuzhenpay.com
 * @date 2019/1/29 14:57
 * @copyright Copyright (c) 2018 wuzhenpay Inc. All Rights Reserved.
 * @description
 */
@Data
public class SettlementInfoVO {

    /***订单金额*/
    private BigDecimal money;

    /***支付金额*/
    private BigDecimal payMoney;

    /***退款金额*/
    private BigDecimal refundMoney;

    /***商户结算费*/
    private BigDecimal ratioMoney;

    /***渠道结算费*/
    private BigDecimal channelRatioMoney;

    /***通道结算费率*/
    private BigDecimal passagewayRatioMoney;

    /***订单总数*/
    private Long payNum;

    /***退款总数*/
    private Long refundNum;

    /***笔单价*/
    private BigDecimal unitMoney;

    /***结算金额*/
    private BigDecimal settlementMoney;

    /***退款费率*/
    private BigDecimal refundRatioMoney;
    /**
     * 分账金额
     */
    private BigDecimal sharedMoney;

}
