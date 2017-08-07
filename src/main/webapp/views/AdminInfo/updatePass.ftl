<script>
$(function(){
   	$("#newpass_content").parents(".dialog:first").find(".close").hide();
	$("#erroInfo").hide();
	$("#erroInfo1").hide();
	$("#casepass").hide();
});

function checkNameCallback(tes){
		var pwd = $("#pwd").val();
		var rpwd = $("#rpwd").val();
		if(pwd.length<6){
			$("#casepass").show();
			return false;
		}else{
			$("#casepass").hide();
		}
		if(rpwd!=pwd){
			$("#erroInfo").show();
			$("#erroInfo1").show();
			return false;
		}else{
			$("#erroInfo").hide();
			$("#erroInfo1").hide();
		}  
		return validateCallback(tes, dialogAjaxDonePenoncel); 
}
function Case(){
		var pwd = $("#pwd").val();
		var rpwd = $("#rpwd").val();
		if(pwd.length<6){
			$("#casepass").show();
		}else{
			$("#casepass").hide();
		}
		if(rpwd!=pwd){
			$("#erroInfo").show();
			$("#erroInfo1").show();
		}else{
			$("#erroInfo").hide();
			$("#erroInfo1").hide();
		}  
}

</script>
<div class="pageContent" style="overflow:hidden;">
	<form method="post" action="/carnival/?spm=${encryption("${action}/updateSavePass")}" class="pageForm required-validate" onsubmit="return checkNameCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="50" style="background-color:#ecf2f4;overflow:hidden;padding-bottom:0">
			<input type="hidden" name="id" value="${id}">
			<input type="hidden" name="admin_realname" value="${admin_realname}">
			<fieldset>
			<p>
				<label >用&nbsp;&nbsp;户&nbsp;&nbsp;&nbsp;名:</label>
				<input type="text" size="17" name="amin_name" value="${amin_name}" readonly="readonly" />
			</p>
			<p>
				<label>修改密码：</label>
				<input id = "pwd" name="pass"  type = "password" size = "17" class = "required" title="请输入修改密码" maxlength="15"/>
				<label style="width:120px;color:red;" id="casepass"><span>密码长度最少6位数!</span></label>
			</p>
			<p>
				<label>确认密码：</label>
				<input id = "rpwd" title="请输入确认密码" size = "17"  type="password"  class="required" maxlength="15" onKeyUp="Case()" />
				<label style="width:120px;color:red;" id="erroInfo1"><span>两次密码输入不一致!</span></label>
			</p>
			</fieldset>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>

