package hotelproject.services;

import java.util.List;

import org.springframework.stereotype.Service;

import hotelproject.mappers.CustomerSupportBoardMapper;
import hotelproject.repositories.vo.CustomerSupportBoardVo;
import jakarta.servlet.http.HttpSession;

@Service
public class CustomerSupportBoardService {

    private final CustomerSupportBoardMapper boardMapper;
    private final HttpSession session;

    public CustomerSupportBoardService(CustomerSupportBoardMapper boardMapper, HttpSession session) {
        this.boardMapper = boardMapper;
        this.session = session;
    }

    // 고객센터 게시글 작성 (로그인한 유저만 가능)
    public void createBoard(String title, String content) {
        String userId = (String) session.getAttribute("loggedInUser");
        if (userId == null) {
            throw new RuntimeException("로그인이 필요합니다.");
        }

        CustomerSupportBoardVo board = new CustomerSupportBoardVo();
        board.setUserId(userId);
        board.setTitle(title);
        board.setContent(content);

        boardMapper.insertBoard(board);
    }

    // 관리자 댓글 작성
    public void createReply(int boardId, String content) {
        String userId = (String) session.getAttribute("loggedInUser");
        String userRole = (String) session.getAttribute("userRole");

        if (!"ADMIN".equals(userRole)) {
            throw new RuntimeException("관리자만 댓글을 작성할 수 있습니다.");
        }

        CustomerSupportBoardVo reply = new CustomerSupportBoardVo();
        reply.setBoardId(boardId);
        reply.setUserId(userId);
        reply.setContent(content);

        boardMapper.insertReply(reply);
    }

    // 모든 게시글 조회 (로그인 필요 없음)
    public List<CustomerSupportBoardVo> getAllBoards() {
        return boardMapper.getAllBoards();
    }
}
