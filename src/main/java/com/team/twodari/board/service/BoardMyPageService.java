package com.team.twodari.board.service;

import com.team.twodari.board.dto.BoardMyLikedResponse;
import com.team.twodari.board.dto.BoardOwnResponse;
import com.team.twodari.board.repository.BoardRepository;
import com.team.twodari.image.repository.BoardImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Service
public class BoardMyPageService {
    private final BoardRepository boardRepository;
    private final BoardImageRepository boardImageRepository;

    @Transactional(readOnly = true)
    public Page<BoardOwnResponse> findOwnBoardOrderByCreateDate(String nickname, Integer page) {
        Page<BoardOwnResponse> ownBoardOrderByCreateDate = boardRepository.findOwnBoardOrderByCreateDate(nickname, page);

        for (BoardOwnResponse board : ownBoardOrderByCreateDate.getContent()){
            List<String> imageUrls = boardImageRepository.findByBoardSeq(board.getBoardSeq());

            board.connectImageUrls(imageUrls);
        }

        return ownBoardOrderByCreateDate;
    }

    @Transactional(readOnly = true)
    public Page<BoardMyLikedResponse> findMyLikedBoardOrderByCreateDate(String nickname, Integer page) {
        Page<BoardMyLikedResponse> myLikedBoardOrderByCreateDate = boardRepository.findMyLikedBoardOrderByCreateDate(nickname, page);

        for (BoardMyLikedResponse board : myLikedBoardOrderByCreateDate.getContent()){
            List<String> imageUrls = boardImageRepository.findByBoardSeq(board.getBoardSeq());

            board.connectImageUrls(imageUrls);
        }

        return myLikedBoardOrderByCreateDate;
    }


}
