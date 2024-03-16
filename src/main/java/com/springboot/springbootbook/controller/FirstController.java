package com.springboot.springbootbook.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Model 클래스 패키지 자동 임포트
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class FirstController {



    /*
    컨트롤러 동작 이해
    1. 이 파일이 컨트롤러임을 선언한다.
    2. 클라이언트로부터 "/h1"라는 요청을 받아 접수한다.
    3. "h1"라는 요청을 받음과 동시에 niceToMeetYou() 메서드를 수행한다.
    4. 뷰 템플릿 페이지에서 사용할 변수를 등록하기 위해 모델 객체를 매개변수로 가져온다.
    5. 모델에서 사용할 변수를 등록한다. 변숫값에 따라 서로 다른 뷰 템플릿 페이지가 출력된다.
    6. 메서드를 수행한 결과로 greetings.mustache 파일을 반환한다. 이때 return 문에는 파일 이름만 작성하면 된다.
    (return "greetings";). 그러면 서버가 알아서 templates 디렉터리에 있는 해당 뷰 템플릿 페이지를 찾아 웹 브라우저로 전송한다.
     */

    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {
        // 형식 mode.addAttribute("변수명", 변숫값);
        model.addAttribute("username", "동동"); // 변숫값을 "변수명"이라는 이름으로 추가
        // model 객체가 "동동" 값을 "username"에 연결해 웹 브라우저로 보냄
        return "greetings"; // greetings.mustache 파일 반환
    }

    @GetMapping("/bye")
    public String seeYouLater(Model model) {
        model.addAttribute("nickname", "슬러브");
        return "goodbye";
    }

    @GetMapping("/random-quote")
    public String randomQuote(Model model) {
        String[] quotes = {
                "행복은 습관이다. 그것을 몸에 지니라. " +
                        "-허버드-" ,
                "고개 숙이지 마십시오. 세상을 똑바로 정면으로 " +
                        "바라보십시오. -헬렌 켈러-",
                "고난의 시기에 동요하지 않는 것, 이것은 진정 " +
                        "칭찬받을 만한 뛰어난 인물의 증거다. -베토벤-",
                "당신이 할 수 있다고 믿든 할 수 업다고 믿든 " +
                        "믿는 대로 될 것이다. -헨리 포드-",
                "나의 죽음을 적들에게 알리지 마라. " +
                        "-이순신-"
        };
        int randInt = (int) (Math.random() * quotes.length);
        model.addAttribute("randomQuote", quotes[randInt]);
        return "quote";
    }
}
