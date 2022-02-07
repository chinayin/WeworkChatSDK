package com.chinayin.wework.chatdata;

import com.tencent.wework.Finance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author chianyin <whereismoney@qq.com>
 */
@SpringBootApplication
@EnableScheduling
@Slf4j
public class WeworkApplication {

    public static void main(String[] args) {
        // 检查sdk
        try {
            long sdk = Finance.NewSdk();
            Finance.DestroySdk(sdk);
        } catch (Exception ex) {
            log.error("WeWorkSdk.NewSdk error, ", ex.getMessage());
            System.exit(1);
        }
        new SpringApplicationBuilder(WeworkApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

}
