<%@page import="com.yc.bean.UserBean"%>
<%@page import="com.yc.biz.UserBiz"%>
<%@page import="com.yc.util.WebUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript">
window.onload = function(){
	var input = document.getElementById("loginBox").getElementsByTagName("input");
	for(i=0; i<input.length; i++) {
		var type = input[i].getAttribute("type");
		if(type == "text" || type == "password") {
			input[i].onfocus = function(){
				this.className += " input-text-over";
			}
			input[i].onblur = function(){
				this.className = this.className.replace(/input-text-over/, "");
			}
		} else if(type == "submit") {
			input[i].onmouseover = function(){
				this.className += " input-submit-over";
			}
			input[i].onmouseout = function(){
				this.className = this.className.replace(/input-button-over/, "");
			}
		}
	}
}
</script>
</head>
<body>
<div id="header" class="wrap">
	<img src="images/logo.gif" />
</div>
<div id="login" class="wrap">
	<div class="main">
		<div class="corner"></div>
		<div class="introduce">
			<h2>源辰信息</h2>
			<p>网上调查系统...</p>
		</div>
		<div class="login">
			<h2>用户登录</h2>
			<form>
				<dl id="loginBox">
					<dt>用户名：</dt>
					<dd><input type="text" class="input-text" name="uname" id="uname" value=""/></dd>
					<dt>密　码：</dt>
					<dd><input type="password" class="input-text" name="pwd" id="pwd" value=""/></dd>
					<dt></dt>
					<dd><input type="button" class="input-button" name="submit" value="登录" onclick="login()"/> <a href="register.jsp">新用户注册</a></dd>
				</dl>
			</form>
			<div class="error"></div>
		</div>
	</div>
</div>
<div class="wrap">
</div>
<div id="footer" class="wrap">
	源辰信息 &copy; 版权所有
</div>
<script type="text/javascript">
	var xhr;
	function createXMLHttpRequest() {
		// 浏览器兼容
		if (window.XMLHttpRequest) {
			xhr = new XMLHttpRequest();
		} else {
			xhr = new ActiveObject("MSXML2.XMLHTTP");
		}
		// 创建不成功 该浏览器不支持XMLHttpRequest()
		if (xhr == undefined || xhr == null) {
			alert("该浏览器不支持XMLHttpRequest");
			return;
		}
		return xhr;
	}
	
	// 用户登录
	function login() {
		// 获取用户名和密码  
		var uname = document.getElementById("uname").value;
		var pwd = document.getElementById("pwd").value;
		console.log(uname + "--" + pwd);
		// 获取xhr对象
		createXMLHttpRequest();
		// 建立连接
		//xhr.open("POST","doLogin.jsp",true);
		xhr.open("POST","voteUserServlet?op=login",true);
		// 状态改变事件
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) { // XMLHttpRequest对象读取响应结束
				if (xhr.status == 200) { // HTTP状态码 服务器响应正常
					// 获取响应结果  xml解析
					var data = xhr.responseText;
					console.log(data);
					if (data.trim() == "success") {
						alert("登录成功");
						window.location.href = "manage.jsp";
					} else {
						alert("登录失败");
					}
				}
			}
		}
		// post请求传递参数  设置请求头
		xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		// 传递参数
		xhr.send("uname=" + uname + "&pwd=" + pwd);
	}

</script>
</body>
</html>
