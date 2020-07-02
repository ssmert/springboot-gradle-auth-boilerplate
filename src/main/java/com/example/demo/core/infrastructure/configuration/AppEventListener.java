package com.example.demo.core.infrastructure.configuration;

import com.example.demo.core.infrastructure.constant.EnvCd;
import com.example.demo.core.util.CheckUtil;
import com.example.demo.core.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.time.LocalDateTime;

/**
 * 어플리케이션 서버 시작과 종료 이벤트를 수신하는 리스너이다.
 *
 * @author jonghyeon
 */
@Slf4j
@Component
public class AppEventListener {
    /**
     * 스프링 환경 제공자
     */
    @Autowired
    private Environment environment;

    /**
     * 서버 버전정보
     */
    @Value("${demo.info.version}")
    private String SERVER_VERSION;

    /**
     * 어플리케이션 서버 시작 시 동작한다.
     *
     * @param event 이벤트
     */
    @EventListener
    public void onStartUp(ApplicationReadyEvent event) {

        // 현재 환경코드를 구한다.
        EnvCd curEnvCd = EnvCd.codeOf(CheckUtil.nvl(environment.getActiveProfiles()[0], EnvCd.Production.getCode()));

        // 어플리케이션 서버정보를 출력한다.
        if (log.isInfoEnabled()) {
            log.info("");
            log.info("############################################    Demo Server Start Up   ###########################################");
            log.info("--------------------------------------------         INFORMATION       -------------------------------------------");
            log.info(String.format("#    Start Up Time : %s", DateUtil.toYmsString(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss")));
            log.info(String.format("#    Version       : %s", SERVER_VERSION));
            log.info(String.format("#    Profile       : %s", String.format("%s(%s)", curEnvCd.getTitle(), curEnvCd.name())));
            log.info("##################################################################################################################");
            log.info("");
        }
    }

    /**
     * 어플리케이션 서버 종료 시 동작한다.
     */
    @PreDestroy
    public void onShutDown() {
        if (log.isInfoEnabled()) {
            log.info("");
            log.info("######################################         Demo Server ShutDown       ######################################");
            log.info(String.format("#    ShutDown Time : %s", DateUtil.toYmsString(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss")));
            log.info("#################################################################################################################");
            log.info("");

        }
    }
}