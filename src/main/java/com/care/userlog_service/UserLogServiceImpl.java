package com.care.userlog_service;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import com.care.log_dao.UserLogDAO;
import com.care.log_dto.UserLogDTO;

public class UserLogServiceImpl implements UserService{
	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int start=0;//���� �Ѱ��� ���� ��
		int viewContent=2;//ȭ�鿡 ������ �� ����
		int webStart=1;//���������� �Ѱ� ��(���� ��ư�� ���)
		if(request.getParameter("start")==null) {
			start=1;//�Ѿ�� ���� ���ٸ� ������ ù������ ���� �����´�.
		}else {
			//���� ���� �������� ���� �� ���
			start = (Integer.parseInt(request.getParameter("start"))-1)*viewContent+1;
			//������ �Ѱ��� �� ����
			webStart = Integer.parseInt(request.getParameter("start"));
		}
		int end = start+viewContent-1;
		UserLogDAO dao = new UserLogDAO();
		//ArrayList<UserLogDTO> lists = dao.list();
		//dao.list ������ ���� �޼ҵ� ���� (�����ε� ����)
		ArrayList<UserLogDTO> lists = dao.list(start,end);		
		//���� �� ���� ������ ���
		int totPage = dao.page();
		
		//������ �� 2���� �������� ������
		int endPage = totPage/viewContent+(totPage%viewContent==0?0:1);
		
		if(end > totPage) end=totPage; //url ���� ���� ����
		if(start>totPage) start=totPage;//url �������� ����
		
		ArrayList<Integer> arrTot = new ArrayList<>();
		for(int i=1;i<=endPage;i++) {//endPage ������ �� �۰��� / ������ �۰��� �� ���
			arrTot.add(i);//jsp������ �ѹ��� ����[1][2]...
		}
		model.addAttribute("start",webStart);//���� �Ǵ� ���� ��ư�� Ȱ��
		model.addAttribute("arrTot",arrTot);//jsp �ѹ���
		model.addAttribute("totPage",arrTot.size());//�ѹ��� �ݺ��� ������ ���� ��
		model.addAttribute("logList",lists);//�� ����
	}
}