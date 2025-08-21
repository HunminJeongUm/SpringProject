package com.example.project0820;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    public List<Board> getAllBoards() {
        return boardMapper.findAll();
    }

    public Board getBoardById(Long id) {
        return boardMapper.findById(id).orElse(null);
    }



    public void saveBoard(Board board) {
        if (board.getId() == null) {
            boardMapper.save(board);
        } else {
            boardMapper.update(board);
        }
    }

    public void deleteBoard(Long id) {
        boardMapper.deleteById(id);
    }
}