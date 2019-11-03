package com.lotgod.common.elasticsearch.query;


import com.lotgod.utils.elasticsearch.BaseEsQuery;
import com.lotgod.utils.elasticsearch.EsWhere;
import com.lotgod.utils.elasticsearch.annotation.EsBindAttrField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class PayOrderEsQuery extends BaseEsQuery {

    @EsBindAttrField(fieldName = "merchantId", where = EsWhere.equal)
    private Integer merchantId;

    @EsBindAttrField(fieldName = "outTradeNo", where = EsWhere.equal)
    private String outTradeNo;

    @EsBindAttrField(fieldName = "ordersType", where = EsWhere.equal)
    private Integer ordersType;

    @EsBindAttrField(fieldName = "payType", where = EsWhere.equal)
    private String payType;

    @EsBindAttrField(fieldName = "authCode", where = EsWhere.equal)
    private String authCode;

    @EsBindAttrField(fieldName = "passagewayTradeNo", where = EsWhere.equal)
    private String passagewayTradeNo;

}
