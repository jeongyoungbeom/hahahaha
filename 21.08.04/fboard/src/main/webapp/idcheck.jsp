<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean class="com.koreait.member.MemberDAO" id="dao"/>
<%
	String userid = request.getParameter("userid");
	if(dao.idCheck(userid)){
		out.print("no");
	}else{
		out.print("ok");
	}
%>