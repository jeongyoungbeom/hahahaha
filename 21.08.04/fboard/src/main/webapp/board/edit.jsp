<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	if(session.getAttribute("userid") == null){
%>
	<script>
		alert('로그인 후 이용하세요');
		location.href='../login.jsp';
	</script>
<%
	}else{
%>
<jsp:useBean id="board" class="com.koreait.board.BoardDTO"/>
<jsp:useBean id="dao" class="com.koreait.board.BoardDAO"/>
<%
		board.setIdx(Integer.parseInt(String.valueOf(request.getParameter("b_idx"))));
		
		String b_title = "";
		String b_content = "";
		String b_file = "";
		
		if(dao.edit(board)!=null){
			b_title = board.getTitle();
			b_content = board.getContent();
			b_file = board.getFile();
		}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글수정</title>
</head>
<body>
	<h2>글수정</h2>
	<form method="post" action="edit_ok.jsp" enctype="multipart/form-data">
		<input type="hidden" name="b_idx" value="<%=board.getIdx()%>">
		<p>작성자 : <%=session.getAttribute("userid")%></p>
		<p><label>제목 : <input type="text" name="b_title" value="<%=b_title%>"></label></p>
		<p>내용</p>
		<p><textarea rows="5" cols="40" name="b_content"><%=b_content%></textarea></p>
		<%
			if(b_file != null && !b_file.equals("")){
				out.println("첨부파일");
				out.println("<img src='../upload/"+b_file+"' alt='첨부파일' width='150'>");
			}
		%>
		<p><input type="file" name="b_file"></p>
		<p><input type="submit" value="수정"> 
		<input type="reset" value="다시작성"> 
		<input type="button" value="돌아가기" onclick="history.back()"></p>
	</form>
</body>
</html>
<%
	}
%>