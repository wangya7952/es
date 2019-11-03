package com.lotgod.common.model.vo;

import java.io.Serializable;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RefundMoneyStatisticsVO implements Serializable {

	private static final long serialVersionUID = -8485145961507694190L;
	
	//全部已退款总金额
	Long totalRefundMoney;
	//全部已退还商户手续费总额
	Long totalRefundRateMoney;
	//全部已退款渠道手续费总额
	Long totalRefundChannelRateMoney;
	//全部已退款通道手续费总额
	Long totalRefundPassagewayRateMoney;
	
	
}
