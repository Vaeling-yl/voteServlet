<%@page import="com.yc.dao.VoteDAO"%>
<%@page import="com.yc.dao.OptionsDAO"%>
<%@page import="com.yc.bean.OptionsBean"%>
<%@page import="com.yc.bean.VoteBean"%>
<%@page import="java.util.List"%>
<%@page import="com.yc.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>投票列表</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />

</head>
<body>
	<%
		VoteDAO vdao = new VoteDAO();
		List<VoteBean> list = vdao.findByTrem(null);
		//System.out.println(list);
	%>
	<div id="header" class="wrap">
		<img src="images/logo.gif" />
	</div>

	<%@ include file="control.jsp"%>
	
	<div id="vote" class="wrap">
		<h2>投票列表</h2>
		<ul class="list">
			<%
				OptionsDAO odo = new OptionsDAO();
				for (VoteBean vote : list) {
					OptionsBean bean = new OptionsBean();
					bean.setVid(vote.getVid());
					List<OptionsBean> options = odo.findByTerm(bean);
				
					//System.out.println("共有"+options.size()+"个选项");
					//System.out.println(options);
					int count = odo.getCountUsid(vote.getVid());
			%>
			<li class="odd">
				<h4>
					<a href="voteUserServlet?op=doView&vid=<%=vote.getVid()%>"><%=vote.getVname()%></a>
				</h4>
				<div class="join">
					<a href="voteUserServlet?op=doVote&vid=<%=vote.getVid()%>">我要参与</a>
				</div>
				<p class="info">
					共有<%=options.size()%>个选项，已有
					<%=count%>
					个网友参与了投票。 /开始和截止时间：<%=vote.getStartDate()%>~<%=vote.getEndDate()%></p>
			</li>

			<%
				}
			%>
		</ul>
	</div>

</body>
</html>