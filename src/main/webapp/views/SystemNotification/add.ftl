
<div class="pageContent" style="overflow:hidden;">
	<form method="post" action="${baseClass}/save" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="50" style="background-color:#ecf2f4;overflow:hidden;padding-bottom:0">
			<fieldset>
				<p>
					<label style="width:60px;">编号</label>
					<input type="text" name="id" value="${(systemNotification.id)!}"/>
				</p>
				<p>
					<label style="width:60px;">发布人</label>
					<input type="text" name="add_man" value="${(systemNotification.add_man)!}"/>
				</p>
				<p>
					<label style="width:60px;">标题</label>
					<input type="text" name="theme" value="${(systemNotification.theme)!}"/>
				</p>
				<p>
					<label style="width:60px;">发布时间</label>
					<input type="text" name="create_time" value="${(systemNotification.create_time)!}"/>
				</p>
				<p>
					<label style="width:60px;">内容</label>
					<input type="text" name="note" value="${(systemNotification.note)!}"/>
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

