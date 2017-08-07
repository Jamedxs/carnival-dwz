
 <html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录后台管理系统</title>
 
<script language="JavaScript" src="${base}/public/login/js/jquery.js"></script>
<link href="${base}/public/login/css/style.css" rel="stylesheet" type="text/css" />
<script src="${base}/public/login/js/cloud.js" type="text/javascript"></script>
<script language="JavaScript" src="${base}/public/dwz/js/my_login.js"></script>

<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
});  
</script> 

</head>

<body style="background-color:#1c77ac; background-image:url(${base}/public/login/images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎登录后台管理界面平台</span>    
    <ul>
   
    </ul>    
    </div>
    
    <div class="loginbody">
    
    <span class="systemlogo"></span> 
       
    <div class="loginbox">
    <ul>
    <li><input name="admin_name" id="userName" type="text" class="loginuser" value="请输入用户名" style="color:#CCCCCC;" onfocus="javascript:this.style.color='#000000';if(this.value=='请输入用户名')this.value='';" onblur="javascript:if(this.value==''){this.value='请输入用户名';this.style.color='#CCCCCC';}" /></li>
    <li><input name="pass"  id="userPwd" type="password" class="loginpwd" value="请输入密码" style="color:#CCCCCC;" onfocus="javascript:this.style.color='#000000';if(this.value=='请输入密码')this.value='';" onblur="javascript:if(this.value==''){this.value='请输入密码';this.style.color='#CCCCCC';}" /></li>
     <li>
    	<input  type="text" id="code" name="code" size="12" style="height:30px; width:70px; border:solid 1px #cadcb2; font-size:15px; color:black;font-weight: bold;vertical-align:middle;"/>
		<img id="validateCode" src="/carnival/CaptchaImageCreate/captcha-image" style="margin-left:5px;vertical-align:middle;cursor:pointer;" border="1" height="30" onclick = "changeCode()" />
		<input type="submit" class="loginbtn" id = "loginButton" value="登录" />
	</li>
	<li>
		<span style="color:#FF0000;font-weight:bold;" id = "warnmsg" ></span>
	</li>
    </ul>
    </div>
    </div>
    <div class="loginbm">版权所有  2015   银联天津分公司</div>
</body>

</html>
