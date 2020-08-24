<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>信息提示界面</title>
</head>
<body>
<%
String info = request.getAttribute("info")+"";
%>
<div id="header" class="wrap">
		<img src="images/logo.gif" />
	</div>
	<div id="navbar" class="wrap">
		<div class="search">
			<form method="get" action="index.html">
				<input type="text" name="keywords" class="input-text" />
				<input type="submit" name="submit" class="input-button" value="" />
			</form>
		</div>
	</div>
<div id="message" class="box">
	<h2>提示信息</h2>
	<div class="content">
	<%
		if("r_success".equals(info)){
			out.print("<p>恭喜：注册成功！<a href=\"login.jsp\">进入登录首页&gt;&gt;</a></p>");
		}else if("r_error".equals(info)){
			out.print("<p>错误提示：用户注册失败！<a href=\"register.jsp\">进入注册首页&gt;&gt;</a></p>");
		}else if("v_success".equals(info)){
				out.print("<p>恭喜：添加主题成功！<a href=\"manage.jsp\">进入投票首页&gt;&gt;</a></p>");
		}else if("v_error".equals(info)){
				out.print("<p>错误提示：添加主题失败！<a href=\"add.jsp\">进入注册首页&gt;&gt;</a></p>");
		}else if("a_success".equals(info)){
			out.print("<p>恭喜：用户投票成功！<a href=\"manage.jsp\">进入投票主题首页&gt;&gt;</a></p>");
	}else if("a_error".equals(info)){
			out.print("<p>错误提示：用户投票失败！<a href=\"vote.jsp\">进入投票首页&gt;&gt;</a></p>");
	}
	%>
	</div>
</div>
</body>
</html>