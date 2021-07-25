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
		String b_idx = request.getParameter("b_idx");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		String b_title = null;
		String b_content = null;

		try {
			conn = Dbconn.getConnection();
			if (conn != null) {
				sql = "select b_title, b_content from tb_board where b_idx = '" + b_idx + "'";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();			
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정</title>
</head>
<body>
	<h2>글쓰기</h2>
	<form method="post" action="edit_ok.jsp?b_idx=<%=b_idx%>">
		<p>
			작성자 :
			<%=session.getAttribute("userid")%></p>
			<%if (rs.next()) {%>
		<p>
			<label>제목: <input type="text" name="b_title"
				value="<%=rs.getString("b_title")%>"></label>
		</p>
		<p>내용</p>
		<p>
			<textarea rows="5" cols="40" name="b_content"><%=rs.getString("b_content")%></textarea>
		</p>
		<%
			}
		%>
		<p>
			<input type="submit" value="수정완료"> <input type="reset"
				value="다시작성"> <input type="button" value="리스트"
				onclick="location.href='list.jsp'">
		</p>
	</form>
</body>
</html>
<%
	}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
%>