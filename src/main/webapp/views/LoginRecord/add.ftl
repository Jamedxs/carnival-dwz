
<div class="pageContent" style="overflow:hidden;">
	<form method="post" action="${baseClass}/save" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="50" style="background-color:#ecf2f4;overflow:hidden;padding-bottom:0">
			<fieldset>
				<p>
					<label style="width:60px;">编号</label>
					<input type="text" name="id" value="${(loginRecord.id)!}"/>
				</p>
				<p>
					<label style="width:60px;">所属企业编号</label>
					<input type="text" name="enterprise_no" value="${(loginRecord.enterprise_no)!}"/>
				</p>
				<p>
					<label style="width:60px;">登录名</label>
					<input type="text" name="user_name" value="${(loginRecord.user_name)!}"/>
				</p>
				<p>
					<label style="width:60px;">登录时间</label>
					<input type="text" name="login_time" value="${(loginRecord.login_time)!}"/>
				</p>
				<p>
					<label style="width:60px;">登录状态0成功1失败</label>
					<input type="text" name="login_state" value="${(loginRecord.login_state)!}"/>
				</p>
				<p>
					<label style="width:60px;">姓名</label>
					<input type="text" name="real_name" value="${(loginRecord.real_name)!}"/>
				</p>
				<p>
					<label style="width:60px;">登录ip</label>
					<input type="text" name="login_ip" value="${(loginRecord.login_ip)!}"/>
				</p>
				<p>
					<label style="width:60px;">内容0成功1密码错误2验证码错误</label>
					<input type="text" name="note" value="${(loginRecord.note)!}"/>
				</p>
				<p>
					<label style="width:60px;">登录日期</label>
					<input type="text" name="login_date" value="${(loginRecord.login_date)!}"/>
				</p>
				<input type="hidden" name="id" value="${(baseinfoClass.id)!}">
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

