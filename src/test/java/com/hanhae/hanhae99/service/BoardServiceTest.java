package com.hanhae.hanhae99.service;

import com.hanhae.hanhae99.board.model.entity.Board;
import com.hanhae.hanhae99.board.model.request.BoardSaveRequest;
import com.hanhae.hanhae99.board.model.response.BoardResponse;
import com.hanhae.hanhae99.board.repository.BoardRepository;
import com.hanhae.hanhae99.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardRepository repository;

    @Autowired
    private BoardService service;

    @Test
    void save() {
        BoardSaveRequest request = getSaveRequest("테스트1");

        BoardResponse response = service.save(request);

        assertEquals(response.title(), request.title());
        assertEquals(response.content(), request.content());
    }

    @Test
    void findAll() {
        int count = repository.findAll().size();

        int findAllCount = service.findAll().size();

        assertEquals(count, findAllCount);
    }

    @Test
    void findById() {
        long pid = 14;

        Board board = repository.findById(pid).get();
        BoardResponse response = service.findById(pid);


        assertEquals(response.title(), board.getTitle());
        assertEquals(response.content(), board.getContent());
    }

    @Test
    void updateBoard() {
        long pid = 13;
        String changeTitle = "바꾼 이름";

        service.updateBoard(pid, getSaveRequest(changeTitle));
        Board board = repository.findById(pid).get();

        assertEquals(board.getTitle(), changeTitle);
    }

    @Test
    void deleteBoard() {
        long pid = 12;

        Board board = repository.findById(pid).get();
        String tot = service.deleteBoard(pid, board.getPassword());

        assertEquals(tot, "성공적으로 삭제되었습니다.");
    }


    private BoardSaveRequest getSaveRequest(String title) {
        return new BoardSaveRequest(
                title,
                "권씨",
                "1234",
                "그래요? 그렇구나~"
        );
    }

}