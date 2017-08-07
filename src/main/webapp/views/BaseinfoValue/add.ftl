
<div class="pageContent" style="overflow:hidden;">
	<form method="post" action="${baseClass}/save" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="50" style="background-color:#ecf2f4;overflow:hidden;padding-bottom:0">
			<fieldset>
				<p>
					<label style="width:60px;"></label>
					<input type="text" name="id" value="${(baseinfoValue.id)!}"/>
				</p>
				<p>
					<label style="width:60px;">内容</label>
					<input type="text" name="value_content" value="${(baseinfoValue.value_content)!}"/>
				</p>
				<p>
					<label style="width:60px;">名称</label>
					<input type="text" name="value_name" value="${(baseinfoValue.value_name)!}"/>
				</p>
				<p>
					<label style="width:60px;">添加人</label>
					<input type="text" name="addname" value="${(baseinfoValue.addname)!}"/>
				</p>
				<p>
					<label style="width:60px;">所属类型</label>
					<input type="text" name="key_en" value="${(baseinfoValue.key_en)!}"/>
				</p>
				<p>
					<label style="width:60px;">英文标识</label>
					<input type="text" name="value_en" value="${(baseinfoValue.value_en)!}"/>
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

