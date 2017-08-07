
<div class="pageContent" style="overflow:hidden;">
	<form method="post" action="${baseClass}/save" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="50" style="background-color:#ecf2f4;overflow:hidden;padding-bottom:0">
			<fieldset>
				<p>
					<label style="width:60px;"></label>
					<input type="text" name="id" value="${(baseinfoKey.id)!}"/>
				</p>
				<p>
					<label style="width:60px;">添加时间</label>
					<input type="text" name="addtime" value="${(baseinfoKey.addtime)!}"/>
				</p>
				<p>
					<label style="width:60px;">父类KEY的名称</label>
					<input type="text" name="parent_key_name" value="${(baseinfoKey.parent_key_name)!}"/>
				</p>
				<p>
					<label style="width:60px;">KEY名称</label>
					<input type="text" name="key_name" value="${(baseinfoKey.key_name)!}"/>
				</p>
				<p>
					<label style="width:60px;">备注</label>
					<input type="text" name="remark" value="${(baseinfoKey.remark)!}"/>
				</p>
				<p>
					<label style="width:60px;">0-启用，1-禁用</label>
					<input type="text" name="status" value="${(baseinfoKey.status)!}"/>
				</p>
				<p>
					<label style="width:60px;">值(内容)</label>
					<input type="text" name="key_value" value="${(baseinfoKey.key_value)!}"/>
				</p>
				<p>
					<label style="width:60px;">父类KEY标识</label>
					<input type="text" name="parent_key_en" value="${(baseinfoKey.parent_key_en)!}"/>
				</p>
				<p>
					<label style="width:60px;">添加人</label>
					<input type="text" name="addname" value="${(baseinfoKey.addname)!}"/>
				</p>
				<p>
					<label style="width:60px;">KEY的英文标识</label>
					<input type="text" name="key_en" value="${(baseinfoKey.key_en)!}"/>
				</p>
				<p>
					<label style="width:60px;">值个数(0-无，1-一个，2-多个)</label>
					<input type="text" name="vl_num" value="${(baseinfoKey.vl_num)!}"/>
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

