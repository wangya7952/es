package com.lotgod.es.service;

//import com.lotgod.common.model.DairyTradeStatisticsPO;
import com.lotgod.common.model.page.PageBean;
import com.lotgod.common.model.vo.RefundMoneyStatisticsVO;
import com.lotgod.es.entity.PayOrderSearch;
import com.lotgod.payment.order.enums.OrderTypeEnum;
import com.lotgod.payment.order.model.vo.PayOrderSearchQueryVO;
import com.lotgod.payment.order.model.vo.SettlementInfoVO;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface PayOrderSearchService {

    /**
     * 保存订单信息
     *
     * @param payOrderSearch
     */
    void insertSelective(PayOrderSearch payOrderSearch);

    /**
     * 根据订单id,查询订单记录
     *
     * @param payNo
     * @return
     */
    PayOrderSearch selectByPayNo(String payNo);

    /**
     * 根据商户id,通道订单号获取订单记录
     *
     * @param merchantId
     * @param passagewayTradeNo 通道订单号
     * @return
     */
    PayOrderSearch selectByPassagewayTradeNo(Integer merchantId, String passagewayTradeNo);

    /**
     * 根据商户id,外部订单号,获取订单记录
     *
     * @param merchantId
     * @param outTradeNo
     * @return
     */
    PayOrderSearch selectByOutTradeNo(Integer merchantId, String outTradeNo);

    Boolean isOutTradeNoExist(Integer merchantId, String outTradeNo, OrderTypeEnum orderType);

    Boolean isAuthCodeExist(String payType, String authCode);

    void updateRecord(PayOrderSearch payOrderSearch);

    /**
     * 今日的营业额
     *
     * @param merchantId 商户id
     * @param payType
     * @return
     */
    BigDecimal selectMerchantTodayTurnoverByPayType(Integer merchantId, String payType,String date);

    /**
     * 根据商户号和退款单号获取退款信息
     *
     * @param merchantId
     * @param outRefundNo
     * @return
     */
    PayOrderSearch selectByRefundNoMerchantId(Integer merchantId, String outRefundNo);

    /**
     * 根据原支付订单号统计已退款金额及手续费
     *
     * @param 	payNo	支付订单号
     * @param refundedTime unix时间戳，秒   退款成功流程传null。   对账时候重新计算传入当笔退款成功时候的时间戳
     * @return
     */
    RefundMoneyStatisticsVO selectRefundedMoney(String payNo, Integer refundedTime);

    /**
     * 非失败退款金额统计
     * @param payNo 	支付订单号
     * @return long 	已退款金额 + 退款中金额
     * @throws
     */
	long selectAllActiveRefundMoneyByPayNo(String payNo);




	/**************************************** 以下是从merchant中分离出来的 **************************************************/



    /**
     * ES查询结账金额
     *
     * @param merchantId
     * @param merchantType
     * @param startTime
     * @param endTime
     * @return
     */
    SettlementInfoVO querySettlementInfo(Integer merchantId, Integer merchantType, Integer startTime, Integer endTime);

    /**
     * 查询某些支付方式订单金额
     *
     * @param merchantId
     * @param merchantType
     * @param startTime
     * @param endTime
     * @param payTypeIds
     * @return
     */
    Long querySettlementProportionEntity(Integer merchantId, Integer merchantType, Integer startTime, Integer endTime, List<Integer> payTypeIds);
//
    /**
     * 查询订单列表
     *
     * @param query
     * @return
     */
    PageBean<PayOrderSearch> queryOrders(PayOrderSearchQueryVO query);

    /**
     * 查询订单流水
     *
     * @param query
     * @return
     */
    PageBean<PayOrderSearch> queryOrderFlowByPayNo(String payNo, Integer page, Integer size);

    /**
     * 查询订单退款金额
     *
     * @param payNo
     * @return
     */
    Long getRefundMoney(String payNo);

    /**
     *
     * @param merchantId
     * @param datetime
     * @return
     */
    Map<String, Object> selectCountAndAmountByTime(Integer merchantId, Integer startTime, Integer endTime);

	/**
	 * 处理未支付的订单数据
	 *
	 * @return
	 */
	Long dealNopayOrderInfoByStatueTime(Integer status, int dataTime);

    /**
     * 查询退款中订单梳理
     * @Title: countRefundingOrderByPayNo
     * @param payNo
     * @return int
     * @throws
     */
    Long countRefundingOrderByPayNo(String payNo);

    /**
	 * 按照通道id、支付方式 统计商户的单天的交易
	 * @Title: dateTradeStatistics
	 * @param merchantIds 商户
	 * @param passagewayId 通道id
	 * @param date yyy-MM-dd
	 * @return List<DairyTradeStatisticsPO> 每日交易统计记录
	 * @throws
	 */
//	public List<DairyTradeStatisticsPO> dateTradeStatistics(List<Integer> merchantIds, Integer passagewayId, String date);

    /**
     * 根据订单号查询订单列表
     * @Title: selectOrderByPayNoList   
     * @param thousandPayNo
     * @return List<PayOrderSearch>      
     * @throws
     */
	List<PayOrderSearch> selectOrderByPayNoList(List<String> thousandPayNo);

	/**
	 * 查询未确认对账的交易
	 * @Title: queryUnVerifyOrders   
	 * @param billDate
	 * @param passagewayId
	 * @return List<PayOrderSearch>      
	 * @throws
	 */
	List<PayOrderSearch> queryUnVerifyOrders(String billDate, int passagewayId);

	/**
	 * 批量更新ES订单数据
	 * @Title: batchUpdatePayOrderSearch   
	 * @param payOrderSearchList
	 * @throws
	 */
	void batchUpdatePayOrderSearch(List<PayOrderSearch> payOrderSearchList);

	/**
	 * 批量保存ES订单
	 * @Title: batchInsertPayOrderSearch   
	 * @param payOrderSearchList      
	 * @throws
	 */
	void batchInsertPayOrderSearch(List<PayOrderSearch> payOrderSearchList);

	/**
	 * 查询支付订单所有退款成功金额列表
	 * @Title: selectRefundedMoneyList   
	 * @param payNo
	 * @return  List<Long>      
	 * @throws
	 */
	List<Long> selectRefundedMoneyList(String payNo);

	/**
	 * 根据支付订单号查询退款订单
	 * @Title: queryOrderByParentNo   
	 * @param page
	 * @param pageSize
	 * @param payNoArr
	 * @return PageBean<PayOrderSearch>      
	 * @throws
	 */
	PageBean<PayOrderSearch> queryRefundOrderByParentNo(int page, int pageSize, String... payNoArr);

	/**
	 * 查询通道交易金额
	 * @param merchantId
	 * @param merchantType
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public  Map<Integer,Long> querySettlementProportionEntityByPassageway(Integer merchantId, Integer merchantType, Integer startTime, Integer endTime);
}
