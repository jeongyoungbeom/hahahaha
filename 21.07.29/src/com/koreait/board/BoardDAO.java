package com.koreait.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.koreait.db.Dbconn;

public class BoardDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	String sql = "";
	List<BoardDTO> boardList = new ArrayList();
	String replycnt_str = "";

	public int delete(BoardDTO board) {
		try {
			sql = "delete from tb_board where b_idx=?";
			conn = Dbconn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getIdx());
			if (pstmt.executeUpdate() > 0) {
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int write(BoardDTO board) {
		try {

			conn = Dbconn.getConnection();
			if (conn != null) {
				sql = "insert into tb_board(b_userid, b_title, b_content, b_file) values(?, ?, ?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, board.getUserid());
				pstmt.setString(2, board.getTitle());
				pstmt.setString(3, board.getContent());
				pstmt.setString(4, board.getFile());
				if (pstmt.executeUpdate() > 0)
					;
				{
					return 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int hit(BoardDTO board) {
		try {
			sql = "update tb_board set b_hit = b_hit + 1 where b_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getIdx());
			if (pstmt.executeUpdate() > 0) {
				return 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public BoardDTO view(BoardDTO board) {
		try {
			conn = Dbconn.getConnection();
			if (conn != null) {
				sql = "update tb_board set b_hit = b_hit + 1 where b_idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, board.getIdx());
				pstmt.executeUpdate();

				sql = "select b_idx, b_userid, b_title, b_content, b_regdate, b_like, b_hit, b_file from tb_board where b_idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, board.getIdx());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					board.setUserid(rs.getString("b_userid"));
					board.setTitle(rs.getString("b_title"));
					board.setContent(rs.getString("b_content"));
					board.setRegdate(rs.getString("b_regdate"));
					board.setLike(rs.getString("b_like"));
					board.setHit(rs.getString("b_hit"));
					board.setFile(rs.getString("b_file"));
					return board;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public BoardDTO edit(BoardDTO board) {
		try {
			conn = Dbconn.getConnection();
			if (conn != null) {
				sql = "select b_title, b_content, b_file from tb_board where b_idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, board.getIdx());
				rs = pstmt.executeQuery();

				if (rs.next()) {
					board.setTitle(rs.getString("b_title"));
					board.setContent(rs.getString("b_content"));
					board.setFile(rs.getString("b_file"));
					return board;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int edit_ok(BoardDTO board) {
		try {
			conn = Dbconn.getConnection();
			if (conn != null) {
				if (board.getFile() != null && !board.getFile().equals("")) {
					sql = "update tb_board set b_title = ? , b_content = ?, b_file = ? where b_idx = '" + board.getIdx()
							+ "'";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, board.getTitle());
					pstmt.setString(2, board.getContent());
					pstmt.setString(3, board.getFile());

				} else {
					sql = "update tb_board set b_title = ? , b_content = ? where b_idx = '" + board.getIdx() + "'";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, board.getTitle());
					pstmt.setString(2, board.getContent());
				}
				if (pstmt.executeUpdate() > 0) {
					return 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public BoardDTO like(BoardDTO board) {
	      try {
	         conn = Dbconn.getConnection();
	         if (conn != null) {
	            sql = "update tb_board set b_like = b_like +1 where b_idx = ? ";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, board.getIdx());
	            pstmt.executeUpdate();

	            sql = "select b_like from tb_board where b_idx = ?";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, board.getIdx());
	            rs = pstmt.executeQuery();
	            if (rs.next()) {
	               board.setLike(rs.getString("b_like"));
	               return board;
	            }

	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return null;
	   }

	public BoardDTO totalcnt(BoardDTO board) {
		try {
			conn = Dbconn.getConnection();
			sql = "select count(b_idx) as total from tb_board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				board.setTotalCount(rs.getInt("total"));
				return board;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public BoardDTO page(BoardDTO board) {
		String PageNum = board.getPageNum();
		int start = 0;
		int pagePerCount = 10;
		
		if(PageNum != null && !PageNum.equals("")){
			start = (Integer.parseInt(PageNum)-1) * pagePerCount;	// 2 * 10
		}else{
			PageNum = "1";
			start=0;
		}
		board.setPageNum(PageNum);
		board.setStart(start);
		return board;
		
	}
	
	public List<BoardDTO> list(int start, int pagePerCount){
		try {
		conn = Dbconn.getConnection();
		sql = "select b_idx, b_userid, b_title, b_regdate, b_hit, b_like, b_file from tb_board order by b_idx desc limit ?, ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, start);
		pstmt.setInt(2, pagePerCount);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			BoardDTO board = new BoardDTO();
			board.setIdx(rs.getInt("b_idx"));
			board.setUserid(rs.getString("b_userid"));
			board.setTitle(rs.getString("b_title"));
			board.setRegdate(rs.getString("b_regdate").substring(0, 10));
			board.setHit(rs.getString("b_hit"));
			board.setLike(rs.getString("b_like"));
			board.setFile(rs.getString("b_file"));
			boardList.add(board);
		}
		
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return boardList;
		
	}

	public BoardDTO cnt(BoardDTO board) {
		try {
		conn = Dbconn.getConnection();
		sql = "select count(re_idx) as replycnt from tb_reply where re_boardidx=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, board.getIdx());
		rs = pstmt.executeQuery();
		
		int replycnt = 0;
		if(rs.next()){
			replycnt = rs.getInt("replycnt");
			if(replycnt > 0){
				 replycnt_str = "["+replycnt+"]";
			}
		}
		board.setReplycnt_str(replycnt_str);
	}catch(Exception e) {
		e.printStackTrace();
	}
	return board;
	}
}
