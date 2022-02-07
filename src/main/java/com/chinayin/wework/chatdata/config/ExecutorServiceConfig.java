package com.chinayin.wework.chatdata.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author chianyin <whereismoney@qq.com>
 */
@Configuration
@Slf4j
@Data
@ConfigurationProperties(prefix = "pool")
public class ExecutorServiceConfig {

    // 核心线程池数
    private Integer corePoolSize = 10;
    // 最大线程池数
    private Integer maxPoolSize = 20;
    // 任务队列的容量
    private Integer queueCapacity = 10;
    // 非核心线程的存活时间
    private Integer keepAliveSeconds = 10;

    @Bean("threadPool")
    public ExecutorService newFixedThreadPool() {
        return new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveSeconds,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(queueCapacity),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }
}
