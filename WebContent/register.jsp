<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>注   册</title>
	<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
	<div id="header" class="wrap">
		<img src="images/logo.gif" />
	</div>
	<div id="navbar" class="wrap">
		<div class="search">
		<!--  
			<form method="get" action="index.html">
				<input type="text" name="keywords" class="input-text" /><input type="submit" name="submit" class="input-button" value="" />
			</form>
		-->	
		</div>
	</div>
	<div id="register" class="box">
		<h2>新用户注册</h2>
		<div class="content">
		   <form method="post" action="voteUserServlet" onsubmie="return checkFrom()">
					<input type="hidden" name="op" value="register">
				<dl>
					<dt>用户名：</dt>
					<dd>
						<input type="text" class="input-text" name="uname" id="uname"  onblur="checkName(this)"/>
						<span id="c_name" style="color:red"></span>
					</dd>
					<dt>密码：</dt>
					<dd><input type="password" class="input-text" name="pwd"  id="pwd"/></dd>
					<dt>确认密码：</dt>
					<dd><input type="password" class="input-text" name="cpwd" id="cpwd"/></dd>
					<dt></dt>
				<!-- 	<dd><input type="submit" class="input-button" name="submit"/> </dd> -->
				<dd><input type="submit" class="input-button" name="submit" id="register1"/> </dd>
				</dl>
			</form>
			<div class="error"></div>
			<div class="error"></div>
		</div>
	</div>
	<div id="footer" class="wrap">
		源辰信息 &copy; 版权所有
	</div>
	<script type="text/javascript">
	var pwd =document.getElementById("pwd");	
	var cpwd =document.getElementById("cpwd");	
	
	cpwd.onblur = checkFrom;
	
	function checkFrom(){
		var opwd = pwd.value;
		var npwd = cpwd.value;
		console.log(opwd+"--"+npwd);
		if(opwd==""||npwd==""){
			alert("密码不能为空");
			return false;
		}
		
		if(opwd==npwd){
			return true;
		}else{
			alert("两次密码输入不一致");
			return false;
		}
	}

	
		//定义变量
		var xhr;
		//获取XMLHttpRequest对象实例的方法
		function createXMLHttpRequest(){
			//兼容问题
			//先来判断 当前用户使用的时哪一种浏览器
			if(window.XMLHttpRequest){ //浏览器支持
				//创建对象
				xhr = new XMLHttpRequest();
			}else{
				xhr = new ActiveObject("MSXML2.XMLHTTP");
			}
			//如果创建不成功 该浏览器不支持XMLHttpRequest
			if(xhr == undefined || xhr == null){
				alert("该浏览器不支持XMLHttpRequest");
				return;
			}
			return xhr;
		}
	
		//检查用户名是否被注册
		function checkName(obj){
			//获取用户输入的用户名
			var uname = obj.value;
			//获取XMLHttpRequest
			createXMLHttpRequest();
			//建议连接
			xhr.open("GET","voteUserServlet?op=CheckName&uname="+uname,true);
			//回调函数
			xhr.onreadystatechange = showName;
			//发送请求
			xhr.send(null);
		}
		//回调函数  显示用户名重名的错误信息
		function showName(){
			if(xhr.readyState == 4){ //4  XMLHttpRequest对象读取响应结束
				if(xhr.status == 200){//HTTPP的状态码  服务器响应正常
					//解析doCheckName.jsp返回的数据 获取out.print("xx")的值
					var data = xhr.responseText;
					//显示错误信息
					document.getElementById("c_name").innerHTML = data;
				}
			}
		}
	</script>
	
</html>