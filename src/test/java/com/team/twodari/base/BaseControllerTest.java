package com.team.twodari.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.twodari.admin.user.AdminUserService;
import com.team.twodari.board.controller.facade.BoardFacadeService;
import com.team.twodari.board.service.BoardService;
import com.team.twodari.image.service.ImageService;
import com.team.twodari.platform.service.PlatformService;
import com.team.twodari.subBoard.service.SubBoardService;
import com.team.twodari.tag.service.TagService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest
public abstract class BaseControllerTest {

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected BoardFacadeService boardFacadeService;

    @MockBean
    protected TagService tagService;

    @MockBean
    protected PlatformService platformService;

    @MockBean
    protected AdminUserService adminUserService;

    @MockBean
    protected BoardService boardService;

    @MockBean
    protected SubBoardService subBoardService;

    @MockBean
    protected ImageService imageService;

    @BeforeEach
    void mockMvcSetUp(
            final WebApplicationContext context) {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .alwaysDo(print())
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }
}
