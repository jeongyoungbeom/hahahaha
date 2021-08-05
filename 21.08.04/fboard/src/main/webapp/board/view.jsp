<%@page import="java.util.List"%>
<%@page import="com.koreait.reply.ReplyDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="board" class="com.koreait.board.BoardDTO" />
<jsp:useBean id="dao" class="com.koreait.board.BoardDAO" />
<%
	board.setIdx(Integer.parseInt(String.valueOf(request.getParameter("b_idx"))));

	String b_userid = "";
	String b_title = "";
	String b_content = "";
	String b_regdate = "";
	String b_file = "";
	String b_like = "";
	String b_hit = "";
	int b_idx = board.getIdx();
	
	if (dao.view(board) != null) {
		b_userid = board.getUserid();
		b_title = board.getTitle();
		b_content = board.getContent();
		b_regdate = board.getRegdate().substring(0, 10);
		b_like = board.getLike();
		b_hit = board.getHit();
		b_file = board.getFile();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글보기</title>
<script>
	function like(){
		const xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
				document.getElementById('like').innerHTML = xhr.responseText;
			}
		}
		xhr.open('GET', './like_ok.jsp?b_idx=<%=board.getIdx()%>', true);
		xhr.send();
	}
</script>
</head>
<body>
	<h2>글보기</h2>
	<table border="1" width="800">
		<tr>
			<td>제목</td>
			<td><%=b_title%></td>
		</tr>
		<tr>
			<td>날짜</td>
			<td><%=b_regdate%></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><%=b_userid%></td>
		</tr>
		<tr>
			<td>조회수</td>
			<td><%=b_hit%></td>
		</tr>
		<tr>
			<td>좋아요</td>
			<td><span id="like"><%=b_like%></span></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><%=b_content%></td>
		</tr>
		<tr>
			<td>파일</td>
			<td>
				<%
					if (b_file != null && !b_file.equals("")) {
						out.println("<img src='../upload/" + b_file + "' alt='첨부파일' width='150'>");
					}
				%>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<%
				if (b_userid.equals((String) session.getAttribute("userid"))) {
			%> 
			<input type="button" value="수정" onclick="location.href='./edit.jsp?b_idx=<%=board.getIdx()%>'"> 
			<input type="button" value="삭제" onclick="location.href='./delete.jsp?b_idx=<%=board.getIdx()%>'"> 
	<%
 	}
 %> 
 <input type="button" value="좋아요" onclick="like()"> 
 <input type="button" value="리스트" onclick="location.href='./list.jsp'">
			</td>
		</tr>
	</table>
	<hr />
	<form method="post" action="reply_ok.jsp">
		<input type="hidden" name="b_idx" value="<%=board.getIdx()%>">
		<p><%=session.getAttribute("userid")%>
			: <input type="text" size="40" name="re_content"> <input
				type="submit" value="확인">
		</p>
	</form>
	<hr />
	
<jsp:useBean id="reply" class="com.koreait.reply.ReplyDTO"/>
<jsp:useBean id="re_dao" class="com.koreait.reply.ReplyDAO"/>
<%		
		String idx = request.getParameter("b_idx");
		List<ReplyDTO> list = re_dao.reply_view(idx);
		for(ReplyDTO DTO : list){
			
	%>
	<p><%=DTO.getUserid()%> : <%=DTO.getContent()%> (<%=DTO.getRegdate()%>)
	<%
		
			}
		%>
	</p>
</body>
</html>