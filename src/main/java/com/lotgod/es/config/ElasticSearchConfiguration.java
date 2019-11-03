package com.lotgod.es.config;


import lombok.Data;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.annotation.PreDestroy;
import java.net.InetAddress;

/**
 * ES配置
 * 
 * @author Leiyongping
 *
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.lotgod.es.repositories")
@Data
public class ElasticSearchConfiguration {
	@Value("${spring.data.elasticsearch.cluster-nodes}")
	private String clusterNodes;

	@Value("${spring.data.elasticsearch.cluster-name}")
	private String clusterName;

	@Autowired
	@Qualifier("esTransportClient")
	private Client tc;

	@Bean(name = "esTransportClient")
	public Client client() {
		PreBuiltTransportClient client = null;
		try {
			Settings settings = Settings.builder().put("cluster.name", clusterName)
					.put("client.transport.sniff", false)
					.put("client.transport.ping_timeout", "20s") // 节点连接时间30秒，响应超时,默认5s
					.put("client.transport.nodes_sampler_interval", "20s") // 多长时间样本/ ping列出的节点和连接。默认为 5s
					.put("client.transport.ignore_cluster_name", true) // 连接节点使忽略集群名称验证
					.build();
			client = new PreBuiltTransportClient(settings);
			if (!"".equals(clusterNodes)) {
				for (String nodes : clusterNodes.split(",")) {
					String InetSocket[] = nodes.split(":");
					String Address = InetSocket[0];
					Integer port = Integer.valueOf(InetSocket[1]);
					client.addTransportAddress(new TransportAddress(InetAddress.getByName(Address), port));
				}
//				GwsLogger.info("初始化PreBuiltTransportClient success");
			}
		} catch (Exception e) {
			e.printStackTrace();
//			GwsLogger.error(e.getMessage());
		}
		return client;
	}

	@Bean
	public GwsElasticsearchTemplate elasticsearchTemplate() {
		return new GwsElasticsearchTemplate(tc);
	}

	@PreDestroy
	public void destoryClient() {
		try {
//			GwsLogger.info("Closing elasticSearch  client");
			if (tc != null) {
				tc.close();
			}
		} catch (final Exception e) {
			e.printStackTrace();
//			GwsLogger.error(e, "Error closing ElasticSearch client: " + e.getMessage());
		}
	}


}
