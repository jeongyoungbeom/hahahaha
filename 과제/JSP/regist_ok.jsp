<%@page import="com.koreait.db.Dbconn"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	String userid = request.getParameter("userid");
	String userpw = request.getParameter("userpw");
	String name = request.getParameter("name");
	String hp = request.getParameter("hp");
	String email = request.getParameter("email");
	String hobby[] = request.getParameterValues("hobby");
	String hobbystr = "";
	for(int i=0; i<hobby.length;i++){
		hobbystr = hobbystr + hobby[i] + " ";
	}
	String ssn1 = request.getParameter("ssn1");
	String ssn2 = request.getParameter("ssn2");
	String zipcode = request.getParameter("zipcode");
	String address1 = request.getParameter("address1");
	String address2 = request.getParameter("address2");
	String address3 = request.getParameter("address3");

	Connection conn = null;
	PreparedStatement pstmt = null;
	String sql = "";

	try {
		conn = Dbconn.getConnection();
		if (conn != null) {
			sql = "insert into tb_member(mem_userid, mem_userpw, mem_name, mem_hp, mem_email, mem_hobby, mem_ssn1, mem_ssn2, mem_zipcode, mem_address1, mem_address2, mem_address3) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, userpw);
			pstmt.setString(3, name);
			pstmt.setString(4, hp);
			pstmt.setString(5, email);
			pstmt.setString(6, hobbystr);
			pstmt.setString(7, ssn1);
			pstmt.setString(8, ssn2);
			pstmt.setString(9, zipcode);
			pstmt.setString(10, address1);
			pstmt.setString(11, address2);
			pstmt.setString(12, address3);
			pstmt.executeUpdate();
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<script>
	alert('회원가입 완료되었습니다.');
	location.href='login.jsp';
</script>