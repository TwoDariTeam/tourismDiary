package com.team.twodari.board.service;

import com.team.twodari.board.dto.BoardCreateDto;
import com.team.twodari.board.dto.BoardUpdateDto;
import com.team.twodari.board.entity.BoardEntity;
import com.team.twodari.board.repository.BoardRepository;
import com.team.twodari.user.entity.LoginEntityImpl;
import com.team.twodari.user.entity.UserEntity;
import com.team.twodari.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public BoardService(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    public Long createBoard(BoardCreateDto createDto) {
        // 현재 로그인한 사용자의 정보 가져오기
        LoginEntityImpl userDetails = (LoginEntityImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserEntity user = userRepository.findByNickname(userDetails.getNickname()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // TODO: author -> userSeq 로 변경 후 수정
        BoardEntity board = BoardEntity.builder()
                .author(user.getNickname())
                .title(createDto.getTitle())
                .introduce(createDto.getIntroduce())
                .accessRole(createDto.getAccessRole())
                .build();
        boardRepository.save(board);

        return board.getBoardSeq();
    }

    @Transactional(readOnly = true)
    public BoardEntity getBoardBySeq(Long boardSeq) {
        return boardRepository.findById(boardSeq)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다"));
    }

    @Transactional
    public Long updateBoard(Long boardSeq, BoardUpdateDto updateDto) {
        LoginEntityImpl userDetails = (LoginEntityImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 현재 로그인한 유저의 닉네임
        String logInUserNickname = userDetails.getNickname();

        BoardEntity board = boardRepository.findById(boardSeq)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다"));

        String boardAuthor = board.getAuthor();

        // 현재 로그인한 유저와 게시글 작성자가 동일한지 확인
        if (!logInUserNickname.equals(boardAuthor)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "게시글 작성자가 아닌 사용자는 수정 할 수 없습니다.");
        }

        board.updateEntity(updateDto.getTitle(), updateDto.getIntroduce(), updateDto.getAccessRole());
        boardRepository.save(board);

        return board.getBoardSeq();
    }

    @Transactional
    public void deleteBoard(Long boardSeq) {
        LoginEntityImpl userDetails = (LoginEntityImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String logInUserNickname = userDetails.getNickname();

        BoardEntity board = boardRepository.findById(boardSeq)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다"));

        String boardAuthor = board.getAuthor();

        if (!logInUserNickname.equals(boardAuthor)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "게시글 작성자가 아닌 사용자는 삭제 할 수 없습니다.");
        }

        board.deleteEntity();

        boardRepository.save(board);
    }
}
