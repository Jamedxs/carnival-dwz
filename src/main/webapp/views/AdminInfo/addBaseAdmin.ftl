<script>
	function only(ele, arr) {
		if (arr.length == 0) {
			return true;
		}
		for ( var j = 0; j < arr.length; j++) {
			if (ele == arr[j]) {
				return false;
			} else {
				return true;
			}
		}
	}

	var arr = [ 0, 1, 2, 3, 4, 5, 6,7,8,9,"a", "b", "c", "d", "e", "f", "g","h", "i", "j", "k", "l", "m", "n","o", "p", "q", "r", "s", "t", "u","v", "w", "x", "y", "z","A", "B", "C", "D", "E", "F", "G","H", "I", "J", "K", "L", "M", "N","O", "P", "Q", "R", "S", "T", "U","V", "W", "X", "Y", "Z" ];

	function onlyy() {
		var randNum = null;
		var old = [];
		var str = "";
		function done() {
			randNum = Math.floor(Math.random() * 42);
			if (only(randNum, old)) {
				str = str + arr[randNum];
				old.push(randNum);
			} else {
				done();
			}
		}
		for ( var index = 0; index < 6; index++) {
			done();
		}
		console.log(str);
		$("#pass").val(str);
	}
$(function(){
   	$("#adminname_content").parents(".dialog:first").find(".close").hide();
	$("#adminname").hide();
});
	function xaCallback(tes){
		var admi = $("#adminame").val()
		var reg = /^[0-9a-zA-Z]/;
		if(!reg.exec(admi)){
			$("#adminname").show();	
			return false;
		}else{
			$("#adminname").hide();
		}
		return validateCallback(tes, dialogAjaxDone); 
	}
	
	function Case(){
		var admi = $("#adminame").val()
		var reg = /^[0-9a-zA-Z]/;
		if(!reg.exec(admi)){
			$("#adminname").show();	
		}else{
			$("#adminname").hide();
		}
	}	
	
</script>

<div class="pageContent" style="overflow:hidden;">
	<form method="post" action="/carnival/?spm=${encryption("AdminInfo/saveBaseAdminUser")}" class="pageForm required-validate" onsubmit="return xaCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="50" style="background-color:#ecf2f4;overflow:hidden;padding-bottom:0">
			<input type="hidden" name="id" value="${(adminInfo.id)!}">
			<input type="hidden" name="enterprise_no" 
			<#if listID='insert'>value="${enterprise_no}"</#if>
			<#if listID='select'>value="${(adminInfo.enterprise_no)!}"</#if>
			/>
			<fieldset>
				<p>
					<label style="width:60px;">用户名：</label>
					<#if listID='insert'>
						<input type="text" name="admin_name" value="${(adminInfo.admin_name)!}" remote="/carnival/?spm=${encryption("${action}/onseBaseName")}" id="adminame" onblur="Case()" class="required" />
						<label style="width:120px;color:red;" id="adminname" ><span>用户名不能是中文!</span></label>
					</#if>
					<#if listID='select'>
						<input type="text" name="admin_name" value="${(adminInfo.admin_name)!}" readonly="readonly"/>
					</#if>
				</p>
			<#if listID='insert'>
				<p>
					<label style="width:60px;">初始密码：</label>
					<input type="text" id="pass" name="pass" value="${(adminInfo.pass)!}"/>
					<input type="button" onclick="onlyy();" value="随机密码"></input>
				</p>
			</#if>
				<p>
					<label style="width:60px;">真实姓名：</label>
					<input type="text" name="admin_realname" value="${(adminInfo.admin_realname)!}"class="required" />
				</p>
				<p>
					<label style="width:60px;">手机号：</label>
					<input type="text" name="phone" value="${(adminInfo.phone)!}" maxlength="11" class="phone required"/>
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

