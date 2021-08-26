package com.example.demo.core.config;

import com.example.demo.core.domain.EnvCd;
import com.example.demo.core.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.time.LocalDateTime;

/**
 * 서버 시작, 종료 이벤트 수신 리스너
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AppEventListener {
    // 스프링 환경
    private final Environment environment;

    // 서버 버전정보
    @Value("${custom.version}")
    private String version;

    /**
     * 서버 시작 시 동작한다.
     *
     * @param event 이벤트
     */
    @EventListener
    public void onStartUp(ApplicationReadyEvent event) {
        // 현재 환경코드를 구한다.
        EnvCd curEnvCd = EnvCd.codeOf(environment.getActiveProfiles()[0]);

        // 어플리케이션 서버정보를 출력한다.
        log.info("");
        log.info("############################################    PAMS Server Start Up   ###########################################");
        log.info("--------------------------------------------         INFORMATION       -------------------------------------------");
        log.info("#    Start Up Time : {}", DateUtil.toYmsString(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss"));
        log.info("#    Version       : {}", version);
        log.info("#    Profile       : {}", String.format("%s(%s)", curEnvCd.getTitle(), curEnvCd.name()));
        log.info("##################################################################################################################");
        log.info("");
    }

    /**
     * 서버 종료 시 동작한다.
     */
    @PreDestroy
    public void onShutDown() {
        if (log.isInfoEnabled()) {
            log.info("");
            log.info("######################################         PAMS Server ShutDown       ######################################");
            log.info("#    ShutDown Time : {}", DateUtil.toYmsString(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss"));
            log.info("#################################################################################################################");
            log.info("");

        }
    }
}