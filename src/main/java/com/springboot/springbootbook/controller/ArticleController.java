package com.springboot.springbootbook.controller;

import com.springboot.springbootbook.dto.ArticleForm;
import com.springboot.springbootbook.entity.Article;
import com.springboot.springbootbook.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Slf4j // 로깅 기능을 쓰기 위해 추가
@Controller
public class ArticleController {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
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
    public String createArticle(ArticleForm form) {
        log.info(form.toString());
//        System.out.println(form);
        // 1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        log.info(article.toString());
//        System.out.println(article); // DTO가 엔티티로 잘 변환되는지 확인 출력
        // 2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
//        System.out.println(saved); // article이 DB에 잘 저장되는지 확인 출력
        return "";
    }

    @GetMapping("/articles/{id}") // @PathVariable: URL 요청으로 들어온 전달값을 컨트롤러의 매개변수로 가져오는 어노테이션
    public String show(@PathVariable Long id, Model model) { // 매개변수로 id 받아오기
        log.info("id = " + id);
        // id를 조회해 데이터 가져오기
//        Optional<Article> articleEntity = articleRepository.findById(id);
         Article articleEntity = articleRepository.findById(id).orElse(null);
        // 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        // 뷰 페이지 반환하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        // 모든 데이터 가져오기
        Iterable<Article> articleEntityList = articleRepository.findAll();
        // 모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);
        // 뷰 페이지 설정하기
        return "articles/index";
    }
}
