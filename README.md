<div align="center">
    <h2>스프링 MVC 1편 - 기본기능 및 실습</h2>
</div>

#### 로깅 간단히 알아보기
- 실무에서는 sout을 사용하지 않고 별도의 로깅 라이브러리를 사용한다.

- 로깅 라이브러리
  - 스프링 부트 라이브러리를 사용하면 스프링 부트 로깅 라이브러리(`sping-boot-starter-logging`)가 함께 포함된다.
  - 이 라이브러리는 기본으로 다음 로깅 라이브러리를 사용한다.
    - SLF4J : 인터페이스
    - Logback :  그 구현체로 Logback을 사용한다.

#### @RestControlelr vs @Controller
- RestController : 컨트롤러에서 문자열을 반환하면 해당 문자열을 HTTP 메시지 바디에 바로 입력한다.
  - `@ResponseBody`와 관련이 있다.
- Controller : 컨트롤러에서 문자열을 반환하면 해당 문자열을 이름으로 갖는 view를 찾아서 view가 렌더링된다.

#### log의 장점
- 로그의 레벨을 지정할 수 있다.
![img.png](img/img_1.png)

- 프로세스 id, 스레드 id 부터 클래스명 등 많은 내용을 볼 수 있다.
`2023-02-11 21:32:33.308  INFO 20728 --- [nio-8080-exec-1] hello.springmvc.basic.LogTestController  :  info log=spring`
- 로그를 콘솔에 출력도 할 수 있고 `파일`로도 기록을 남길 수 있다.
- sout보다 성능이 좋다(내부 버퍼링, 멀티 쓰레드 등), 로그는 성능이 극한으로 설정되어 있다.
- 모든 로그를 보고 싶은 경우
  - `application.properties`에 로그 레벨을 지정하면 된다.
  - 로그 레벨은 아래와 같은 순서, 상위 등급으로 설정할 수록 하위 등급 로그를 더 볼 수 있다.
  - LEVEL: TRACE > DEBUG > INFO > WARN > ERROR 
  ```groovy
  log.trace("trace log={}", name);
  log.debug("debug log={}", name);  // 현재 로그는 디버그 할 때 보는거야(개발 서버)
  log.info("info log={}", name);  // 운영 시스템에서도 봐야할 중요한 정보
  log.warn("warn log={}", name);  // 경고
  log.error("error log={}", name);
  ```
- 로그 레벨 설정
```groovy
#전체 로그 레벨 설정(기본 info) 
logging.level.root=info

#hello.springmvc 패키지와 그 하위 로그 레벨 설정 
logging.level.hello.springmvc=debug
```
- 운영서버와 개발서버에서 로그의 레벨을 다르게 설정해서 사용을 한다.
  - 설정만으로 로그 출력 모양을 조절할 수 있다는 점도 장점이다. 
  - 그렇게 안하고 sout을 사용하여 출력을 시킨다면 이런 설정이 불가능하다.
  - 개발 서버는 debug 출력
  - 운영 서버는 info 출력
- 올바른 로그 사용법
  - `log.debug("data="+data`
  - 로그 출력 레벨을 info로 하더라도 해당 문자열을 합치는 연산이 발생한다.
  - 즉 출력을 하지도 않는데 문자열을 합치는 연산이 일어나서 메모리가 낭비되는 것이 문제점이다.
  - 따라서 이는 사용은 가능하지만 절대 사용하면 안되는 방법이다.
