package com.koreait.reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.koreait.db.Dbconn;

public class ReplyDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	String sql = "";
	List<ReplyDTO> list = new ArrayList();

	public int reply(ReplyDTO reply) {
		try {
			conn = Dbconn.getConnection();
			if (conn != null) {
				sql = "insert into tb_reply (re_userid, re_content, re_boardidx) values(?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, reply.getUserid());
				pstmt.setString(2, reply.getContent());
				pstmt.setInt(3, reply.getIdx());
				if(pstmt.executeUpdate() > 0) {
					return 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<ReplyDTO> reply_view(String idx) {
		try {	
		conn = Dbconn.getConnection();
		sql = "select re_idx, re_userid, re_content, re_regdate from tb_reply where re_boardidx=? order by re_idx desc";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, idx);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			ReplyDTO reply = new ReplyDTO();
			reply.setIdx(rs.getInt("re_idx"));
			reply.setUserid(rs.getString("re_userid"));
			reply.setContent(rs.getString("re_content"));
			reply.setRegdate(rs.getString("re_regdate"));	
			list.add(reply);
		}		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
