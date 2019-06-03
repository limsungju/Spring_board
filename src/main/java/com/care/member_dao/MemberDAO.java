package com.care.member_dao;
import java.util.ArrayList;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import com.care.member_dto.MemberDTO;
import com.care.template.Constant;
public class MemberDAO {
	public final int CHK_OK = 0;
	public final int CHK_NO = 1;
	private JdbcTemplate template;
	public MemberDAO() {
		this.template = Constant.template;
		System.out.println("template : "+template);
	}
	public int userCheck(String id,String pw) {
		String sql = "select * from member02 where id='"+id+"'";
		MemberDTO dto = null;
		try {
			dto=template.queryForObject(sql,
					new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class));
		}catch(Exception e) {
			return CHK_NO;
		}
		if(dto != null && dto.getPw().equals(pw))
			return CHK_OK;
		return CHK_NO;
	}

	public ArrayList<MemberDTO> memberInfo() {
		String sql = "select * from member02";
		return (ArrayList<MemberDTO>)template.query(sql,
				new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class));
	}

	public MemberDTO memberData(String id) {
		//String sql = "select * from member02 where id=?";
		String sql = "select * from member02 where id='"+id+"'";
		
		return template.queryForObject(sql,
				new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class));
	}

	public int register(String id, String pw) {
		String sql = "insert into member02 values(?,?)";
		int result=0;
		result = template.update(sql,ps->{
			ps.setString(1, id);
			ps.setString(2, pw);
		});
		System.out.println("실행 결과 result : "+result);
		return result;
	}

}