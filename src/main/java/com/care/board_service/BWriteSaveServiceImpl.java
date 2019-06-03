package com.care.board_service;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import com.care.board_dao.BoardDAO;
public class BWriteSaveServiceImpl implements BoardService {
	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String,Object>map = model.asMap();//map형식으로 변환해서 request의 값을 가져옴
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		BoardDAO dao = new BoardDAO();
		dao.write_save(name,title,content);
	}
}