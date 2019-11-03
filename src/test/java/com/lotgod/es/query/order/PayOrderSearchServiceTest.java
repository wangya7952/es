package com.lotgod.es.query.order;

import com.lotgod.es.EsApplication;
import com.lotgod.es.entity.PayOrderSearch;
import com.lotgod.es.service.PayOrderSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PayOrderSearchServiceTest {

    @Autowired
    PayOrderSearchService payOrderSearchService;
    /**
     *根据订单id,查询订单记录
     */
    @Test
    public  void selectByPayNoTest(){
        String payNo="10030000222019080100001628321";
        PayOrderSearch payOrderSearch = payOrderSearchService.selectByPayNo(payNo);
        System.out.println(payOrderSearch);
    }

    /**
     * 根据商户id和通道交易id
     */
    @Test
    public  void selectByPassagewayTradeNoTest(){
        Integer merchantId=1000000111;
        String passagewayTradeNo="1846910331";
        PayOrderSearch payOrderSearch = payOrderSearchService.selectByPassagewayTradeNo(merchantId,passagewayTradeNo);
        System.out.println(payOrderSearch);
    }

    /**
     * 根据平台订单号，单条更新ES
     */
    @Test
    public  void updateRecordTest(){
        PayOrderSearch payOrderSearch = new PayOrderSearch();
        payOrderSearch.setPayNo("10000001112019083115211166055");
        payOrderSearch.setPayMoney(20l);//20分
        payOrderSearchService.updateRecord(payOrderSearch);
    }

    @Test
    public  void test2(){
        String payType ="5";
        String dateStr  = "2019-08-24";
        payOrderSearchService.selectMerchantTodayTurnoverByPayType(null,payType,dateStr);
    }



}
