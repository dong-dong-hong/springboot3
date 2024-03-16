package com.springboot.springbootbook.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder // setter 사용 지양으로 나온 해결방법
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 무분별한 객체 생성체크
@AllArgsConstructor
@ToString(exclude = {"id"}) // id 필드를 toString()에서 제외
@Entity // 엔티티선언
public class Article {
    @Id // 엔티티의 대푯값 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column // title 필드 선언, DB 테이블의 title 열과 연결됨
    private String title;
    @Column // content 필드 선언, DB 테이블의 content 열과 연결됨
    private String content;

//    public Article(Long id, String title, String content) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//    }
//


//    Article article = new Article(1L,"aa","111");
//    Article article = Article.builder().id(1L).title("aa").content("111").build();
//    빌더 패턴 적용 전과 후를 비교한건데 되도록이면 setter 사용을 지양하고 빌더패턴을 사용하자
}
