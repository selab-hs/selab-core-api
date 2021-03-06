package kr.ac.hs.selab.board.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import kr.ac.hs.selab.board.dto.request.BoardRequest;
import kr.ac.hs.selab.board.dto.response.BoardFindAllResponse;
import kr.ac.hs.selab.board.dto.response.BoardFindResponse;
import kr.ac.hs.selab.board.dto.response.BoardResponse;
import kr.ac.hs.selab.common.template.ResponseTemplate;

@Api(tags = "게시판 API", description = "Board Controller (미사용)")
public interface BoardSdk {
    @Operation(summary = "게시판 생성", description = "게시판 정보를 이용해서 게시판을 생성한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "게시판 생성 성공"),
            @ApiResponse(code = 400, message = "게시판 생성 실패")
    })
    ResponseTemplate<BoardResponse> create(@Parameter(description = "게시판 정보") BoardRequest request);

    @Operation(summary = "전체 게시판 조회", description = "전체 게시판을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "게시판 조회 성공"),
            @ApiResponse(code = 400, message = "게시판 조회 실패")
    })
    ResponseTemplate<BoardFindAllResponse> findAll();

    @Operation(summary = "게시판 조회", description = "게시판 정보를 이용해서 게시판을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "게시판 조회 성공"),
            @ApiResponse(code = 400, message = "게시판 조회 실패")
    })
    ResponseTemplate<BoardFindResponse> find(@Parameter(description = "게시판 id 값") Long id);

    @Operation(summary = "게시판 수정", description = "새로운 게시판 정보를 이용해서 게시판을 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "게시판 수정 성공"),
            @ApiResponse(code = 400, message = "게시판 수정 실패")
    })
    ResponseTemplate<BoardResponse> update(@Parameter(description = "게시판 id 값") Long id,
                                               @Parameter(description = "게시판 정보") BoardRequest request);

    @Operation(summary = "게시판 삭제", description = "게시판을 지정하여 게시판을 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "게시판 삭제 성공"),
            @ApiResponse(code = 400, message = "게시판 삭제 실패")
    })
    ResponseTemplate<BoardResponse> delete(@Parameter(description = "게시판 id 값") Long id) throws InterruptedException;
}
