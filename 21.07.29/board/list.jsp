<%@page import="com.koreait.board.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.koreait.db.Dbconn"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<jsp:useBean id="board" class="com.koreait.board.BoardDTO"/>
<jsp:useBean id="dao" class="com.koreait.board.BoardDAO"/>
<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	ResultSet rs_reply = null;
	String sql = "";
	int totalCount = 0;
	int pagePerCount = 10;	// 페이지당 글개수
	int start = 0;	// 시작 글번호
	
	Date from = new Date();
	SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
	String to = fm.format(from);	// 2021-07-27
	
	board.setPageNum(request.getParameter("pageNum"));
	if(dao.totalcnt(board) != null){
		totalCount = board.getTotalCount();
	}
	if(dao.page(board)!= null){
		start = board.getStart();
	}	  	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트</title>
</head>
<body>
	<h2>리스트</h2>
	<p>게시글 : <%=totalCount%>개</p>
	
	<table border="1" width="800">
		<tr>
			<th width="50">번호</th>
			<th width="300">제목</th>
			<th width="100">글쓴이</th>
			<th width="75">조회수</th>
			<th width="200">날짜</th>
			<th width="75">좋아요</th>
		</tr>
<%
		List<BoardDTO> list = dao.list(start, pagePerCount);
		for(int i = 0 ; i <list.size();i++){
			
		String newDateStr = "";
		if(to.equals(list.get(i).getRegdate())){
			newDateStr = "<img src='./new.png' alt='새글' size='10' height='20'>";
		}
		
		String fileStr = "";
		if(list.get(i).getFile() != null && !list.get(i).getFile().equals("")){
			fileStr = "<img src='./img.png' alt='파일' size='10' height='20'>";
		}
		
		int idx = list.get(i).getIdx();
		board.setIdx(idx);
		if(dao.cnt(board) != null){
			
		}
%>
		<tr>
			<td><%=list.get(i).getIdx()%></td>
			<td><a href="./view.jsp?b_idx=<%=list.get(i).getIdx()%>"><%=list.get(i).getTitle()%></a> <%=board.getReplycnt_str()%> <%=fileStr%> <%=newDateStr%></td>
			<td><%=list.get(i).getUserid()%></td>
			<td><%=list.get(i).getHit()%></td>
			<td><%=list.get(i).getRegdate()%></td>
			<td><%=list.get(i).getLike()%></td>
		</tr>
<%
}
		
	int pageNums = 0;
	if(totalCount % pagePerCount == 0){
		pageNums = (totalCount / pagePerCount);	// 20 / 10
	}else{
		pageNums = (totalCount / pagePerCount) + 1;	
	}
%>
	<tr>
		<td colspan="6" align="center">
			<%
				for(int i=1; i<=pageNums; i++){
					out.print("<a href='list.jsp?pageNum="+i+"'>["+i+"]</a> ");
				}
			%>
		</td>
	</tr>
	</table>
	
	
	<p><input type="button" value="글쓰기" onclick="location.href='write.jsp'"> <input type="button" value="메인" onclick="location.href='../login.jsp'"></p>
</body>
</html>










