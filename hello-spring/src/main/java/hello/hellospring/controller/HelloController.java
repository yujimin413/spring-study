package hello.hellospring.controller;

import hello.hellospring.HelloSpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");  // hello.html의 ${data}에 키값인 "hello!!"가 들어감
        return "hello"; // src/resources/templates/hello.html 로 가서 렌더링해라.
    }

    // 2. MVC와 템플릿 엔진
    // 외부에서 파라미터 받기 위해 @RequestParam 어노테이션 사용
    // tip) cmd+p : 변수의 option 확인
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // 3. API : 텍스트 그대로 전달 (inspector로 확인)
    @GetMapping("hello-string")
    @ResponseBody   // api는 이 어노테이션 써줘야 함
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    // 3. API
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {  // http://localhost:8080/hello-api?name=spring!!!
        // tip) cmd+shift+enter -> 자동완성
        Hello hello = new Hello();  // 인스턴스 만들고
        hello.setName(name);    // name 변수에 값 할당
        return hello;   // 인스턴스 자체를 리턴. default는 JSON 방식으로 데이터 만들어서(JsonConverter) HTTP 응답에 반환하겠다는게 기본 정책
    }

    static class Hello {
        private String name;

        // tip) name 변수 선언하고 cmd+N 단축키 -> getter, setter 자동 생성 가능
        // Getter, Setter 개념.. "property 접근 방식"이라고 함
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
