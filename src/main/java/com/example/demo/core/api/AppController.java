package com.example.demo.core.api;

import com.example.demo.core.domain.EnvCd;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 어플리케이션 컨트롤러
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/server")
public class AppController {
    private final Environment environment;
    @Value("${custom.version}")
    private String version;
    @Value("${custom.title}")
    private String title;

    @ApiOperation(value = "어플리케이션 서버정보를 조회한다.", nickname = "retrieveServerInfo")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Ok") })
    @GetMapping(value = "/info")
    public String retrieveServerInfo() {
        // 현재 환경코드를 구한다.
        EnvCd env = EnvCd.codeOf(environment.getActiveProfiles()[0]).orElse(EnvCd.None);

        // 어플리케이션 서버정보를 담은 HTML를 작성한다.
        StringBuilder appInfo = new StringBuilder();
        appInfo.append("<html lang=\"ko\">");
        appInfo.append("<head><meta charset=\"UTF-8\"/></head>");
        appInfo.append("<body style=\"font-size:25px;\">");
        appInfo.append("<div style=\" position:absolute; border:black 2px solid; margin:auto; top:0; right:0; bottom:0; left:0; width:50%; height:50%;\">");
        appInfo.append("<div>");
        appInfo.append("<h2 style=\"text-align: center;\">Welcome ").append(title).append(" !</h2>");
        appInfo.append("</div>");
        appInfo.append("<ul>");
        appInfo.append("<li><b>Version</b> : ").append(version).append("</li>");
        appInfo.append("<li><b>Profile</b> : ").append(String.format("%s(%s)", env.getTitle(), env.name())).append("</li>");
        appInfo.append("</ul>");
        appInfo.append("</div>");
        appInfo.append("</body>");
        appInfo.append("</html>");

        return appInfo.toString();
    }
}
