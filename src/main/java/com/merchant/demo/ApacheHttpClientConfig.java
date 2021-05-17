package com.merchant.demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;
import javax.net.ssl.SSLContext;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ApacheHttpClientConfig {
	private static ScheduledExecutorService httpIdleConnMonitorExecutor = Executors.newSingleThreadScheduledExecutor();
	
	@Bean
	public static PoolingHttpClientConnectionManager globalPoolingHttpClientConnectionManager() {
        SSLContext sslcontext = SSLContexts.createSystemDefault();
		
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslcontext))
                .build();
		
		PoolingHttpClientConnectionManager connManager 
		= new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		
		
        // Create socket configuration
        SocketConfig socketConfig = SocketConfig.custom()
            .setTcpNoDelay(true)
            .build();
        // Configure the connection manager to use socket configuration either
        // by default or for a specific host.
        connManager.setDefaultSocketConfig(socketConfig);
        // Validate connections after 3 sec of inactivity
        connManager.setValidateAfterInactivity(3000);
        
        // Configure total max or per route limits for persistent connections
        // that can be kept in the pool or leased by the connection manager.
        connManager.setMaxTotal(100);
        connManager.setDefaultMaxPerRoute(50);
		
		return connManager;
	}
	
	@Bean
	public static RequestConfig globalRequestConfig() {
        RequestConfig globalRequestConfig = RequestConfig.custom()
                .setSocketTimeout(30 * 1000)
                .setConnectTimeout(30 * 1000)
                .setConnectionRequestTimeout(30 * 1000)
                .build();
        return globalRequestConfig;
	}
	
	@Bean 
	public static CloseableHttpClient globalHttpClient() throws InterruptedException, ExecutionException {
        CloseableHttpClient httpclient = HttpClients.custom()
                .setConnectionManager(globalPoolingHttpClientConnectionManager())
                .setDefaultRequestConfig(globalRequestConfig())
                .build();
        
        httpIdleConnMonitorExecutor.scheduleWithFixedDelay(idleConnectionClose(globalPoolingHttpClientConnectionManager()), 30, 30, TimeUnit.SECONDS);
        
        return httpclient;
	}
	
	@Bean
	public static Runnable idleConnectionClose(HttpClientConnectionManager connManager) {
		
		Runnable idleConnCloseTask = ( () -> {
			if (connManager != null ) {
				connManager.closeExpiredConnections();    // Close expired connections
				connManager.closeIdleConnections(30, TimeUnit.SECONDS);
				
				if (connManager instanceof PoolingHttpClientConnectionManager) {
					PoolingHttpClientConnectionManager con =(PoolingHttpClientConnectionManager) connManager;
					log.trace(con.getTotalStats().toString());
				}
			}
		});
		
		return idleConnCloseTask;
	}
	
	@PreDestroy
	public void destroy() {
		if (httpIdleConnMonitorExecutor == null)  
			return;
		
		httpIdleConnMonitorExecutor.shutdown();
		try {
			if (! httpIdleConnMonitorExecutor.awaitTermination(31, TimeUnit.SECONDS)) {
				httpIdleConnMonitorExecutor.shutdownNow();
			}
		} catch (InterruptedException e) {
			httpIdleConnMonitorExecutor.shutdownNow();
		}
	}
	
	
	public static HttpHost getProxyHostWithFixedRoutingStrategy() {
		
//		switch (Profile.ACTIVE_PROFILE) {
//		case LOCAL:
//			return null;
//		case DEV:
//			return new HttpHost("211.181.255.222", 3128);
//		case REAL_01:
//			return new HttpHost("211.38.41.196", 3128);
//		case REAL_02:
//			return new HttpHost("211.38.41.197", 3128);
//		defausslt:
			return null;
	}

}
