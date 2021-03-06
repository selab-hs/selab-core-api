package kr.ac.hs.selab.board.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import kr.ac.hs.selab.board.dto.request.PostRequest;
import kr.ac.hs.selab.board.dto.response.PostFindByBoardIdAndPageResponse;
import kr.ac.hs.selab.board.dto.response.PostFindResponse;
import kr.ac.hs.selab.board.dto.response.PostResponse;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import org.springframework.data.domain.Pageable;

@Api(tags = "게시글 API", description = "Post Controller (미사용)")
public interface PostSdk {
    @Operation(summary = "게시글 생성", description = "게시글 정보를 이용해서 게시판을 생성한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "게시글 생성 성공"),
            @ApiResponse(code = 400, message = "게시글 생성 실패")
    })
    ResponseTemplate<PostResponse> create(@Parameter(description = "게시판 id 값") Long boardId,
                                          @Parameter(description = "게시글 정보") PostRequest request);

    @Operation(summary = "게시글 조회", description = "게시글 정보를 이용해서 게시판을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "게시글 조회 성공"),
            @ApiResponse(code = 400, message = "게시글 조회 실패")
    })
    ResponseTemplate<PostFindResponse> find(@Parameter(description = "게시글 id 값") Long postId);

    @Operation(summary = "게시판의 전체 게시글 조회", description = "게시판에 해당하는 전체 게시글을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "게시글 조회 성공"),
            @ApiResponse(code = 400, message = "게시글 조회 실패")
    })
    ResponseTemplate<PostFindByBoardIdAndPageResponse> findByBoardAndPage(Long boardId,
                                                                          Pageable pageable);

    @Operation(summary = "게시글 수정", description = "새로운 게시글 정보를 이용해서 게시글을 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "게시글 수정 성공"),
            @ApiResponse(code = 400, message = "게시글 수정 실패")
    })
    ResponseTemplate<PostResponse> update(@Parameter(description = "게시글 id 값") Long postId,
                                          @Parameter(description = "게시글 정보") PostRequest request);

    @Operation(summary = "게시글 삭제", description = "게시글을 지정하여 게시판을 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "게시글 삭제 성공"),
            @ApiResponse(code = 400, message = "게시글 삭제 실패")
    })
    ResponseTemplate<PostResponse> delete(@Parameter(description = "게시글 id 값") Long postId) throws InterruptedException;
}
