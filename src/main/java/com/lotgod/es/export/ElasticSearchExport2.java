package com.lotgod.es.export;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.*;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author rstyro
 */
public class ElasticSearchExport2 {

    public static void main(String[] args) throws Exception {
        // String clustName = "es-cluster";//公司es集群名字
        //String clusttIp = "192.168.10.52"; //公司es地址

        String clustName = "my-application";
        String indexName = "payment_order";

        String clusttIp = "192.168.10.152";//罗总虚拟机
        int clustPort = 9300;
        String filePath = "F:\\es_data\\order_share_profit.json";
        String typeName = "payment_order";
//        outToFile(clustName, indexName, typeName, clusttIp, clustPort, filePath);
//        fileToEs(clustName, indexName, typeName, clusttIp, clustPort, filePath);
        insert(clustName, indexName, typeName, clusttIp, clustPort, filePath);
    }

    /**
     * elasticsearch 数据到文件
     *
     * @param clustName  集群名称
     * @param indexName  索引名称
     * @param typeName   type名称
     * @param sourceIp   ip
     * @param sourcePort transport 服务端口
     * @param filePath   生成的文件路径
     */
    public static void outToFile(String clustName, String indexName, String typeName, String sourceIp, int sourcePort, String filePath) throws Exception {


        TransportClient client = createClient(clustName, sourceIp, sourcePort);
        SearchRequestBuilder builder = client.prepareSearch(indexName);
        if (typeName != null) {
            builder.setTypes(typeName);
        }


        SearchResponse response = builder
                .setQuery(QueryBuilders.matchAllQuery()).setSize(10000).setScroll(new TimeValue(600000))
                .setSearchType(SearchType.DEFAULT).execute().actionGet();//setSearchType(SearchType.Scan) 告诉ES不需要排序只要结果返回即可 setScroll(new TimeValue(600000)) 设置滚动的时间
        String scrollid = response.getScrollId();
        try {
            //把导出的结果以JSON的格式写到文件里
            BufferedWriter out = new BufferedWriter(new FileWriter(filePath, true));

            //每次返回数据10000条。一直循环查询直到所有的数据都查询出来
            while (true) {
                SearchResponse response2 = client.prepareSearchScroll(scrollid).setScroll(new TimeValue(1000000))
                        .execute().actionGet();
                SearchHits searchHit = response2.getHits();
                //再次查询不到数据时跳出循环
                if (searchHit.getHits().length == 0) {
                    break;
                }
                System.out.println("查询数量 ：" + searchHit.getHits().length);
                for (int i = 0; i < searchHit.getHits().length; i++) {
                    String json = searchHit.getHits()[i].getSourceAsString();
                    out.write(json);
                    out.write("\r\n");
                }
            }
            System.out.println("查询结束");
            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    /**
     * 把json 格式的文件导入到elasticsearch 服务器
     *
     * @param clustName  集群名称
     * @param indexName  索引名称
     * @param typeName   type 名称
     * @param sourceIp   ip
     * @param sourcePort 端口
     * @param filePath   json格式的文件路径
     */
    @SuppressWarnings("deprecation")
    public static void fileToEs(String clustName, String indexName, String typeName, String sourceIp, int sourcePort, String filePath) throws Exception {

        TransportClient client = createClient(clustName, sourceIp, sourcePort);
        try {
            //把导出的结果以JSON的格式写到文件里
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String json = null;
            int count = 0;
            boolean b=false;
            //开启批量插入
            BulkRequestBuilder bulkRequest = client.prepareBulk();
            while ((json = br.readLine()) != null) {
                HashMap<String, Object> dataAsMap = processJson(json);
                bulkRequest.add(client.prepareIndex(indexName, typeName).setSource(dataAsMap));
                //每一千条提交一次
                count++;
                if (count % 1000 == 0) {
                    System.out.println("本次提交了1000条");
                    bulkRequest.execute().actionGet();

//                    BulkResponse bulkResponse = bulkRequest.execute().actionGet();
//                    if (bulkResponse.hasFailures()) {
//                        System.out.println("message:" + bulkResponse.buildFailureMessage());
//                    }
                    //重新创建一个bulk
//                    bulkRequest = client.prepareBulk();
                }
            }
            bulkRequest.execute().actionGet();
            System.out.println("总提交了：" + count);
            br.close();
            client.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static  void insert(String clustName, String indexName, String typeName, String sourceIp, int sourcePort, String filePath) throws Exception {
//        Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "elasticsearch-bigdata").build();
//        Client client = new TransportClient(settings)
//                .addTransportAddress(new InetSocketTransportAddress("10.58.71.6", 9300));
        TransportClient client = createClient(clustName, sourceIp, sourcePort);
        try {
            //读取刚才导出的ES数据
            BufferedReader br = new BufferedReader(new FileReader("es"));
            String json = null;
            int count = 0;
            //开启批量插入
            BulkRequestBuilder bulkRequest = client.prepareBulk();
            while ((json = br.readLine()) != null) {
                HashMap<String, Object> dataAsMap = processJson(json);
                bulkRequest.add(client.prepareIndex("payment_order", "payment_order").setSource(dataAsMap));
                //每一千条提交一次
                if (count% 1000==0) {
                    bulkRequest.execute().actionGet();
                    System.out.println("提交了：" + count);
                }
                count++;
            }
            bulkRequest.execute().actionGet();
            System.out.println("插入完毕");
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static  HashMap<String, Object> processJson(String json){
        Map<String, Object> itemMap = JSONObject.toJavaObject(JSONObject.parseObject(json), Map.class);
        HashMap<String, Object> dataAsMap = new HashMap<String, Object>(itemMap);
        Iterator iter = dataAsMap.keySet().iterator();
        boolean b=false;
        while (iter.hasNext()) {
            Object key = iter.next();
            Object val = dataAsMap.get(key);
            if(val!=null && val.toString().indexOf(".")>-1){
                b = isDouble(val.toString());
                if(b) {
                    dataAsMap.put(key.toString(), Double.valueOf(val.toString()));
                }
            }
        }
        return  dataAsMap;
    }
    public static   boolean isDouble(String str)
    {
        try
        {
            Double.parseDouble(str);
            return true;
        }
        catch(NumberFormatException ex){}
        return false;
    }
    /**
     * 建立连接
     * @param cluster
     * @param ip
     * @param port
     * @return
     * @throws Exception
     */
    private static TransportClient createClient(String cluster, String ip, Integer port2) throws Exception {

        PreBuiltTransportClient client = null;
        try {
            Settings settings = Settings.builder().put("cluster.name", cluster)
                    .put("client.transport.sniff", false)
                    .put("client.transport.ping_timeout", "20s") // 节点连接时间30秒，响应超时,默认5s
                    .put("client.transport.nodes_sampler_interval", "20s") // 多长时间样本/ ping列出的节点和连接。默认为 5s
                    .put("client.transport.ignore_cluster_name", true) // 连接节点使忽略集群名称验证
                    .build();
            client = new PreBuiltTransportClient(settings);
            //
//            client.addTransportAddress(new TransportAddress(InetAddress.getByName(ip),
//                    port));

            //
            String clusterNodes = "192.168.10.52:9300,192.168.10.53:9300,192.168.10.51:9300";
            if (!"".equals(clusterNodes)) {
                for (String nodes : clusterNodes.split(",")) {
                    String InetSocket[] = nodes.split(":");
                    String Address = InetSocket[0];
                    Integer port = Integer.valueOf(InetSocket[1]);
                    client.addTransportAddress(new TransportAddress(InetAddress.getByName(Address), port));
                }
//                GwsLogger.info("初始化PreBuiltTransportClient success");
            }
        } catch (Exception e) {
            e.printStackTrace();
//            GwsLogger.error(e.getMessage());
        }
        return client;
        /*
        TransportClient client = null;
        if (client == null) {
            synchronized (ElasticSearchExport.class) {
                Settings settings = Settings.settingsBuilder().put("cluster.name", cluster).build();
                client = TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ip),
                        port));
            }
        }
        return client;
        */
    }
}
