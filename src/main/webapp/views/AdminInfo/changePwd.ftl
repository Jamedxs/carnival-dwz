<script>
$(function(){
   	$("#newpass_content").parents(".dialog:first").find(".close").hide();
	$("#erroInfo").hide();
});

function checkPwdIsTrue(th, dialogAjaxDone){
		var pwd = $("#pwd").val();
		var rpwd = $("#rpwd").val();
		if(pwd.length<6){
			alertMsg.info('密码长度不能小于6位');
			return false;
		}
		if(rpwd!=pwd){
			$("#erroInfo").show();
			return false;
		}else{
			$("#erroInfo").hide();
		}
}
</script>
<div class="pageContent" id="newpass_content">
	<form method="post" action="${base}/AdminInfo/publicChangePwdForUser/" class="pageForm required-validate" onsubmit="return checkPwdIsTrue(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>修改密码：</label>
				<input id = "pwd" name="adminPass"  type = "password" size = "12" class = "required" title="请输入修改密码" />
			</p>
			<p>
				<label>确认密码：</label>
				<input id = "rpwd" title="请输入确认密码" size = "12"  type = "password"  class = "required"/>
				<label style = "color:red;" id = "erroInfo"><span>两次密码输入不一致!</span></label>
			</p>
			<p style = "color:red;text-align:center;font-size:12px;padding-top:5%">*第一次登录请修改密码！修改成功后自动跳转到登录页面！</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
			</ul>
		</div>
	</form>
</div>

