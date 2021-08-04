package com.koreait.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.koreait.db.Dbconn;
import com.koreait.db.SqlMapConfig;

public class BoardDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	String sql = "";
	List<BoardDTO> boardList = new ArrayList();

	SqlSessionFactory ssf = SqlMapConfig.getSqlMapInstance();
	SqlSession sqlsession;

	public BoardDAO() {
		sqlsession = ssf.openSession(true); // openSession(true)설정 시 자동 커밋
	}

	public int delete(BoardDTO board) {
		HashMap<String, String> dataMap = new HashMap();
		dataMap.put("b_idx", String.valueOf(board.getIdx()));

		return sqlsession.delete("Board.delete", dataMap);
	}

	public int write(BoardDTO board) {
		HashMap<String, String> dataMap = new HashMap();
		dataMap.put("b_userid", board.getUserid());
		dataMap.put("b_title", board.getTitle());
		dataMap.put("b_content", board.getContent());
		dataMap.put("b_file", board.getFile());
		return sqlsession.insert("Board.write", dataMap);
	}

	public BoardDTO view(BoardDTO board) {
		HashMap<String, String> dataMap = new HashMap();
		dataMap.put("b_idx", String.valueOf(board.getIdx()));
		sqlsession.update("Board.hitup", dataMap);

		dataMap = sqlsession.selectOne("Board.view", dataMap);
		if (dataMap != null) {
			board.setIdx(Integer.parseInt(String.valueOf(dataMap.get("b_idx"))));
			board.setUserid(dataMap.get("b_userid"));
			board.setTitle(dataMap.get("b_title"));
			board.setContent(dataMap.get("b_content"));
			board.setRegdate(String.valueOf(dataMap.get("b_regdate")));
			board.setLike(String.valueOf(dataMap.get("b_like")));
			board.setHit(String.valueOf(dataMap.get("b_hit")));
			board.setFile(dataMap.get("b_file"));
			return board;
		}
		return null;
	}

	public BoardDTO edit(BoardDTO board) {
		HashMap<String, String> dataMap = new HashMap();
		dataMap.put("b_idx", String.valueOf(board.getIdx()));
		dataMap = sqlsession.selectOne("Board.edit", dataMap);

		if (dataMap != null) {
			board.setTitle(dataMap.get("b_title"));
			board.setContent(dataMap.get("b_content"));
			board.setFile(dataMap.get("b_file"));
			return board;
		}
		return null;
	}

	public int edit_ok(BoardDTO board) {
		HashMap<String, String> dataMap = new HashMap();
		if (board.getFile() != null && !board.getFile().equals("")) {
			dataMap.put("b_idx", String.valueOf(board.getIdx()));
			dataMap.put("b_title", board.getTitle());
			dataMap.put("b_file", board.getFile());
			dataMap.put("b_content", board.getContent());
			return sqlsession.update("Board.edit_ok1", dataMap);
		} else {
			dataMap.put("b_idx", String.valueOf(board.getIdx()));
			dataMap.put("b_title", board.getTitle());
			dataMap.put("b_content", board.getContent());
			return sqlsession.update("Board.edit_ok2", dataMap);
		}
	}

	public BoardDTO like(BoardDTO board) {
		HashMap<String, String> dataMap = new HashMap();
		dataMap.put("b_idx", String.valueOf(board.getIdx()));
		sqlsession.update("Board.likeup", dataMap);

		dataMap = sqlsession.selectOne("Board.like", dataMap);
		if (dataMap != null) {
			board.setLike(String.valueOf(dataMap.get("b_like")));
			return board;
		}
		return null;
	}

	public int totalCount() {
		return sqlsession.selectOne("Board.totalcnt");
	}

	public List<BoardDTO> list(int start, int pagePerCount) {
		HashMap<String, String> dataMap = new HashMap();
		dataMap.put("start", String.valueOf(start));
		dataMap.put("pagePerCount", String.valueOf(pagePerCount));
		return sqlsession.selectList("Board.list", dataMap);

	}

	public String re_cnt(int idx) {
		String re_cnt = "";
		int replycnt = 0;
		
		HashMap<String, String> dataMap = new HashMap();
		dataMap.put("idx", String.valueOf(idx));
		dataMap = sqlsession.selectOne("Reply.re_cnt", dataMap);
		
		if (dataMap != null) {
			replycnt = Integer.parseInt(String.valueOf((dataMap.get("replycnt"))));
			if (replycnt > 0) {
				re_cnt = "[" + replycnt + "]";
			}
		}
		return re_cnt;
	}
}
