<%@page import="com.yc.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	UserBean user = (UserBean)session.getAttribute("user");
	if(null==user){
		response.sendRedirect("login.jsp");
		
	}else{
	System.out.print(user);
%>	
	<div id="navbar" class="wrap">
		<div class="profile">
		 您好，<%=user.getUname()%>
		 <span class="return"><a href="manage.jsp">返回列表</a></span>
		 <span class="addnew"><a href="add.jsp">添加新投票</a></span>
		 <span class="modify"><a href="manage.jsp">维护</a></span>
		</div>
		
	<div class="search">
		<form method="post" action="">
			<input type="text" name="keywords" class="input-text" value=""/><input type="submit" name="submit" class="input-button" value="" />
		</form>
	</div>
	</div>
	
<% 
}
%>	