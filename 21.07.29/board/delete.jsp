<%@page import="java.sql.*"%>
<%@ page import="com.koreait.db.Dbconn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	if (session.getAttribute("userid") == null) {
%>
<script>
	alert('로그인 후 이용하세요');
	location.href = '../login.jsp';
</script>

<%
	} else {
%>
<jsp:useBean class="com.koreait.board.BoardDTO" id="board"/>
<jsp:useBean class="com.koreait.board.BoardDAO" id="dao" />
<%
	board.setIdx(Integer.parseInt(String.valueOf(request.getParameter("b_idx"))));
		if (dao.delete(board) == 1) {
%>
<script>
	alert('삭제되었습니다.');
	location.href = 'list.jsp';
</script>
<%
	} else {
%>
<script>
	alert('삭제실패.');
	history.back();
</script>
<%
	}
	}
%>






