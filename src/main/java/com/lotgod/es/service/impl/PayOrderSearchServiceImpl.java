package com.lotgod.es.service.impl;

//import com.lotgod.common.model.DairyTradeStatisticsPO;
import com.lotgod.common.elasticsearch.query.PayOrderEsQuery;
import com.lotgod.common.model.page.PageBean;
import com.lotgod.common.model.vo.RefundMoneyStatisticsVO;
import com.lotgod.es.entity.PayOrderSearch;
import com.lotgod.es.repositories.PayOrderSearchRepository;
import com.lotgod.es.service.PayOrderSearchService;
import com.lotgod.payment.order.enums.OrderTypeEnum;
import com.lotgod.payment.order.model.vo.PayOrderSearchQueryVO;
import com.lotgod.payment.order.model.vo.SettlementInfoVO;
import com.lotgod.utils.DateUtil;
import org.apache.commons.beanutils.PropertyUtils;

import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.sum.InternalSum;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.mapping.ElasticsearchPersistentEntity;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.util.*;

@Service
public class PayOrderSearchServiceImpl implements PayOrderSearchService {

    @Autowired
    PayOrderSearchRepository payOrderSearchRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public void insertSelective(PayOrderSearch payOrderSearch) {

    }

    /**
     * 根据平台订单号查询
     * @param payNo
     * @return
     */
    @Override
    public PayOrderSearch selectByPayNo(String payNo) {
        Optional<PayOrderSearch>  optional =  payOrderSearchRepository.findById(payNo);
        return optional.orElse(null);
    }

    @Override
    public PayOrderSearch selectByPassagewayTradeNo(Integer merchantId, String passagewayTradeNo) {
        PayOrderEsQuery payOrderEsQuery = new PayOrderEsQuery();
        payOrderEsQuery.setMerchantId(merchantId);
        payOrderEsQuery.setPassagewayTradeNo(passagewayTradeNo);
        payOrderEsQuery.setPage(0);
        payOrderEsQuery.setPageSize(1);
        return elasticsearchTemplate.queryForObject(payOrderEsQuery.buildQuery(), PayOrderSearch.class);
    }

    /**
     * 根据商户id 和 商户交易id
     * @param merchantId
     * @param outTradeNo
     * @return
     */
    @Override
    public PayOrderSearch selectByOutTradeNo(Integer merchantId, String outTradeNo) {
        PayOrderEsQuery payOrderEsQuery = new PayOrderEsQuery();
        payOrderEsQuery.setMerchantId(merchantId);
        payOrderEsQuery.setOutTradeNo(outTradeNo);
        payOrderEsQuery.setPage(0);
        payOrderEsQuery.setPageSize(1);
        return elasticsearchTemplate.queryForObject(payOrderEsQuery.buildQuery(), PayOrderSearch.class);
    }

    /**
     * //TODO 商户id，交易订单号，订单类型是否存在
     * @param merchantId
     * @param outTradeNo
     * @param orderType
     * @return
     */
    @Override
    public Boolean isOutTradeNoExist(Integer merchantId, String outTradeNo, OrderTypeEnum orderType) {
        PayOrderEsQuery payOrderEsQuery = new PayOrderEsQuery();
        payOrderEsQuery.setMerchantId(merchantId);
        payOrderEsQuery.setOutTradeNo(outTradeNo);
        payOrderEsQuery.setOrdersType(orderType.getCode());
        long row = elasticsearchTemplate.count(payOrderEsQuery.buildQuery(),PayOrderSearch.class);
        return row>0;
    }

    @Override
    public Boolean isAuthCodeExist(String payType, String authCode) {
        return null;
    }

    /**
     * //TODO 重点关注
     * 流程：
     *    1、封装updateQuery对象
     *      1.1、利用 PropertyUtils.getPropertyDescriptors 获取PayOrderSearch属性名称及值，放到map里（es的_source是json格式，最终map转到json存到es里）
     *      1.2、利用UpdateQueryBuilder.withId(payOrderSearch.getPayNo()).withClass(PayOrderSearch.class).build() 创建UpdateQuery
     *      1.3、创建UpdateRequest对象，updateRequest.doc(map)
     *      1.4、UpdateQuery.setUpdateRequest(updateRequest)
     *    2、elasticsearchTemplate上场
     *      elasticsearchTemplate.update(UpdateQuery对象)
     *      elasticsearchTemplate.refresh(PayOrderSearch.class)
     * UpdateQueryBuilder
     * @param payOrderSearch
     */
    @Override
    public void updateRecord(PayOrderSearch payOrderSearch) {
        try {
            //1、把
            Map<String,Object> map =new HashMap<>();
            PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(payOrderSearch);
            for (PropertyDescriptor  pd :pds) {
                String fieldName = pd.getName();
                if(fieldName.equals("class")){
                    continue;
                }
                Object fieldValue = PropertyUtils.getProperty(payOrderSearch,fieldName);
                if(fieldValue != null) {
                    map.put(fieldName, fieldValue);
                }
            }
            if(map.size() > 0){
                UpdateRequest updateRequest = new UpdateRequest();
                updateRequest.doc(map);
                UpdateQuery updateQuery = new UpdateQueryBuilder().withId(payOrderSearch.getPayNo()).withClass(PayOrderSearch.class).build();
                updateQuery.setUpdateRequest(updateRequest);
                elasticsearchTemplate.update(updateQuery);
                //一个文档进入es初期，文档是被缓存内存里，1秒后会被写入文件系统缓存，这样该文档就可以被搜索到。注意，此时该索引数据被没有最终写入到磁盘上。
                // 如果你对这1s的时间间隔还不满意， 调用_refresh就可以立即实现内存->文件系统缓存， 从而使文档可以立即被搜索到。
                elasticsearchTemplate.refresh(PayOrderSearch.class);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 批量更新
     * @param payOrderSearchList
     */
    @Override
    public void batchUpdatePayOrderSearch(List<PayOrderSearch> payOrderSearchList) {
        try {
            List<UpdateQuery> list = new ArrayList<>();
            if(!CollectionUtils.isEmpty(payOrderSearchList)) {
                //1、把
                for (PayOrderSearch payOrderSearch : payOrderSearchList) {
                    Map<String, Object> map = new HashMap<>();
                    PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(payOrderSearch);
                    for (PropertyDescriptor pd : pds) {
                        String fieldName = pd.getName();
                        if (fieldName.equals("class")) {
                            continue;
                        }
                        Object fieldValue = PropertyUtils.getProperty(payOrderSearch, fieldName);
                        if (fieldValue != null) {
                            map.put(fieldName, fieldValue);
                        }
                    }
                    if (map.size() > 0) {
                        UpdateRequest updateRequest = new UpdateRequest();
                        updateRequest.doc(map);
                        UpdateQuery updateQuery = new UpdateQueryBuilder().withId(payOrderSearch.getPayNo()).withClass(PayOrderSearch.class).build();
                        updateQuery.setUpdateRequest(updateRequest);
                        list.add(updateQuery);
                    }
                }
                if (list.size() > 0) {
                    elasticsearchTemplate.bulkUpdate(list);
                    //一个文档进入es初期，文档是被缓存内存里，1秒后会被写入文件系统缓存，这样该文档就可以被搜索到。注意，此时该索引数据被没有最终写入到磁盘上。
                    // 如果你对这1s的时间间隔还不满意， 调用_refresh就可以立即实现内存->文件系统缓存， 从而使文档可以立即被搜索到。
                    elasticsearchTemplate.refresh(PayOrderSearch.class);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 某日的营业额
     * dateStr 格式：yyyy-MM-dd
     */
    @Override
    public BigDecimal selectMerchantTodayTurnoverByPayType(Integer merchantId, String payType,String dateStr) {
        //都是出自 org.elasticsearch.xxx包
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        //status状态为1:已支付,3:已对账
       /* queryBuilder.should(QueryBuilders.termQuery("status",1));
        queryBuilder.should(QueryBuilders.termQuery("status",3));
        queryBuilder.minimumShouldMatch(1);//必须匹配一个 or
        */

        //支付时间
        Date date = DateUtil.parseStrToDate(dateStr,DateUtil.DATE_FORMAT_YYYY_MM_DD);
        int startTime = DateUtil.getTimestamp(DateUtil.getDayBeginTime(date)).intValue();
        int endTime = DateUtil.getTimestamp(DateUtil.getDayEndTime(date)).intValue();
        //原代码如下：查当天，被我注释掉
//        int startTime = DateUtil.getTimestamp(DateUtil.getDayBeginTime(new Date())).intValue();
//        int endTime = DateUtil.getTimestamp(DateUtil.getDayEndTime(new Date())).intValue();
        queryBuilder.must(QueryBuilders.rangeQuery("createTime").gt(startTime).lt(endTime));

        if (payType.contains("%")) {
            queryBuilder.must(QueryBuilders.queryStringQuery(payType.replace("%", "*")).field("payType"));
        } else {
            queryBuilder.must(QueryBuilders.termQuery("payType", payType));
        }

        //
        BoolQueryBuilder statusBuilder = QueryBuilders.boolQuery();
        //status状态为1:已支付,3:已对账
        statusBuilder.should(QueryBuilders.termQuery("status",1));
        statusBuilder.should(QueryBuilders.termQuery("status",3));
        statusBuilder.minimumShouldMatch(1);//必须匹配一个 or
        queryBuilder.must(statusBuilder);
        //

        //总支付金额
        SumAggregationBuilder sumAggrMoney = AggregationBuilders.sum("sum_pay_money").field("payMoney");
        //总退款额
        SumAggregationBuilder sumRefundMoney=AggregationBuilders.sum("sum_refund_money").field("refundMoney");

        ElasticsearchPersistentEntity<?> persistentEntity = elasticsearchTemplate.getPersistentEntityFor(PayOrderSearch.class);
        String indexname = persistentEntity.getIndexName();
        String type = persistentEntity.getIndexType();

        SearchQuery sumPaymoneySQ = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withIndices(indexname)
                .withTypes(type)
                .addAggregation(sumAggrMoney).build();
       BigDecimal sumPaymoney = elasticsearchTemplate.query(sumPaymoneySQ,response->{
            InternalSum internalSum =  (InternalSum) response.getAggregations().asList().get(0);
            return BigDecimal.valueOf(internalSum.getValue());
        });

        SearchQuery sumRefundmoneySQ = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withIndices(indexname)
                .withTypes(type)
                .addAggregation(sumRefundMoney).build();
        BigDecimal sumRefundmoney = elasticsearchTemplate.query(sumRefundmoneySQ,response->{
            InternalSum internalSum =  (InternalSum) response.getAggregations().asList().get(0);
            return BigDecimal.valueOf(internalSum.getValue());
        });



        return null;
    }

    @Override
    public PayOrderSearch selectByRefundNoMerchantId(Integer merchantId, String outRefundNo) {
        return null;
    }

    @Override
    public RefundMoneyStatisticsVO selectRefundedMoney(String payNo, Integer refundedTime) {
        return null;
    }

    @Override
    public long selectAllActiveRefundMoneyByPayNo(String payNo) {
        return 0;
    }

    @Override
    public SettlementInfoVO querySettlementInfo(Integer merchantId, Integer merchantType, Integer startTime, Integer endTime) {
        return null;
    }

    @Override
    public Long querySettlementProportionEntity(Integer merchantId, Integer merchantType, Integer startTime, Integer endTime, List<Integer> payTypeIds) {
        return null;
    }

    @Override
    public PageBean<PayOrderSearch> queryOrders(PayOrderSearchQueryVO query) {
        return null;
    }

    @Override
    public PageBean<PayOrderSearch> queryOrderFlowByPayNo(String payNo, Integer page, Integer size) {
        return null;
    }

    @Override
    public Long getRefundMoney(String payNo) {
        return null;
    }

    @Override
    public Map<String, Object> selectCountAndAmountByTime(Integer merchantId, Integer startTime, Integer endTime) {
        return null;
    }

    @Override
    public Long dealNopayOrderInfoByStatueTime(Integer status, int dataTime) {
        return null;
    }

    @Override
    public Long countRefundingOrderByPayNo(String payNo) {
        return null;
    }

//    @Override
//    public List<DairyTradeStatisticsPO> dateTradeStatistics(List<Integer> merchantIds, Integer passagewayId, String date) {
//        return null;
//    }

    @Override
    public List<PayOrderSearch> selectOrderByPayNoList(List<String> thousandPayNo) {
        return null;
    }

    @Override
    public List<PayOrderSearch> queryUnVerifyOrders(String billDate, int passagewayId) {
        return null;
    }



    @Override
    public void batchInsertPayOrderSearch(List<PayOrderSearch> payOrderSearchList) {
        try {
            payOrderSearchRepository.saveAll(payOrderSearchList);
            // 立即实现内存->文件系统缓存， 从而使文档可以立即被搜索到
            elasticsearchTemplate.refresh(PayOrderSearch.class);
        } catch (Exception e) {
            e.printStackTrace();
//            GwsLogger.error(e,"执行批量插入ES订单数据发生异常，原因:{}", e.getMessage());
        }
    }

    @Override
    public List<Long> selectRefundedMoneyList(String payNo) {
        return null;
    }

    @Override
    public PageBean<PayOrderSearch> queryRefundOrderByParentNo(int page, int pageSize, String... payNoArr) {
        return null;
    }

    @Override
    public Map<Integer, Long> querySettlementProportionEntityByPassageway(Integer merchantId, Integer merchantType, Integer startTime, Integer endTime) {
        return null;
    }
}
