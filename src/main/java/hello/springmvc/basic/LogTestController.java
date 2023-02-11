package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestController {

    private final Logger log = LoggerFactory.getLogger(getClass());  // 현재 클래스를 지정

    @GetMapping("/log-test")
    public String logTest() {
        String name = "spring";
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);  // 현재 로그는 디버그 할 때 보는거야(개발 서버)
        log.info("info log={}", name);  // 운영 시스템에서도 봐야할 중요한 정보
        log.warn("warn log={}", name);  // 경고
        log.error("error log={}", name);  // 에러, 빠르게 찾아서 봐야 함
        return "ok";
    }

}
