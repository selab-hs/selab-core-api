package kr.ac.hs.selab.notice.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.notice.converter.NoticeConverter;
import kr.ac.hs.selab.notice.domain.Notice;
import kr.ac.hs.selab.notice.dto.NoticeFindByPageDto;
import kr.ac.hs.selab.notice.dto.request.NoticeRequest;
import kr.ac.hs.selab.notice.dto.response.NoticeResponse;
import kr.ac.hs.selab.notice.facade.NoticeFacade;
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
@Import(NoticeController.class)
@ExtendWith(MockitoExtension.class)
public class NoticeControllerTest {
    @MockBean
    private NoticeFacade noticeFacade;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void ????????????_????????????() throws Exception {  // TODO Non exist member
//        // given
//        var member = fixtureMonkey.giveMeOne(Member.class);
//        var notice = fixtureMonkey.giveMeBuilder(Notice.class)
//                .set("title", "???????????????. ??????????????? ??????????????????.")
//                .set("content", "??????????????? ???????????? ????????? ?????????????????? ?????? ?????????????????????.")
//                .set("memberId", member.getId())
//                .sample();
//
//        var request = new NoticeRequest(
//                notice.getTitle(),
//                notice.getContent()
//        );
//        var response = new NoticeResponse(notice.getId());
//
//        // mocking
//        Mockito.when(SecurityUtils.getCurrentUsername())
//                .thenReturn(member.getEmail());
//        Mockito.when(noticeFacade.create(anyString(), any()))
//                .thenReturn(response);
//
//        // when, then
//        mockMvc.perform(
//                        post("/api/v1/notices")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .characterEncoding("utf-8")
//                                .content(objectMapper.writeValueAsString(request))
//                )
//                .andExpect(status().isOk());
    }

    @Test
    public void ????????????_????????????_????????????() throws Exception {
        // given
        var notice = fixtureMonkey.giveMeOne(Notice.class);
        var response = NoticeConverter.toNoticeFindByIdResponse(notice);

        // mocking
        Mockito.when(noticeFacade.findById(anyLong()))
                .thenReturn(response);

        // when, then
        mockMvc.perform(get("/api/v1/notices/{id}", notice.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void ????????????_??????_????????????_????????????() throws Exception {
        // given
        var totalCount = 100L;
        var pageable = PageRequest.of(1, 20);
        var notices = fixtureMonkey.giveMe(Notice.class, (int) totalCount);

        var dto = NoticeFindByPageDto.builder()
                .totalCount(totalCount)
                .pageable(pageable)
                .notices(new PageImpl<>(notices, pageable, totalCount))
                .build();
        var response = NoticeConverter.toNoticeFindByPageResponse(dto);

        // mocking
        Mockito.when(noticeFacade.findByPage(any()))
                .thenReturn(response);

        // when, then
        mockMvc.perform(get("/api/v1/notices"))
                .andExpect(status().isOk());
    }

    @Test
    public void ????????????_????????????() throws Exception {
        // given
        var notice = fixtureMonkey.giveMeOne(Notice.class);
        var request = new NoticeRequest(
                "???????????? ????????? ???????????????.",
                "?????? ????????? ????????? ????????????."
        );
        var response = new NoticeResponse(notice.getId());

        // mocking
        Mockito.when(noticeFacade.update(anyLong(), any()))
                .thenReturn(response);

        // when, then
        mockMvc.perform(
                        put("/api/v1/notices/{id}", notice.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void ????????????_????????????() throws Exception {
        // given
        var notice = fixtureMonkey.giveMeOne(Notice.class);
        var response = new NoticeResponse(notice.getId());

        // mocking
        Mockito.when(noticeFacade.delete(anyLong()))
                .thenReturn(response);

        // when, then
        mockMvc.perform(patch("/api/v1/notices/{id}", notice.getId()))
                .andExpect(status().isOk());
    }
}
