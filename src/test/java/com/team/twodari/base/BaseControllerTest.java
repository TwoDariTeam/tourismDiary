package com.team.twodari.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.twodari.admin.user.AdminUserService;
import com.team.twodari.board.controller.facade.BoardFacadeService;
import com.team.twodari.board.service.BoardService;
import com.team.twodari.common.security.jwt.JwtFilter;
import com.team.twodari.common.security.jwt.TokenProvider;
import com.team.twodari.global.infrastructure.TourApiService;
import com.team.twodari.image.service.BoardImageService;
import com.team.twodari.image.service.SubBoardImageService;
import com.team.twodari.image.service.UserImageService;
import com.team.twodari.platform.service.PlatformService;
import com.team.twodari.subBoard.service.SubBoardService;
import com.team.twodari.tag.service.TagService;
import com.team.twodari.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@Import(FilterChainConfig.class)
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
    protected BoardImageService boardImageService;

    @MockBean
    protected SubBoardImageService subBoardImageService;

    @MockBean
    protected UserImageService userImageService;

    @MockBean
    protected TourApiService tourApiService;

    @MockBean
    protected UserService userService;

    @MockBean
    protected JwtFilter jwtFilter;

    @MockBean
    protected TokenProvider tokenProvider;




//    @BeforeEach
//    void mockMvcSetUp(
//            final WebApplicationContext context) {
//
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
//                .alwaysDo(print())
//                .addFilter(new JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);
//                .addFilter(new CharacterEncodingFilter("UTF-8", true))
//                .build();
//    }
}
