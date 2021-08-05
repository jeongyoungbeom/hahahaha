<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	if (session.getAttribute("userid") == null) {
%>
<script>
	alert('로그인 후 이용하세요');
	location.href = '../login.jsp';
</script>
<%
	} else {
%>
<jsp:useBean id="board" class="com.koreait.board.BoardDTO"/>
<jsp:useBean id="dao" class="com.koreait.board.BoardDAO"/>
<%		
	int size = 1024*1024*10;
	String uploadPath = request.getRealPath("upload");
	MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "UTF-8", new DefaultFileRenamePolicy());
	
	board.setIdx(Integer.parseInt(String.valueOf(multi.getParameter("b_idx"))));
	board.setTitle(multi.getParameter("b_title"));
	board.setFile(multi.getFilesystemName("b_file"));
	board.setContent(multi.getParameter("b_content"));
		if(dao.edit_ok(board) == 1){
			
%>
<script>
	alert('수정이 완료되었습니다.');
	location.href='./view.jsp?b_idx=<%=board.getIdx()%>';
</script>
<%
		}
	}
%>
