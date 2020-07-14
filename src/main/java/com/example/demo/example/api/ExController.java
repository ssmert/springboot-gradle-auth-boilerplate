package com.example.demo.example.api;

import com.example.demo.core.infrastructure.constant.YesOrNo;
import com.example.demo.example.api.transferobject.ExRequest;
import com.example.demo.example.api.transferobject.ExResponse;
import com.example.demo.example.api.transferobject.PagingExResponse;
import com.example.demo.example.application.ChangeExService;
import com.example.demo.example.application.RetrieveExService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * 예제 REST 컨트롤러이다.
 *
 * @author jonghyeon
 */
@RestController
@AllArgsConstructor
@RequestMapping("/examples")
public class ExController {
    /**
     * 예제 조회 서비스
     */
    private final RetrieveExService retrieveExService;

    /**
     * 예제 변경 서비스
     */
    private final ChangeExService changeExService;

    @ApiOperation(value = "예제 목록을 조회한다.", nickname = "retrieveExList")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = PagingExResponse.class) })
    @GetMapping(value = "")
    public @ResponseBody
    ResponseEntity<PagingExResponse> retrieveExList(@RequestParam(value = "exUseYn", required = false) YesOrNo exUseYn,
            @RequestParam(value = "exTitle", required = false, defaultValue = "") String exTitle,
            @RequestParam(value = "startRegDate", required = false) @DateTimeFormat(pattern = "yyyyMMdd") LocalDate startRegDate,
            @RequestParam(value = "endRegDate", required = false) @DateTimeFormat(pattern = "yyyyMMdd") LocalDate endRegDate,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        // 예제 목록을 조회한다.
        PagingExResponse response = retrieveExService.retrieveExList(exUseYn, exTitle, startRegDate, endRegDate, page, size);

        // 응답(성공)을 설정한다.
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "예제를 조회한다.", nickname = "retrieveEx")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ExResponse.class) })
    @GetMapping(value = "/{ex-seq}")
    public @ResponseBody
    ResponseEntity<ExResponse> retrieveEx(@PathVariable("ex-seq") long exSeq) {
        // 예제를 조회한다.
        ExResponse response = retrieveExService.retrieveEx(exSeq);

        // 응답(성공)을 설정한다.
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "예제를 등록한다.", nickname = "registerEx")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
    @PostMapping(value = "")
    public @ResponseBody
    HttpEntity<Void> registerCh(@RequestBody ExRequest exRequest) {
        // 예제를 등록한다.
        changeExService.registerEx(exRequest);

        // 응답(성공)을 설정한다.
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "예제를 수정한다.", nickname = "editEx")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
    @PutMapping(value = "/{ex-seq}")
    public @ResponseBody
    HttpEntity<Void> editEx(@PathVariable("ex-seq") long exSeq, @RequestBody ExRequest exRequest) {
        // 예제를 수정한다.
        changeExService.editEx(exSeq, exRequest);

        // 응답(내용없음)을 설정한다.
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
