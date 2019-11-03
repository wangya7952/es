package com.lotgod.es.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单索引
 *
 * @author Administrator
 */
@Document(indexName = "payment_order", type = "payment_order", createIndex = false)
@Data
public class PayOrderSearch implements Serializable {
    private static final long serialVersionUID = -8451891447837444786L;

    /**
     * 交易流水号
     */
    @Field(type = FieldType.Keyword)
    @Id
    private String payNo;

    /**
     * 父级订单号
     */
    @Field(type = FieldType.Keyword)
    private String parentPayNo;

    /**
     * 普通订单状态，-1:已关闭/已撤销；0:未支付；1:已支付；2:支付中；3:已对账；4:支付异常；5:支付失败； -----退款订单状态，-1:失败；0:退款中；1:退款成功；
     */
    @Field(type = FieldType.Integer)
    private Integer status;

    /**
     * 订单类型，default:默认订单；refund:退款订单
     */
    @Field(type = FieldType.Integer)
    private Integer ordersType;

    /**
     * 异步通知地址
     */
    @Field(type = FieldType.Keyword)
    private String notifyUrl;
    
    /**
     * 同步跳转地址
     */
    @Field(type = FieldType.Keyword)
    private String returnUrl;

    /**
     * 通道ID
     */
    @Field(type = FieldType.Integer)
    private Integer passagewayId;

    /**
     * 渠道ID
     */
    @Field(type = FieldType.Integer)
    private Integer channelId;

    /**
     * 商户类别
     */
    @Field(type = FieldType.Integer)
    private Integer merchantType;

    /**
     * 商户ID
     */
    @Field(type = FieldType.Integer)
    private Integer merchantId;

    /**
     * 大商户ID
     */
    @Field(type = FieldType.Integer)
    private Integer bigmerchantId;

    /**
     * 商户进件信息ID
     */
    @Field(type = FieldType.Integer)
    private Integer merchantRegisterInfoId;

    /**
     * 终端ID
     */
    @Field(type = FieldType.Keyword)
    private String terminalId;

    /**
     * 操作员ID
     */
    @Field(type = FieldType.Keyword)
    private String operatorId;

    /**
     * 支付方式，pay.wechat.js|pay.alipay.js等
     */
    @Field(type = FieldType.Integer)
    private Integer payType;

    /**
     * 订单标题
     */
    @Field(type = FieldType.Text)
    private String title;

    /**
     * 商户订单号
     */
    @Field(type = FieldType.Keyword)
    private String outTradeNo;

    /**
     * 通道订单号,
     */
    @Field(type = FieldType.Keyword)
    private String passagewayTradeNo;

    /**
     * 第三方支付号，比如支付宝、微信等
     */
    @Field(type = FieldType.Keyword)
    private String thirdTradeNo;

    /**
     * 待支付金额(单位:分)
     */
    @Field(type = FieldType.Long)
    private Long money;

    /**
     * 实际支付金额(单位:分)
     */
    @Field(type = FieldType.Long)
    private Long payMoney;

    //分账金额(单位:分)
    //按照 sum(floor(交易金额 * 各个分帐比例))
    @Field(type = FieldType.Long)
    private Long sharedMoney;
    
    /**
     * 分账计算结果
     * JSON格式字符串  
     * OrderShareProfitResult
     */
    @Field(type = FieldType.Text)
    private String shareResult;
    
    /**
     * 已退款金额
     */
    @Field(type = FieldType.Long)
    private Long refundedMoney;

    /**
     * 商户结算费率，3.5‰即3.5
     */
    @Field(type = FieldType.Double)
    private Double ratio;
    
    /**
     * 商户费率拓展
     */
    @Field(type = FieldType.Keyword)
    private String ratioExtend;
    
    /**
     * 商户结算费(单位:分)
     */
    @Field(type = FieldType.Long)
    private Long ratioMoney;

    /**
     * 渠道结算费率
     */
    @Field(type = FieldType.Double)
    private Double channelRatio;
    
    /**
     * 代理商费率拓展
     */
    @Field(type = FieldType.Keyword)
    private String channelRatioExtend;

    /**
     * 渠道结算费(单位:分)
     */
    @Field(type = FieldType.Long)
    private Long channelRatioMoney;

    /**
     * 通道结算费率
     */
    @Field(type = FieldType.Double)
    private Double passagewayRatio;
    
    /**
     * 平台通道费率拓展
     */
    @Field(type = FieldType.Keyword)
    private String passagewayRatioExtend;

    /**
     * 通道结算费(单位:分)
     */
    @Field(type = FieldType.Long)
    private Long passagewayRatioMoney;

    /**
     * 代理商利润 单位:分 (商户手续费 - 代理商手续费)
     */
    @Field(type = FieldType.Long)
    private Long agentProfit;

    /**
     * 平台利润 单位:分 (代理商手续费 - 通道手续费)
     */
    @Field(type = FieldType.Long)
    private Long platformProfit;

    /**
     * 确认对账 1-已确认 0-未确认
     */
    @Field(type = FieldType.Integer)
    private Integer verified;

    /**
     * 创建时间
     */
    @Field(type = FieldType.Integer)
    private Integer createTime;

    /**
     * 支付时间
     */
    @Field(type = FieldType.Integer)
    private Integer payTime;

    /**
     * 取消订单时间
     */
    @Field(type = FieldType.Integer)
    private Integer cancelTime;

    /**
     * 订单超时时间
     */
    @Field(type = FieldType.Integer)
    private Integer expireTime;

    /**
     * 更新时间
     */
    @Field(type = FieldType.Integer)
    private Integer updateTime;

    /**
     * 确认对账时间
     */
    @Field(type = FieldType.Integer)
    private Integer verifiedTime;

    /**
     * 业务拓展参数
     */
    @Field(type = FieldType.Keyword)
    private String attach;

    /**
     * 服务商公众号APPID
     */
    @Field(type = FieldType.Keyword)
    private String appid;

    /**
     * 用户在服务商 appid 下的唯一标识
     */
    @Field(type = FieldType.Keyword)
    private String openid;

    /**
     * 是否为小程序支付
     */
    @Field(type = FieldType.Integer)
    private Integer minipg;

    /**
     * 买家在支付宝的用户id
     */
    @Field(type = FieldType.Keyword)
    private String buyerUserId;

    /**
     * 买家支付宝账号
     */
    @Field(type = FieldType.Keyword)
    private String buyerLogonId;
    
    /**
	 * 付款银行卡类型
	 */
    @Field(type = FieldType.Integer)
	private Integer bankcardType;
	
	/**
	 * 付款银行卡号
	 */
    @Field(type = FieldType.Keyword)
	private String bankcardNo;

    /**
     * 交易支付使用的资金渠道
     */
    @Field(type = FieldType.Text)
    private String fundBillList;

    /**
     * 第三方支付源数据
     */
    @Field(type = FieldType.Text)
    private String sourceData;

    /**
     * 条码支付code
     */
    @Field(type = FieldType.Keyword)
    private String authCode;

    /**
     * IP地址
     */
    @Field(type = FieldType.Long)
    private Long ip;

    /**
     * 信息
     */
    @Field(type = FieldType.Keyword)
    private String msg;

    /**
     * 银行类型
     */
    @Field(type = FieldType.Keyword)
    private String bankType;

    /**
     * 货币种类
     */
    @Field(type = FieldType.Keyword)
    private String feeType;

    /**
     * 备注
     */
    @Field(type = FieldType.Keyword)
    private String remark;


    /**
     * 下发通知状态(0:失败 1:成功)
     */
    @Field(type = FieldType.Integer)
    private Integer notifyStatus;

    /**
     * 下发通知成功时间
     */
    @Field(type = FieldType.Integer)
    private Integer notifyTime;

    /**
     * 账单日期
     */
    @Field(type = FieldType.Date)
    private Date billDate;

    //版本号
    @Field(type = FieldType.Integer)
    private Integer version;
}
