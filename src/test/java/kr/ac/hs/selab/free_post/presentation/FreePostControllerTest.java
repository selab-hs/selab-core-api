package kr.ac.hs.selab.free_post.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.free_post.converter.FreePostConverter;
import kr.ac.hs.selab.free_post.domain.FreePost;
import kr.ac.hs.selab.free_post.dto.FreePostFindByPageDto;
import kr.ac.hs.selab.free_post.dto.request.FreePostRequest;
import kr.ac.hs.selab.free_post.dto.response.FreePostResponse;
import kr.ac.hs.selab.free_post.facade.FreePostFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(useDefaultFilters = false)
@AutoConfigureMockMvc(addFilters = false)
@Import(FreePostController.class)
@ExtendWith(MockitoExtension.class)
public class FreePostControllerTest {
    @MockBean
    private FreePostFacade freePostFacade;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void ???????????????_????????????() throws Exception {  // TODO Non exist member
//        // given
//        var member = fixtureMonkey.giveMeOne(Member.class);
//        var post = fixtureMonkey.giveMeBuilder(FreePost.class)
//                .set("title", "?????????????????? ???????????????.")
//                .set("content", "???????????? ?????? ??????????????? ?????????.")
//                .set("memberId", member.getId())
//                .sample();
//        var request = new FreePostRequest(
//                post.getTitle(),
//                post.getContent()
//        );
//        var response = new FreePostResponse(post.getId());
//
//        // mocking
//        Mockito.when(SecurityUtils.getCurrentUsername())
//                .thenReturn(member.getEmail());
//        Mockito.when(freePostFacade.create(anyString(), any()))
//                .thenReturn(response);
//
//        // when, then
//        mockMvc.perform(
//                        post("/api/v1/free-posts")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .characterEncoding("utf-8")
//                                .content(objectMapper.writeValueAsString(request))
//                )
//                .andExpect(status().isOk());
    }

    @Test
    public void ????????????_???????????????_????????????() throws Exception {
        // given
        var post = fixtureMonkey.giveMeOne(FreePost.class);
        var response = FreePostConverter.toFreePostFindByIdResponse(post);

        // mocking
        Mockito.when(freePostFacade.findById(anyLong()))
                .thenReturn(response);

        // when, then
        mockMvc.perform(get("/api/v1/free-posts/{id}", post.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void ???????????????_????????????_????????????() throws Exception {
        // given
        var totalCount = 100L;
        var pageable = PageRequest.of(1, 20);
        var posts = fixtureMonkey.giveMe(FreePost.class, (int) totalCount);

        var dto = FreePostFindByPageDto.builder()
                .totalCount(totalCount)
                .pageable(pageable)
                .freePosts(new PageImpl<>(posts, pageable, totalCount))
                .build();
        var response = FreePostConverter.toFreePostFindByPageResponse(dto);

        // mocking
        Mockito.when(freePostFacade.findByPage(any()))
                .thenReturn(response);

        // when, then
        mockMvc.perform(get("/api/v1/free-posts"))
                .andExpect(status().isOk());
    }

    @Test
    public void ???????????????_????????????() throws Exception {
        // given
        var post = fixtureMonkey.giveMeOne(FreePost.class);
        var request = new FreePostRequest(
                "???????????? ????????? ???????????????.",
                "?????? ????????? ????????? ????????????."
        );
        var response = new FreePostResponse(post.getId());

        // mocking
        Mockito.when(freePostFacade.update(anyLong(), any()))
                .thenReturn(response);

        // when, then
        mockMvc.perform(
                        put("/api/v1/free-posts/{id}", post.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void ???????????????_????????????() throws Exception {
        // given
        var post = fixtureMonkey.giveMeOne(FreePost.class);
        var response = new FreePostResponse(post.getId());

        // mocking
        Mockito.when(freePostFacade.delete(anyLong()))
                .thenReturn(response);

        // when, then
        mockMvc.perform(patch("/api/v1/free-posts/{id}", post.getId()))
                .andExpect(status().isOk());
    }
}
