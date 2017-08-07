
<div class="pageContent" style="overflow:hidden;">
	<form method="post" action="${baseClass}/save" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="50" style="background-color:#ecf2f4;overflow:hidden;padding-bottom:0">
			<fieldset>
				<p>
					<label style="width:60px;">主键</label>
					<input type="text" name="id" value="${(permission.id)!}"/>
				</p>
				<p>
					<label style="width:60px;">类名</label>
					<input type="text" name="action_class" value="${(permission.action_class)!}"/>
				</p>
				<p>
					<label style="width:60px;">表单地址</label>
					<input type="text" name="lower_href" value="${(permission.lower_href)!}"/>
				</p>
				<p>
					<label style="width:60px;">状态(0-启用，1-禁用)</label>
					<input type="text" name="status" value="${(permission.status)!}"/>
				</p>
				<p>
					<label style="width:60px;">行为模板</label>
					<input type="text" name="behavior_args" value="${(permission.behavior_args)!}"/>
				</p>
				<p>
					<label style="width:60px;">行为名称</label>
					<input type="text" name="behavior" value="${(permission.behavior)!}"/>
				</p>
				<p>
					<label style="width:60px;">所属菜单</label>
					<input type="text" name="carte_id" value="${(permission.carte_id)!}"/>
				</p>
				<p>
					<label style="width:60px;">添加时间</label>
					<input type="text" name="create_time" value="${(permission.create_time)!}"/>
				</p>
				<p>
					<label style="width:60px;">按钮名称</label>
					<input type="text" name="button_name" value="${(permission.button_name)!}"/>
				</p>
				<p>
					<label style="width:60px;">按钮路径</label>
					<input type="text" name="href" value="${(permission.href)!}"/>
				</p>
				<p>
					<label style="width:60px;">方法名称</label>
					<input type="text" name="action_method" value="${(permission.action_method)!}"/>
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

