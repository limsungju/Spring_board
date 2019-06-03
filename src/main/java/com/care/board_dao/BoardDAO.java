package com.care.board_dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.care.board_dto.BoardDTO;
import com.care.template.Constant;
public class BoardDAO {
	private JdbcTemplate template;
	/*
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@192.168.0.180:1521:xe";
	private String user = "jsp";
	private String pwd = "1234";
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	*/
	public BoardDAO() {
		template = Constant.template;
		/*
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/
	}
	public ArrayList<BoardDTO> list(){
		//String sql="select * from mvc_board";
		String sql="select * from mvc_board order by idgroup desc, step asc";
		ArrayList<BoardDTO> lists = null;
				try {
					lists = (ArrayList<BoardDTO>)
					template.query(sql, new BeanPropertyRowMapper<BoardDTO>(BoardDTO.class));
				}catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
		return lists;
		/*
		ArrayList<BoardDTO> lists = new ArrayList<BoardDTO>();
		try {
			con=DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setId(rs.getInt("id"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setSavedate(rs.getTimestamp("savedate"));
				dto.setHit(rs.getInt("hit"));
				dto.setIdgroup(rs.getInt("idgroup"));
				dto.setStep(rs.getInt("step"));
				dto.setIndent(rs.getInt("indent"));
				lists.add(dto);
			}
		} catch (SQLException e) {	e.printStackTrace(); }
		return lists;
		*/
	}
	public void write_save(final String name,final String title,final String content) {
		String sql="insert into mvc_board (id,name,title,content,hit,idgroup,step,indent)"+
	"values(mvc_board_seq.nextval,?,?,?,0,mvc_board_seq.currval,0,0)";
		try {
			template.update(sql,new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, name);
					ps.setString(2, title);
					ps.setString(3, content);
				}
			});
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		/*
		try {
			con=DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, title);
			ps.setString(3, content);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
	}
	public BoardDTO contentView(String id) {
		upHit(id);
		//String sql="select * from mvc_board where id=?";
		String sql="select * from mvc_board where id="+id;
		
		BoardDTO dto = null;
		try {
			dto = template.queryForObject(
					sql, new BeanPropertyRowMapper<BoardDTO>(BoardDTO.class));
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		return dto;
		/*
		BoardDTO dto = new BoardDTO();
		try {
			con=DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto.setId(rs.getInt("id"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setSavedate(rs.getTimestamp("savedate"));
				dto.setHit(rs.getInt("hit"));
				dto.setIdgroup(rs.getInt("idgroup"));
				dto.setStep(rs.getInt("step"));
				dto.setIndent(rs.getInt("indent"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
		*/
	}
	private void upHit(final String id) {
		String sql = "update mvc_board set hit=hit+1 where id=?";
		
		template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, id);
			}
		});
		
		/*
		try {
			con=DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	public void modify(final String id,final String name,final String title,final String content) {
		String sql="update mvc_board set name=?,title=?,content=? where id=?";
		
		try {
			template.update(sql,new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, name);
					ps.setString(2, title);
					ps.setString(3, content);
					ps.setString(4, id);
				}
			});
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		/*
		try {
			con=DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, title);
			ps.setString(3, content);
			ps.setString(4, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
	}
	public void delete(String id) {
		//String sql = "delete from mvc_board where id=?";
		String sql = "delete from mvc_board where id="+id;
		
		template.update(sql);
		/*
		try {
			con=DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
	}
	public BoardDTO reply_view(String id) {
		//String sql="select * from mvc_board where id=?";
		String sql="select * from mvc_board where id="+id;
		BoardDTO dto = null;
		try {
			dto = template.queryForObject(
					sql, new BeanPropertyRowMapper<BoardDTO>(BoardDTO.class));
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		return dto;
		/*
		BoardDTO dto = new BoardDTO();
		try {
			con=DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto.setId(rs.getInt("id"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setSavedate(rs.getTimestamp("savedate"));
				dto.setHit(rs.getInt("hit"));
				dto.setIdgroup(rs.getInt("idgroup"));
				dto.setStep(rs.getInt("step"));
				dto.setIndent(rs.getInt("indent"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
		*/
	}
	public void reply(final BoardDTO dto) {
		replyShape(dto);
		String sql="insert into mvc_board(id,name,title,content,idgroup,step,indent)"+
						"values(mvc_board_seq.nextval,?,?,?,?,?,?)";
		
		template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, dto.getName());
				ps.setString(2, dto.getTitle());
				ps.setString(3, dto.getContent());
				ps.setInt(4, dto.getIdgroup());
				ps.setInt(5, dto.getStep()+1);
				ps.setInt(6, dto.getIndent()+1);
			}
		});
		/*
		try {
			con=DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getTitle());
			ps.setString(3, dto.getContent());
			ps.setInt(4, dto.getIdgroup());
			ps.setInt(5, dto.getStep()+1);
			ps.setInt(6, dto.getIndent()+1);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
	}
	public void replyShape(final BoardDTO dto) {
		String sql = "update mvc_board set step=step+1 where idgroup=? and step>?";
		
		template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, dto.getIdgroup());
				ps.setInt(2, dto.getStep());
			}
		});
		/*
		try {
			con=DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setInt(1, dto.getIdgroup());
			ps.setInt(2, dto.getStep());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
}

