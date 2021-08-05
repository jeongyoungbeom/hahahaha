package com.koreait.reply;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.koreait.db.SqlMapConfig;

public class ReplyDAO {	
	SqlSessionFactory ssf = SqlMapConfig.getSqlMapInstance();
	SqlSession sqlsession;
	
	public ReplyDAO() {
		sqlsession = ssf.openSession(true);
		System.out.println("마이바티스 설정 성공");
	}
	public int reply(ReplyDTO reply) {
		HashMap<String, String> dataMap = new HashMap();
		dataMap.put("re_userid", reply.getUserid());
		dataMap.put("re_content", reply.getContent());
		dataMap.put("re_boardidx", String.valueOf(reply.getIdx()));
		
		return sqlsession.insert("Reply.reply", dataMap);
	}
	
	public List<ReplyDTO> reply_view(String idx) {
		return sqlsession.selectList("Reply.view", idx);
	}
	
	
}
