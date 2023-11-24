package com.team.twodari.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.twodari.board.dto.BoardCreateDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createBoardTest() throws Exception {
        // Given
        BoardCreateDto requestDto = new BoardCreateDto("John Doe", "Sample Title", 1);

        // When
        ResultActions result = mockMvc.perform(post("/board/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)));

        // Then
        result.andExpect(status().isOk())
                .andExpect(content().string(containsString("게시글 작성 성공")))
                .andExpect(jsonPath("$.message").value(containsString("게시글 작성 성공")))
                .andExpect(jsonPath("$.data").isNumber());
    }
}
