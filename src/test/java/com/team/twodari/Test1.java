package com.team.twodari;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

public class Test1 {
    @Test
    void test() {
        RestTemplate restTemplate = new RestTemplate();
        String str = restTemplate.getForObject("https://jsonplaceholder.typicode.com/todos/1",String.class);
        System.out.println("hello: "+str);
    }
}
