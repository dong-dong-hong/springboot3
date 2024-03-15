package com.springboot.springbootbook.controller;

import com.springboot.springbootbook.dto.ArticleForm;
import com.springboot.springbootbook.entity.Article;
import com.springboot.springbootbook.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    private final ArticleRepository articleRepository;
    @Autowired



    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "article/new";
    }

    /*
        action, method 속성 정보 설정하고나서 서버의 컨트롤러가 정보 조합 사용자가 전송한 폼 데이터 받음
        1. 컨트롤러에 createArticle() 메서드를 추가하고 형식을 맞추기 위해 return 값에는 빈 문자를 적는다.
        2. 그 다음 URL 요청을 받아 와야 하는데, 여기서는 GetMapping()을 사용하지 않고 @PostMapping()을 사용한다.
        뷰 페이지에서 폼 데이터를 Post 방식으로 전송했으므로 컨트롤러에서 받을 때도 @PostMapping()으로 받는다.
        이때 괄호 안에는 받는 URL 주소를 넣는다. new.mustache에서 <form> 태그에 action="/articles/create"로 설정했으므로
        @PostMapping("/articles/create")로 작성한다.
     */
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        System.out.println(form);
        // 1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        System.out.println(article); // DTO가 엔티티로 잘 변환되는지 확인 출력
        // 2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        System.out.println(saved); // article이 DB에 잘 저장되는지 확인 출력
        return "";
    }
}
