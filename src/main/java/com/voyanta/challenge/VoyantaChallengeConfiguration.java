package com.voyanta.challenge;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
@EnableJpaRepositories
@EnableCaching
public class VoyantaChallengeConfiguration implements AsyncConfigurer {

	private static final int MAX_POOL_SIZE = 42;
	private static final int THREAD_POOL_SIZE = 10;
	private static final String THREAD_PREFIX = "AdditionService-";

	public static void main(String[] args) {
		SpringApplication.run(VoyantaChallengeConfiguration.class, args);
	}

	@Override
	public Executor getAsyncExecutor() {
		// FIXME: configure the thread pool in the application server
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(THREAD_POOL_SIZE);
		taskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
		taskExecutor.setThreadNamePrefix(THREAD_PREFIX);
		taskExecutor.initialize();
		return taskExecutor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new SimpleAsyncUncaughtExceptionHandler();
	}

	@Bean
	public CacheManager getEhCacheManager() {
		return new EhCacheCacheManager(getEhCacheFactory().getObject());
	}

	@Bean
	public EhCacheManagerFactoryBean getEhCacheFactory() {
		EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
		factoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
		factoryBean.setShared(true);
		return factoryBean;
	}
}
