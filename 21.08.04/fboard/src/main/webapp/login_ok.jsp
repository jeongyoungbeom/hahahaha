<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="dao" class="com.koreait.member.MemberDAO"/>
<jsp:useBean id="member" class="com.koreait.member.MemberDTO"/>
<jsp:setProperty property="*" name="member"/>
<%	
	if(dao.login(member) != null){
		session.setAttribute("userid", member.getUserid());
		session.setAttribute("name", member.getUsername());
		session.setAttribute("idx", member.getIdx());
	
%>
<script>
	alert('로그인 되었습니다.');
	location.href = 'login.jsp'; // 새로고침 캐시가 남지않음
</script>
<%
	} else {
				// 로그인 실패
%>
<script>
	alert('아이디 또는 비밀번호를 확인하세요.');
	history.back(); // 뒤로가기 ,히스토리  캐시가 남아있음
</script>
<%
	}
%>