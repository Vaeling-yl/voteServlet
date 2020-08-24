<%@page import="com.yc.vo.VoteVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>参与投票</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
<%
	// 从doVote.jsp获取网友数量
	List<VoteVO> list = (List<VoteVO>)request.getAttribute("votes");
	int count = (Integer)request.getAttribute("count");
	//System.out.println("count:" + count);
%>
<div id="header" class="wrap">
	<img src="images/logo.gif" />
</div>
<!-- 静态包含 -->
<%@ include file="control.jsp" %>

<div id="vote" class="wrap">
	<h2>参与投票</h2>
	<ul class="list">
		<li>
			<h4><%=list.get(0).getVname() %></h4>
			<p class="info">共有<%=list.size() %>个选项，已有<%=count %>个网友参与了投票。</p>
			<form method="post" action="voteUserServlet">
				<!-- 隐藏域 传递参数 -->
				<input type="hidden" name="op" value="doAddVote">
			    <input type="hidden" name="vtype" value="<%=list.get(0).getVtype()%>"/> 
				<ol>
				   <%for(VoteVO v : list) { %>
					<li><input type="checkbox" name="options"  value="<%=v.getOpid() %>"/><%=v.getOpname() %></li>
				   <%} %>	
				</ol>
				<p class="voteView">
				<input type="image" src="images/button_vote.gif" />
				<a href="voteUserServlet?op=doView&vid=<%=list.get(0).getVid() %>"><img src="images/button_view.gif" /></a>
				</p>
			</form>
		</li>
	</ul>
</div>
<div id="footer" class="wrap">
	源辰信息 &copy; 版权所有
</div>
<script src="js/jquery-3.5.0.min.js"></script>
<script type="text/javascript">
	// 根据主题类型    判断是否为单选
	$(function() {
		// 获取所有的复选框
		var options = $("input[name='options']");
		// 获取主题类型
		var vtype = $("input[name='vtype']").val();
		// 复选框添加点击事件
		options.click(function() {
			if (vtype == 1) { //单选
				for(let i = 0, len = options.length; i < len; i++) {
					if (options[i] != this) { // 除当前以外的复选框
						$(options[i]).prop("checked", false);
					}
				}
			}
		});
	})

</script>
</body>
</html>
