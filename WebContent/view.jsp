<%@page import="java.text.Format"%>
<%@page import="com.yc.vo.VoteVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
	<%
		// 从doView.jsp获取网友数量和总票数
		List<VoteVO> list = (List<VoteVO>) request.getAttribute("votes");
		int count = (Integer) request.getAttribute("count");
		int total = (Integer) request.getAttribute("total");
	%>
	<div id="header" class="wrap">
		<img src="images/logo.gif" />
	</div>

	<!-- 静态包含 -->
	<%@ include file="control.jsp"%>

	<div id="vote" class="wrap">
		<h2>查看投票</h2>
		<ul class="list">
			<li>

				<h4><%=list.get(0).getVname()%></h4>
				<p class="info">
					共有
					<%=list.size()%>
					个选项，已有
					<%=count%>
					个网友参与了投票。
				</p>
				<ol>
					<%
						for (VoteVO v : list) {
							String percentString = "";
							double percent = 0;
							if (total == 0) {
								percentString = "0";

							} else {
								percent = v.getViews() * 1.0 / total * 100;
								percentString = String.format("%.2f", percent);
							}
					%>
					<li><span><%=v.getOpname()%></span>
						<div class="rate">
							<div class="ratebg">
								<div class="percent" style="width:<%=percent%>"></div>
							</div>
							<p><%=v.getViews()%>票<span>(<%=percentString%>%)
								</span>
							</p>
						</div></li>
					<%
						}
					%>

				</ol>
				<div class="goback">
					<a href="manage.jsp">返回投票列表</a>
				</div>
			</li>
		</ul>
	</div>

</body>
</html>