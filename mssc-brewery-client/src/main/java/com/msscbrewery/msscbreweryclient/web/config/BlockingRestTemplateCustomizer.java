package com.msscbrewery.msscbreweryclient.web.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
//@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = true)
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {
	private Integer maxtotal;
	private Integer conrectimeout;
	private Integer maxperroute;
	private Integer sockettimeout;
	
	
	
	public BlockingRestTemplateCustomizer(@Value("${sfg.maxtotal}") Integer maxtotal, 
			@Value("${sfg.conrectimeout}") Integer conrectimeout, 
			@Value("${sfg.maxperroute}") Integer maxperroute,
			@Value("${sfg.sockettimeout}") Integer sockettimeout) {
		super();
		this.maxtotal = maxtotal;
		this.conrectimeout = conrectimeout;
		this.maxperroute = maxperroute;
		this.sockettimeout = sockettimeout;
	}

	public ClientHttpRequestFactory clientHttpRequestFactory(){
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxtotal);
        connectionManager.setDefaultMaxPerRoute(maxperroute);

        RequestConfig requestConfig = RequestConfig
                .custom()
                .setConnectionRequestTimeout(conrectimeout)
                .setSocketTimeout(sockettimeout)
                .build();

        CloseableHttpClient httpClient = HttpClients
                .custom()
                .setConnectionManager(connectionManager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(requestConfig)
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }

}
