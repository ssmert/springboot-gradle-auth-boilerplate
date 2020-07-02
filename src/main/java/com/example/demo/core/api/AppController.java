package com.example.demo.core.api;

import com.example.demo.core.infrastructure.constant.EnvCd;
import com.example.demo.core.util.CheckUtil;
import com.example.demo.core.util.DateUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 어플리케이션에 대한 컨트롤러이다.
 *
 * @author jonghyeon
 */
@Slf4j
@NoArgsConstructor
@RestController
@RequestMapping("/server")
public class AppController {
    /**
     * 스프링 프래임워크 환경정보
     */
    @Autowired
    private Environment environment;

    /**
     * 서버 버전정보
     */
    @Value("${demo.info.version}")
    private String SERVER_VERSION;


    @ApiOperation(value = "어플리케이션 서버정보를 조회한다.", nickname = "retrieveServerInfo")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "Success")})
    @GetMapping(value = "/info")
    public String retrieveServerInfo() {
        // 현재 환경코드를 구한다.
        EnvCd curEnvCd = EnvCd.codeOf(CheckUtil.nvl(environment.getActiveProfiles()[0], EnvCd.Production.getCode()));

        // 어플리케이션 서버정보를 담은 HTML를 작성한다.
        StringBuilder appInfo = new StringBuilder();
        appInfo.append("<html lang=\"ko\">");
        appInfo.append("<head><meta charset=\"UTF-8\"/></head>");
        appInfo.append("<body style=\"font-size:25px;\">");
        appInfo.append("<div style=\" position:absolute; border:black 2px solid; margin:auto; top:0; right:0; bottom:0; left:0; width:50%; height:50%;\">");
        appInfo.append("<div>");
        appInfo.append("<h2 style=\"text-align: center;\">Welcome Demo !</h2>");
        appInfo.append("</div>");
        appInfo.append("<ul>");
        appInfo.append("<li><b>Version</b> : ").append(SERVER_VERSION).append("</li>");
        appInfo.append("<li><b>Profile</b> : ").append(String.format("%s(%s)", curEnvCd.getTitle(), curEnvCd.name())).append("</li>");
        appInfo.append("</ul>");
        appInfo.append("</div>");
        appInfo.append("</body>");
        appInfo.append("</html>");

        return appInfo.toString();
    }

    @ApiOperation(value = "디렉토리 조회", nickname = "retrieveFileList")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "Success")})
    @GetMapping(value = "/files")
    public String retrieveFileList(@RequestParam(value = "path", required = true) String path) {
        StringBuilder appInfo = new StringBuilder();
        appInfo.append("<html lang=\"ko\">");
        appInfo.append("<head><meta charset=\"UTF-8\"/></head>");
        appInfo.append("<body style=\"font-size:25px;\">");
        appInfo.append("<div style=\" position:absolute; border:black 2px solid; margin:auto; top:0; right:0; bottom:0; left:0; width:50%; height:50%;\">");
        appInfo.append("<div>");
        appInfo.append("<h2 style=\"text-align: center;\">Server Files !</h2>");
        appInfo.append("</div>");
        appInfo.append("<ul style=\"font-size:15px;\">");

        File dir = new File(path);
        for (File file : dir.listFiles()) {
            if (file.isFile()) {
                LocalDateTime dt = LocalDateTime.ofInstant(Instant.ofEpochMilli(file.lastModified()), ZoneId.of("Asia/Seoul"));
                appInfo.append(
                        String.format("<li><b>File</b> : %s, Size: %s Byte,  변경일 : %s", file.getName(), file.length() / 1000.0, DateUtil.toYmsDashString(dt)));
            }
        }

        appInfo.append("</ul>");
        appInfo.append("</div>");
        appInfo.append("</body>");
        appInfo.append("</html>");

        return appInfo.toString();
    }
}
