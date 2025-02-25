package hotelproject.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hotelproject.repositories.vo.CustomerSupportBoardVo;
import hotelproject.services.CustomerSupportBoardService;

@RestController
@RequestMapping("/board")
public class CustomerSupportBoardController {

    private final CustomerSupportBoardService boardService;

    public CustomerSupportBoardController(CustomerSupportBoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createBoard(@RequestBody Map<String, String> request) {
        boardService.createBoard(request.get("title"), request.get("content"));
        return ResponseEntity.ok("게시글이 등록되었습니다.");
    }

    @PostMapping("/reply")
    public ResponseEntity<String> createReply(@RequestBody Map<String, String> request) {
        int boardId = Integer.parseInt(request.get("boardId"));
        String content = request.get("content");
        boardService.createReply(boardId, content);
        return ResponseEntity.ok("댓글이 등록되었습니다.");
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerSupportBoardVo>> getAllBoards() {
        return ResponseEntity.ok(boardService.getAllBoards());
    }
}
