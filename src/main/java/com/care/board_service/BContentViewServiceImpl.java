package com.care.board_service;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import com.care.board_dao.BoardDAO;
import com.care.board_dto.BoardDTO;
public class BContentViewServiceImpl implements BoardService{
	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String id = request.getParameter("id");
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.contentView(id);
		model.addAttribute("content_view",dto);
	}
}