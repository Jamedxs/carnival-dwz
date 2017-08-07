$(document).ready(function()
{ 
	$(document).keypress(function(e) { 
		    // 回车键事件 
		    if(e.which == 13) {
		    	//登录方法
				loginForm();
		    } 
	}); 
	
	$("#loginButton").click(function(){
		//登录方法
		loginForm();
	});
	
});
 
 function loginForm(){
	 if(checkForm()){          //验证登录
			var name = $("#userName").val();
			var pwd = $("#userPwd").val();
			var code = $("#code").val();
			var remember =$("#remember").attr("checked")
			$("#loginButton").val("正在登录...");
			$.post(
					"/"+demo_name()+"/AdminInfo/login",
					{"admin_name" : name,"pass" : pwd,"code" : code, "remember":remember},
					function(data){
						//更换验证码
						changeCode();
						$("#loginButton").val("登录");
						if (data['code'] == "7") {
							if (window.location.href.indexOf(demo_name()+"/Index/index") == -1) {
								window.location.href = "/"+demo_name()+"/Index/index?count=1";
							} else {
								window.location.reload(true);
							}
						}
						else if(data['code'] == "0"){
							$("#loginButton").val("正在进入...");
							if (window.location.href.indexOf(demo_name()+"/Index/index") == -1) {
								window.location.href = "/"+demo_name()+"/Index/index";
							} else {
								window.location.reload(true);
							}
						}else if(data['code'] == "1"){
							$("#warnmsg").text("用户不存在！！");
						}else if(data['code'] == "2"){
							$("#warnmsg").text("用户已禁用！");
						}else if(data['code'] == "3"){
							$("#warnmsg").text(data['msg']);
						}else if(data['code'] == "4"){
							$("#warnmsg").text("验证码输入有误！");
						}else if(data['code'] == "5"){
							$("#warnmsg").text("登录异常，请联系管理员！");
						}else if(data['code'] == "6"){
							$("#warnmsg").text("密码不正确5次,该用户已被冻结！");
						}
					}
			,"json");
			
		}
 }
 
 function checkForm() {
	 var name = $("#userName").val();
	 if (name.length <= 0) {
		 $("#warnmsg").text("用户名不能为空！");
		 return false;
	 }  
	 var pass = $("#userPwd").val();
	 if (pass.length <= 0) {
		 $("#warnmsg").text("密码不能为空！");
		 return false;
	 }
	 var code = $("#code").val();
	 if (code.length <= 0) {
		 $("#warnmsg").text("验证码不能为空！");
		 return false;
	 }
	 return true;
 }
  
 function demo_name() {
	 var pathName=window.document.location.pathname;
	 return pathName.substring(1,pathName.substr(1).indexOf('/')+1);
 }
 //改变验证码
 function changeCode(){
	 $("#validateCode").attr('src', "/"+demo_name()+'/CaptchaImageCreate/captcha-image?date='+new Date().getTime());     
 }