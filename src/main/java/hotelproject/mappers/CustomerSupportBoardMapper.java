package hotelproject.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hotelproject.repositories.vo.CustomerSupportBoardVo;

@Mapper
public interface CustomerSupportBoardMapper {
    void insertBoard(CustomerSupportBoardVo board);
    void insertReply(CustomerSupportBoardVo board);
    List<CustomerSupportBoardVo> getAllBoards();
}
