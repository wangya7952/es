package com.lotgod.payment.order.model.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 订单分账明细表请求实体
 */
@Data
public class OrderShareProfitDetailRequest {
	
    /**
     * 收单商户名称或id
     */
    private Integer acquireMerchantId;

    /**
     * 分润商户名称或id
     */
    private Integer shareMerchantId;
    
    /**
     * 订单号
     */
    private String payNo;
    
    /**
     * 交易类型:0-支付 1-退款
     */
    private  Integer ordersType;

    /*
      开始时间
     */
    private  Integer startTime;

    /*
        结束时间
    */
    private  Integer endTime;

    /**
     * 当前页
     */
    @Min(1)
    @NotNull
    private Integer page;

    /**
     * 页大小
     */
    @Min(1)
    @NotNull
    private Integer size;
}
