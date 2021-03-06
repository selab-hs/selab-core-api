package kr.ac.hs.selab.notice.facade;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.board.dto.response.BoardResponse;
import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.notice.application.NoticeService;
import kr.ac.hs.selab.notice.converter.NoticeConverter;
import kr.ac.hs.selab.notice.domain.Notice;
import kr.ac.hs.selab.notice.dto.NoticeFindByPageDto;
import kr.ac.hs.selab.notice.dto.request.NoticeRequest;
import kr.ac.hs.selab.notice.dto.response.NoticeFindByPageResponse;
import kr.ac.hs.selab.notice.dto.response.NoticeResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class NoticeFacadeTest {
    @Mock
    private MemberService memberService;

    @Mock
    private NoticeService noticeService;

    @InjectMocks
    private NoticeFacade noticeFacade;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void 공지사항_생성하기() {
        // given
        var member = fixtureMonkey.giveMeOne(Member.class);
        var notice = fixtureMonkey.giveMeBuilder(Notice.class)
                .set("memberId", member.getId())
                .sample();
        var request = new NoticeRequest(
                notice.getTitle(),
                notice.getContent()
        );
        var expected = new BoardResponse(notice.getId());

        // mocking
        Mockito.when(memberService.findByEmail(anyString()))
                .thenReturn(member);
        Mockito.when(noticeService.create(any()))
                .thenReturn(notice);

        // when
        var actual = noticeFacade.create(member.getEmail(), request);

        // then
        assertEquals(expected.getId(), actual.getId());
    }

    @Test
    public void 아이디로_공지사항_찾기() {
        // given
        var notice = fixtureMonkey.giveMeOne(Notice.class);
        var expected = NoticeConverter.toNoticeFindByIdResponse(notice);

        // mocking
        Mockito.when(noticeService.findById(anyLong()))
                .thenReturn(notice);

        // when
        var actual = noticeFacade.findById(notice.getId());

        // then
        assertEquals(expected.getTitle(), actual.getTitle());
    }

    @Test
    public void 전체_공지사항_페이지로_찾기() {
        // given
        var totalCount = 100L;
        var pageable = PageRequest.of(1, 20);
        var notices = fixtureMonkey.giveMe(Notice.class, (int) totalCount);
        var noticePage = new PageImpl<>(notices, pageable, totalCount);

        var dto = NoticeFindByPageDto.builder()
                .totalCount(totalCount)
                .pageable(pageable)
                .notices(noticePage)
                .build();
        var expected = NoticeConverter.toNoticeFindByPageResponse(dto);

        // mocking
        Mockito.when(noticeService.count())
                .thenReturn(totalCount);
        Mockito.when(noticeService.findByPage(any()))
                .thenReturn(noticePage);

        // when
        NoticeFindByPageResponse actual = noticeFacade.findByPage(pageable);

        // when
        assertEquals(expected.getTotalCount(), actual.getTotalCount());
        IntStream.range(0, 10)
                .forEach(i -> assertEquals(expected.getNotices().get(i).getTitle(), actual.getNotices().get(i).getTitle()));
    }

    @Test
    public void 공지사항_수정하기() {
        // given
        var notice = fixtureMonkey.giveMeOne(Notice.class);
        var request = new NoticeRequest(
                fixtureMonkey.giveMeOne(String.class),
                fixtureMonkey.giveMeOne(String.class)
        );
        var expected = new NoticeResponse(notice.getId());

        // mocking
        Mockito.when(noticeService.update(any()))
                .thenReturn(notice);

        // when
        var actual = noticeFacade.update(notice.getId(), request);

        // then
        assertEquals(expected.getId(), actual.getId());
    }

    @Test
    public void 공지사항_삭제하기() {
        // given
        var notice = fixtureMonkey.giveMeOne(Notice.class);
        var expected = new NoticeResponse(notice.getId());

        // mocking
        Mockito.when(noticeService.delete(anyLong()))
                .thenReturn(notice);

        // when
        var actual = noticeFacade.delete(expected.getId());

        // then
        assertEquals(expected.getId(), actual.getId());
    }
}
