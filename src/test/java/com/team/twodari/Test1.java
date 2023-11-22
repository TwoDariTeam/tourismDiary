package com.team.twodari;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team.twodari.board.entity.BoardEntity;
import com.team.twodari.board.entity.QBoardEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.team.twodari.board.entity.QBoardEntity.*;

@SpringBootTest
public class Test1 {

    @Autowired
    JPAQueryFactory jpaQueryFactory;
    @Test
    void test() {
        RestTemplate restTemplate = new RestTemplate();
        String str = restTemplate.getForObject("https://jsonplaceholder.typicode.com/todos/1", String.class);
        System.out.println("hello: " + str);
    }

    @Test
    void t2() {
        List<BoardEntity> fetch =
                jpaQueryFactory.selectFrom(boardEntity).fetch();
        for (BoardEntity entity : fetch) {
            System.out.println(entity);
        }
    }
}
