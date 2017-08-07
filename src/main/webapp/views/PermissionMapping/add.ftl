
<div class="pageContent" style="overflow:hidden;">
	<form method="post" action="${baseClass}/save" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="50" style="background-color:#ecf2f4;overflow:hidden;padding-bottom:0">
			<fieldset>
				<p>
					<label style="width:60px;">主键</label>
					<input type="text" name="id" value="${(permissionMapping.id)!}"/>
				</p>
				<p>
					<label style="width:60px;">用户ID</label>
					<input type="text" name="admin_id" value="${(permissionMapping.admin_id)!}"/>
				</p>
				<p>
					<label style="width:60px;">按钮ID</label>
					<input type="text" name="per_id" value="${(permissionMapping.per_id)!}"/>
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

