package com.springboot.springbootbook.repository;

import com.springboot.springbootbook.entity.Article;
import org.springframework.data.repository.CrudRepository;

/*
 리파지토리는 사용자가 직접 구현할 수도 있지만 JPA에서 제공하는 리파지터리 인터페이스를 활용해 만들 수도 있다.
 CrudRepository는 JPA에서 제공하는 인터페이스로 이를 상속해 엔티티를 관리(생성,조회,수정,삭제)할 수 있다.
 < , >
 Aricle: 관리 대상 엔티티의 클래스 타입이다.
 Long: 관리 대상 엔티티의 대푯값이다. Article.java 파일에서 id를 대푯값으로 지정했고 타입은 Long이다.
 */

public interface ArticleRepository extends CrudRepository<Article, Long> {

}
