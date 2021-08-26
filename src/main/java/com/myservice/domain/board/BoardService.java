package com.myservice.domain.board;

import com.myservice.domain.member.Member;
import com.myservice.web.user.board.BoardForm;
import com.myservice.web.user.board.BoardUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;

    public Long save(Board board) {
        return boardRepository.save(board);
    }

    public Board findOne(Long id, boolean flag) {
        Board board = boardRepository.findOne(id);
        if (flag) board.addViews();
        return board;
    }

    @Transactional(readOnly = true)
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Board> findAllOfUser(Member user) {
        return boardRepository.findAllOfUser(user);
    }

    public Long update(Long boardId, BoardUpdateForm form) {
        Board board = boardRepository.findOne(boardId);
        board.updateBoard(form);
        return boardId;
    }

    public void remove(Board board) {
        boardRepository.remove(board);
    }

    @Transactional(readOnly = true)
    public List<Board> findBoardsByPaging(int startIndex) {
        return boardRepository.findBoardsByPaging(startIndex);
    }

    @Transactional(readOnly = true)
    public Long findBoardsTotalSize() {
        return boardRepository.findBoardsTotalSize();
    }
}
